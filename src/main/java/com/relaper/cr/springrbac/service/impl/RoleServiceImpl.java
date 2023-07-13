package com.relaper.cr.springrbac.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.relaper.cr.springrbac.dao.RoleDao;
import com.relaper.cr.springrbac.dao.RoleMenuDao;
import com.relaper.cr.springrbac.dao.RoleUserDao;
import com.relaper.cr.springrbac.dto.RoleDto;
import com.relaper.cr.springrbac.entity.MyRole;
import com.relaper.cr.springrbac.entity.MyRoleUser;
import com.relaper.cr.springrbac.service.RoleService;
import com.relaper.cr.springrbac.utils.Result;
import com.relaper.cr.springrbac.utils.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private RoleUserDao roleUserDao;


    @Override
    public Result<MyRole> getFuzzyRolesByPage(Integer offectPosition, Integer limit,String queryName) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<MyRole> fuzzyRolesByPage = roleDao.getFuzzyRolesByPage(queryName);
        return Result.ok().count(page.getTotal()).data(fuzzyRolesByPage).code(ResultCode.TABLE_SUCCESS);
    }

    @Override
    public MyRole getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public Result update(RoleDto roleDto) {
        List<Integer> menuIds = roleDto.getMenuIds();
        menuIds.remove(0L);
        //1、更新角色权限之前要删除该角色之前的所有权限
        roleMenuDao.deleteRoleMenu(roleDto.getId());
        //2、判断该角色是否有赋予权限值，有就添加"
        if (!CollectionUtils.isEmpty(menuIds)) {
            roleMenuDao.save(roleDto.getId(), menuIds);
        }
        //3、更新角色表
        int countData = roleDao.update(roleDto);
        if(countData > 0){
            return Result.ok().message("更新成功");
        }else{
            return Result.error().message("更新失败");
        }
    }

    @Override
    public Result save(RoleDto roleDto) {
        //1、先保存角色"
        roleDao.saveRole(roleDto);
        List<Integer> menuIds = roleDto.getMenuIds();
        //移除0,permission id是从1开始
        //2、保存角色对应的所有权限
        if (!CollectionUtils.isEmpty(menuIds)) {
            roleMenuDao.save(roleDto.getId(), menuIds);
        }
        return Result.ok().message("插入成功");
    }

    @Override
    public Result<MyRole> delete(Integer id) {
        List<MyRoleUser> tbRoleUsers = roleUserDao.listAllRoleUserByRoleId(id);
        if(tbRoleUsers.size() <= 0){
            roleMenuDao.deleteRoleMenu(id);
            roleDao.delete(id);
            return Result.ok().message("删除成功");
        }
        return Result.error().message("该角色已经关联,无法删除");
    }

    @Override
    public Result<MyRole> getAllRoles() {
        return Result.ok().data(roleDao.getAllRoles());
    }
}
