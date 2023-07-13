package com.relaper.cr.springrbac.service;

import com.relaper.cr.springrbac.utils.Result;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
public interface RoleUserService {

    /**
     * 返回用户拥有的角色
     * @param userId
     * @return
     */
    Result getMyRoleUserByUserId(Integer userId);

}
