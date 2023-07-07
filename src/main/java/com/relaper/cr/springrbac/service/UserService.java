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
    Result<MyUser> getAllUsersByPage(Integer startPosition, Integer limit, UserQueryDto userQueryDto);

    MyUser getUserById(Integer id);

    MyUser getUserByPhone(String phone);

    Result<MyUser> updateUser(UserDto userDto, Integer roleId);

    Result<MyUser> save(UserDto userDto, Integer roleId);

    int deleteUser(Integer id);
}
