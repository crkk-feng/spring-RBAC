package com.relaper.cr.springrbac.service;

import com.relaper.cr.springrbac.dto.RoleDto;
import com.relaper.cr.springrbac.entity.MyRole;
import com.relaper.cr.springrbac.utils.Result;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
public interface RoleService {

    Result<MyRole> getFuzzyRolesByPage(Integer startPosition, Integer limit,String queryName);

    MyRole getRoleById(Integer id);

    Result update(RoleDto roleDto);

    Result save(RoleDto roleDto);

    Result<MyRole> delete(Integer id);

    Result<MyRole> getAllRoles();
}
