package com.relaper.cr.springrbac.log.aspect;

import com.relaper.cr.springrbac.log.entity.MyLog;
import com.relaper.cr.springrbac.log.service.MyLogService;
import com.relaper.cr.springrbac.log.utils.LogUtils;
import com.relaper.cr.springrbac.log.utils.RequestHolder;
import com.relaper.cr.springrbac.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Cr
 * @date 2023/7/11
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    private MyLogService logService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();
    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.relaper.cr.springrbac.log.aop.MyLog)")
    public void logPoinCut() {
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPoinCut()")
    public Object saveSysLog(ProceedingJoinPoint joinPoint)throws Throwable{
        Object result;
        //记录方法的执行时间
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        //定义日志类型
        MyLog log = new MyLog("INFO",System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        logService.save(SecurityUtils.getCurrentUsername(), LogUtils.getBrowser(request), LogUtils.getIp(request),joinPoint, log);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPoinCut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        MyLog log = new MyLog("ERROR",System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        log.setExceptionDetail(LogUtils.getStackTrace(e));
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        logService.save(SecurityUtils.getCurrentUsername(), LogUtils.getBrowser(request), LogUtils.getIp(request), (ProceedingJoinPoint)joinPoint, log);
    }
}