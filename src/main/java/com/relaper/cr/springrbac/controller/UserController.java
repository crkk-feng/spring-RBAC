package com.relaper.cr.springrbac.controller;

import com.relaper.cr.springrbac.dto.UserDto;
import com.relaper.cr.springrbac.dto.UserQueryDto;
import com.relaper.cr.springrbac.entity.MyUser;
import com.relaper.cr.springrbac.log.aop.MyLog;
import com.relaper.cr.springrbac.service.UserService;
import com.relaper.cr.springrbac.utils.Md5;
import com.relaper.cr.springrbac.utils.PageTableRequest;
import com.relaper.cr.springrbac.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Controller
@RequestMapping("/api/user")
@Api(tags = "系统：用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('user:list')")
    public String index(){
        return "system/user/user";
    }
    @GetMapping
    @ResponseBody
    @ApiOperation(value = "用户列表")
    @PreAuthorize("hasAnyAuthority('user:list')")
    @MyLog("查询用户")
    public Result<MyUser> userList(PageTableRequest pageTableRequest, UserQueryDto userQueryDto){
        pageTableRequest.countOffset();
        return userService.getAllUsersByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit(),userQueryDto);
    }

    @GetMapping("/add")
    @ApiOperation(value = "添加用户页面")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String addUser(Model model){
        model.addAttribute("MyUser",new MyUser());
        return "/system/user/user-add";
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加用户")
    @PreAuthorize("hasAnyAuthority('user:add')")
    @MyLog("添加用户")
    public Result<MyUser> saveUser(@RequestBody UserDto userDto){
        MyUser myUser = null;
        myUser = userService.getUserByPhone(userDto.getPhone());
        if(myUser !=null && !(myUser.getId().equals(userDto.getId())) ){
            return Result.error().code(20001).message("手机号已存在");
        }
        userDto.setPassword(Md5.crypt("123456"));
        return userService.save(userDto,userDto.getRoleId());
    }

    @GetMapping("edit")
    @ApiOperation(value = "修改用户界面")
    @PreAuthorize("hasAnyAuthority('user:edit')")
    public String editUser(Model model, MyUser tbUser){
        model.addAttribute("MyUser",userService.getUserById(tbUser.getId()));
        return "/system/user/user-edit";
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改用户")
    @PreAuthorize("hasAnyAuthority('user:edit')")
    @MyLog("修改用户")
    public Result<MyUser> updateUser(@RequestBody UserDto userDto){
        MyUser tbUser = null;
        tbUser = userService.getUserByPhone(userDto.getPhone());
        if(tbUser !=null && !(tbUser.getId().equals(userDto.getId())) ){
            return Result.error().message("手机号已存在");
        }
        return userService.updateUser(userDto,userDto.getRoleId());
    }

    @DeleteMapping
    @ResponseBody
    @ApiOperation(value = "删除用户")
    @PreAuthorize("hasAnyAuthority('user:del')")
    @MyLog("删除用户")
    public Result deleteUser(Integer id){
        int count = userService.deleteUser(id);
        if(count>0){
            return Result.ok().message("删除成功");
        }else {
            return Result.error().message("删除失败");
        }
    }
}
