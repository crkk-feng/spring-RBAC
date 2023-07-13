package com.relaper.cr.springrbac.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cr
 * @createTime 2023/5/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends  RuntimeException {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String  msg;
}
