package com.relaper.cr.springrbac.controller;

import com.relaper.cr.springrbac.dto.RoleDto;
import com.relaper.cr.springrbac.entity.MyRole;
import com.relaper.cr.springrbac.service.RoleService;
import com.relaper.cr.springrbac.utils.PageTableRequest;
import com.relaper.cr.springrbac.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Controller
@RequestMapping("/api/role")
@Api(tags = "系统：角色管理")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "分页返回角色列表")
    public Result list(PageTableRequest request,String queryName) {
        request.countOffset();
        return roleService.getFuzzyRolesByPage(request.getOffset(), request.getLimit(),queryName);
    }

    @GetMapping(value = "/edit")
    @ApiOperation(value = "修改角色页面")
    public String editRole(Model model, MyRole role) {
        model.addAttribute("MyRole",roleService.getRoleById(role.getId()));
        return "system/role/role-edit";
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改角色")
    public Result updateRole(@RequestBody RoleDto roleDto) {
        return roleService.update(roleDto);
    }


    @GetMapping(value = "/add")
    @ApiOperation(value = "添加角色页面")
    public String addRole(Model model) {
        model.addAttribute("MyRole",new MyRole());
        return "/system/role/role-add";
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加角色")
    public Result saveRole(@RequestBody RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @DeleteMapping
    @ResponseBody
    @ApiOperation(value = "删除角色")
    public Result<MyRole> deleteRole(RoleDto roleDto) {
        return roleService.delete(roleDto.getId());
    }

    @GetMapping("/all")
    @ResponseBody
    @ApiOperation(value = "角色列表")
    public Result<MyRole> getAll(){
        return roleService.getAllRoles();
    }
}
