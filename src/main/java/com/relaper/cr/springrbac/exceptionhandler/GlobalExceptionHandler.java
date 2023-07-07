package com.relaper.cr.springrbac.exceptionhandler;

import com.relaper.cr.springrbac.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Cr
 * @createTime 2023/5/8
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //指定处理什么异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("执行了全局异常");
    }
    //自定义异常
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
