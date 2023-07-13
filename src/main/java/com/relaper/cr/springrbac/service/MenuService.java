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

    /**
     * 返回菜单
     * @param queryName 用户名
     * @param queryType 类型
     * @return
     */
    List<MyMenu> getMenuAll(String queryName,Integer queryType);

    /**
     * 获取菜单信息
     * @param id
     * @return
     */
    MyMenu getMenuById(Integer id);

    /**
     * 菜单树
     * @return
     */
    List<MenuDto> buildMenuAll();

    /**
     * 跟新菜单
     * @param menu
     * @return
     */
    Result updateMenu(MyMenu menu);

    /**
     * 保存菜单
     * @param menu
     * @return
     */
    Result<MyMenu> save(MyMenu menu);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    Result delete(Integer id);

    /**
     * 通过权限id绘制菜单树
     * @param roleId
     * @return
     */
    List<MenuDto> buildMenuAllByRoleId(Integer roleId);

    /**
     * 通过用户id获取菜单
     * @param userId
     * @return
     */
    List<MenuIndexDto> getMenu(Integer userId);

}
