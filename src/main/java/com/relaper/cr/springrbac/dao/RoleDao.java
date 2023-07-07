package com.relaper.cr.springrbac.dao;

import com.relaper.cr.springrbac.dto.RoleDto;
import com.relaper.cr.springrbac.entity.MyRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Mapper
public interface RoleDao {
    //计算所有
    @Select("select count(*) from my_role t")
    Long countAllRoles();

    //分页模糊查询权限
    List<MyRole> getFuzzyRolesByPage(@Param("startPosition")Integer startPosition, @Param("limit")Integer limit,@Param("queryName") String queryName);

    @Select("select t.id,t.name,t.description,t.status,t.create_time,t.update_time from my_role t where t.id = #{id}")
    MyRole getRoleById(Integer id);

    int update(RoleDto roleDto);

    int saveRole(RoleDto roleDto);

    @Delete("delete from my_role where id = #{id}")
    int delete(Integer id);

    @Select("select t.id,t.name,t.description,t.status,t.create_time,t.update_time from my_role t")
    List<MyRole> getAllRoles();
}
