package com.relaper.cr.springrbac.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Cr
 * @createTime 2023/7/6
 */
@Data
public class MenuDto implements Serializable {
    private Integer id;
    private Integer parentId;
    private String checkArr = "0";
    private String title;
}
