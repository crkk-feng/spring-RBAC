package com.relaper.cr.springrbac.service;

import com.relaper.cr.springrbac.dto.MenuDto;
import com.relaper.cr.springrbac.dto.MenuIndexDto;
import com.relaper.cr.springrbac.entity.MyMenu;
import com.relaper.cr.springrbac.utils.Result;

import java.util.List;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
public interface MenuService {

    List<MyMenu> getMenuAll(String queryName,Integer queryType);

    MyMenu getMenuById(Integer id);

    List<MenuDto> buildMenuAll();

    Result updateMenu(MyMenu menu);

    Result<MyMenu> save(MyMenu menu);

    Result delete(Integer id);

    List<MenuDto> buildMenuAllByRoleId(Integer roleId);

    List<MenuIndexDto> getMenu(Integer userId);

}
