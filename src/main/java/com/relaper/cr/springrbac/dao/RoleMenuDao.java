package com.relaper.cr.springrbac.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Mapper
public interface RoleMenuDao {
    @Delete("delete from my_role_menu where role_id = #{roleId}")
    int deleteRoleMenu(Integer id);


    void save(@Param("roleId")Integer id,@Param("menuIds") List<Integer> menuIds);
}
