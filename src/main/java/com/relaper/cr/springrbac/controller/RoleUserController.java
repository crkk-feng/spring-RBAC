package com.relaper.cr.springrbac.controller;

import com.relaper.cr.springrbac.service.RoleUserService;
import com.relaper.cr.springrbac.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Controller
@RequestMapping("/api/roleuser")
@Api(tags = "系统：用户角色管理")
public class RoleUserController {
    @Autowired
    private RoleUserService roleUserService;

    @PostMapping()
    @ResponseBody
    @ApiOperation(value = "通过用户id返回角色")
    public Result getRoleUserByUserId(Integer userId) {
        return roleUserService.getMyRoleUserByUserId(userId);
    }
}
