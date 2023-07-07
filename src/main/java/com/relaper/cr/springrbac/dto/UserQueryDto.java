package com.relaper.cr.springrbac.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Data
public class UserQueryDto implements Serializable {

    private String nickName;

    private String userName;
}
