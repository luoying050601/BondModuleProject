package com.cscec8b.bond.module.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一打印controller的请求参数
 */
@Aspect
@Component
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    HttpServletRequest httpServletRequest;

    /**
     * 抽取切入点
     */
    @Pointcut("execution(public * com.cscec8b.bond.module.controller.*.*(..))")
    public void pointCut() {
    }

    /**
     * 通过joinPoint获取入参、方法名等信息
     *
     * @param joinPoint
     */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        try {
            String method = joinPoint.getSignature().getDeclaringTypeName() +
                    "." + joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            logger.info("<请求URL>：" + httpServletRequest.getRequestURI());
            logger.info("<请求方法>：" + method);
            logger.info("<请求参数args>：" + JSON.toJSONString(args[0]));

        } catch (Exception e) {
            logger.info("<日志警告>：请求参数无法打印，Exception：" + e.getMessage());
        }

    }

}
