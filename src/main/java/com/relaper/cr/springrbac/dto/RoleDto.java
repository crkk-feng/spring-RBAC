package com.relaper.cr.springrbac.dto;

import com.relaper.cr.springrbac.entity.MyRole;

import java.util.List;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
public class RoleDto extends MyRole {
    private static final long serialVersionUID = -5784234789156935003L;

    private List<Integer> menuIds;

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }

}
