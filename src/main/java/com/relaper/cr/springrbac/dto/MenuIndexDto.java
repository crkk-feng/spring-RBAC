package com.relaper.cr.springrbac.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Cr
 * @date 2023/7/6
 */
@Data
public class MenuIndexDto implements Serializable {
    private Integer id;
    private Integer parentId;
    private String title;
    private String icon;
    private Integer type;
    private String href;
    private String permission;
    private List<MenuIndexDto> children;
}

