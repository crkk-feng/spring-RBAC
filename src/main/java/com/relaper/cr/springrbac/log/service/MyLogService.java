package com.relaper.cr.springrbac.log.service;

import com.relaper.cr.springrbac.log.dto.ErrorLogDto;
import com.relaper.cr.springrbac.log.dto.LogDto;
import com.relaper.cr.springrbac.log.dto.LogQuery;
import com.relaper.cr.springrbac.log.entity.MyLog;
import com.relaper.cr.springrbac.utils.Result;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Cr
 * @date 2023/7/11
 */
public interface MyLogService {

    /**
     * 分页模糊查询用户日志
     * @param startPosition 起始页
     * @param limit 每页多少条数据
     * @param logQuery //查询条件
     * @return
     */
    Result<LogDto> getFuzzyInfoLogByPage(Integer startPosition, Integer limit, LogQuery logQuery);

    /**
     * 分页模糊查询错误日志
     * @param startPosition 起始页
     * @param limit 每页多少条数据
     * @param logQuery //查询条件
     * @return
     */
    Result<ErrorLogDto> getFuzzyErrorLogByPage(Integer startPosition, Integer limit, LogQuery logQuery);

    /**
     * 保存日志
     * @param userName 用户名
     * @param browser 浏览器
     * @param ip IP地址
     * @param joinPoint 切入点
     * @param log 日志信息
     */
    void save(String userName, String browser, String ip, ProceedingJoinPoint joinPoint, MyLog log);

    /**
     * 删除所有错误日志
     */
    void delAllByError();

    /**
     * 删除所有INFO日志
     */
    void delAllByInfo();
}
