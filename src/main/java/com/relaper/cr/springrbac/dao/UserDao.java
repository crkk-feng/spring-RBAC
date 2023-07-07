package com.relaper.cr.springrbac.dao;

import com.relaper.cr.springrbac.dto.UserDto;
import com.relaper.cr.springrbac.dto.UserQueryDto;
import com.relaper.cr.springrbac.entity.MyUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Mapper
public interface UserDao {

    //分页返回所有用户
     List<MyUser> getFuzzyUserByPage(@Param("startPosition")Integer startPosition,@Param("limit")Integer limit, @Param("userQueryDto") UserQueryDto userQueryDto);

    //计算所有用户数量
    // @Select("select count(*) from My_user")
    // Long countAllUser();

    @Select("select t.id,t.user_name,t.password,t.nick_name,t.phone,t.email,t.status,t.create_time,t.update_time from my_user t where t.id = #{id}")
    MyUser getUserById(Integer id);

    @Select("select t.id,t.user_name,t.password,t.nick_name,t.phone,t.email,t.status,t.create_time,t.update_time from my_user t where t.phone = #{phone}")
    MyUser getUserByPhone(String phone);

    int updateUser(UserDto userDto);

    //插入用户
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into my_user(user_name, password, nick_name, phone, email, status, create_time, update_time) values(#{userName}, #{password}, #{nickName}, #{phone}, #{email}, #{status}, now(), now())")
    int save(UserDto userDto);

    @Delete("delete from my_user where id = #{userId}")
    int deleteUserById(Integer id);
}
