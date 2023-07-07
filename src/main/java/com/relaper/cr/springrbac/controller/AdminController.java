package com.relaper.cr.springrbac.controller;

import com.relaper.cr.springrbac.dto.MenuIndexDto;
import com.relaper.cr.springrbac.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Cr
 * @date 2023/7/6
 */
@Controller
@RequestMapping("/api")
@Api(tags = "系统：菜单路由")
public class AdminController {
    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/index")
    @ResponseBody
    @ApiOperation(value = "通过用户id获取菜单")
    public List<MenuIndexDto> getMenu(Integer userId) {
        return menuService.getMenu(userId);
    }

    @GetMapping("/console")
    public String console(){
        return "console/console1";
    }

    @GetMapping("/403")
    public String error403(){
        return "error/403";
    }

    @GetMapping("/404")
    public String error404(){
        return "error/404";
    }

    @GetMapping("/500")
    public String error500(){
        return "error/500";
    }
    @GetMapping("/admin")
    public String admin(){
        return "index";
    }
}

