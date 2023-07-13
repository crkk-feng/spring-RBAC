package com.relaper.cr.springrbac.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.relaper.cr.springrbac.dao.RoleUserDao;
import com.relaper.cr.springrbac.dao.UserDao;
import com.relaper.cr.springrbac.dto.UserDto;
import com.relaper.cr.springrbac.dto.UserQueryDto;
import com.relaper.cr.springrbac.entity.MyRoleUser;
import com.relaper.cr.springrbac.entity.MyUser;
import com.relaper.cr.springrbac.service.UserService;
import com.relaper.cr.springrbac.utils.Result;
import com.relaper.cr.springrbac.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public Result<MyUser> getAllUsersByPage(Integer offectPosition, Integer limit, UserQueryDto userQueryDto) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<MyUser> fuzzyUserByPage = userDao.getFuzzyUserByPage(userQueryDto);
        return Result.ok().count(page.getTotal()).data(fuzzyUserByPage).code(ResultCode.TABLE_SUCCESS);
    }


    @Override
    public MyUser getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public MyUser getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    @Override
    public Result<MyUser> updateUser(UserDto userDto, Integer roleId) {
        if (roleId!=null){
            userDao.updateUser(userDto);
            MyRoleUser myRoleUser = new MyRoleUser();
            myRoleUser.setUserId(userDto.getId());
            myRoleUser.setRoleId(roleId);
            if(roleUserDao.getRoleUserByUserId(userDto.getId())!=null){
                roleUserDao.updateMyRoleUser(myRoleUser);
            }else {
                roleUserDao.save(myRoleUser);
            }
            return Result.ok().message("更新成功");
        }else {
            return Result.error().message("更新失败");
        }
    }

    @Override
    public Result<MyUser> save(UserDto userDto, Integer roleId) {
        if(roleId!= null){
            userDao.save(userDto);
            MyRoleUser myRoleUser = new MyRoleUser();
            myRoleUser.setRoleId(roleId);
            myRoleUser.setUserId(userDto.getId().intValue());
            roleUserDao.save(myRoleUser);
            return Result.ok().message("添加成功，初始密码123456");
        }
        return Result.error().message("添加失败");
    }

    @Override
    public int deleteUser(Integer id) {
        roleUserDao.deleteRoleUserByUserId(id);
        return userDao.deleteUserById(id);
    }

    @Override
    public MyUser getUser(String userName) {
        return userDao.getUser(userName);
    }
}
