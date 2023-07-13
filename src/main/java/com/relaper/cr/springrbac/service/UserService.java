package com.relaper.cr.springrbac.service;

import com.relaper.cr.springrbac.dto.UserDto;
import com.relaper.cr.springrbac.dto.UserQueryDto;
import com.relaper.cr.springrbac.entity.MyUser;
import com.relaper.cr.springrbac.utils.Result;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
public interface UserService {

    /**
     * 返回用户列表
     * @param offectPosition
     * @param limit
     * @param userQueryDto
     * @return
     */
    Result<MyUser> getAllUsersByPage(Integer offectPosition, Integer limit, UserQueryDto userQueryDto);

    /**
     * 根据id返回用户信息
     * @param id
     * @return
     */
    MyUser getUserById(Integer id);

    /**
     * 通过手机查询用户
     * @param phone
     * @return
     */
    MyUser getUserByPhone(String phone);

    /**
     * 更新用户
     * @param userDto
     * @param roleId
     * @return
     */
    Result<MyUser> updateUser(UserDto userDto, Integer roleId);

    /**
     * 新建用户
     * @param userDto
     * @param roleId
     * @return
     */
    Result<MyUser> save(UserDto userDto, Integer roleId);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(Integer id);
    /**
     *  根据用户名查询用户
     * @param userName
     * @return
     */
    MyUser getUser(String userName);
}
