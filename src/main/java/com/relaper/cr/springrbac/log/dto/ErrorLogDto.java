package com.relaper.cr.springrbac.log.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cr
 * @date 2023/7/11
 */
@Data
public class ErrorLogDto implements Serializable {
    private String userName;

    private String ip;

    private String params;

    private String description;

    private String exceptionDetail;

    private String method;

    private String browser;

    private Date createTime;
}
