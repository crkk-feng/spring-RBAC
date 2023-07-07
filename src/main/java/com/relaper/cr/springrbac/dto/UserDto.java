package com.relaper.cr.springrbac.dto;

import com.relaper.cr.springrbac.entity.BaseEntity;
import com.relaper.cr.springrbac.entity.MyUser;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Data
public class UserDto extends BaseEntity<Integer> {
    private String userName;
    private String nickName;
    private String password;
    private String phone;
    private String email;
    private Integer status;
    private Integer roleId;
}
