package com.relaper.cr.springrbac.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MyRole extends BaseEntity<Integer>{
    private static final long serialVersionUID = -6525908145032868837L;

    private String name;
    private String description;
    private Integer status;

    @Override
    public String toString() {
        return "MyRole{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
