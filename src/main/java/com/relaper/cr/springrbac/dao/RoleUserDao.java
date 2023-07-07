package com.relaper.cr.springrbac.dao;

import com.relaper.cr.springrbac.entity.MyRoleUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Mapper
public interface RoleUserDao {
    @Select("select * from my_role_user t where t.role_id = #{roleId}")
    List<MyRoleUser> listAllRoleUserByRoleId(Integer id);

    //通过用户id查询权限id
    @Select("select * from my_role_user t where t.user_id = #{userId}")
    List<MyRoleUser> getMyRoleUserByUserId(Integer userId);

    @Select("select * from my_role_user t where t.user_id = #{userId}")
    MyRoleUser getRoleUserByUserId(int intValue);

    int updateMyRoleUser(MyRoleUser myRoleUser);

    @Insert("insert into my_role_user(user_id, role_id) values(#{userId}, #{roleId})")
    int save(MyRoleUser myRoleUser);

    @Delete("delete from my_role_user where user_id = #{userId}")
    int deleteRoleUserByUserId(Integer id);
}
