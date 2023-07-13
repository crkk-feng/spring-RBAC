package com.relaper.cr.springrbac.log.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cr
 * @date 2023/7/11
 */
@Data
public class LogDto implements Serializable {
    private String userName;

    private String ip;

    private String params;

    private String description;

    private String method;

    private Long time;

    private String browser;

    private Date createTime;
}
