package com.relaper.cr.springrbac.service.impl;

import com.relaper.cr.springrbac.dao.RoleUserDao;
import com.relaper.cr.springrbac.entity.MyRoleUser;
import com.relaper.cr.springrbac.service.RoleUserService;
import com.relaper.cr.springrbac.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {
    @Autowired
    private RoleUserDao roleUserDao;
    @Override
    public Result getMyRoleUserByUserId(Integer userId) {
        List<MyRoleUser> tbRoleUser = roleUserDao.getMyRoleUserByUserId(userId);
        if(tbRoleUser != null){
            return Result.ok().data(tbRoleUser);
        }else{
            return Result.error();
        }
    }
}
