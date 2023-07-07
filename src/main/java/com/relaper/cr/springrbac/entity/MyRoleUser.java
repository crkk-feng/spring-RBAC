package com.relaper.cr.springrbac.entity;

import lombok.Data;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Data
public class MyRoleUser {
    private static final long serialVersionUID = 8545514045582235838L;
    private Integer userId;
    private Integer roleId;
}
