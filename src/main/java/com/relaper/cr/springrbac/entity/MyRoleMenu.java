package com.relaper.cr.springrbac.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Data
public class MyRoleMenu implements Serializable {
    private static final long serialVersionUID = 8925514045582235875L;
    private Integer roleId;
    private Integer permissionId;
}
