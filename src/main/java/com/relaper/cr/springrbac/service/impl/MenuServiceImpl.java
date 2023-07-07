package com.relaper.cr.springrbac.service.impl;

import com.relaper.cr.springrbac.dao.MenuDao;
import com.relaper.cr.springrbac.dto.MenuDto;
import com.relaper.cr.springrbac.dto.MenuIndexDto;
import com.relaper.cr.springrbac.entity.MyMenu;
import com.relaper.cr.springrbac.service.MenuService;
import com.relaper.cr.springrbac.utils.Result;
import com.relaper.cr.springrbac.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;
    @Override
    public List<MyMenu> getMenuAll(String queryName,Integer queryType) {

        return menuDao.getFuzzyMenu(queryName,queryType);
    }

    @Override
    public MyMenu getMenuById(Integer id) {
        return menuDao.getMenuById(id);
    }

    @Override
    public List<MenuDto> buildMenuAll() {

        return menuDao.buildAll();
    }

    @Override
    public Result updateMenu(MyMenu menu) {
        return (menuDao.update(menu) > 0) ? Result.ok().message("修改成功") : Result.error().message("修改失败");

    }

    @Override
    public Result<MyMenu> save(MyMenu menu) {
        return (menuDao.save(menu) > 0) ? Result.ok().message("添加成功") : Result.error().message("添加失败");

    }

    //如果这里删除了菜单树的父节点，把它的子节点一并删除
    @Override
    public Result delete(Integer id) {
        menuDao.deleteById(id);
        menuDao.deleteByParentId(id);
        return Result.ok().message("删除成功");
    }

    @Override
    public List<MenuDto> buildMenuAllByRoleId(Integer roleId) {
        List<MenuDto> listByRoleId = menuDao.listByRoleId(roleId);
        List<MenuDto> permissionDtos = menuDao.buildAll();
        List<MenuDto> tree = TreeUtil.tree(listByRoleId, permissionDtos);
        return tree;
    }

    @Override
    public List<MenuIndexDto> getMenu(Integer userId) {
        List<MenuIndexDto> list = menuDao.listByUserId(userId);
        List<MenuIndexDto> result = TreeUtil.parseMenuTree(list);
        return result;
    }

}
