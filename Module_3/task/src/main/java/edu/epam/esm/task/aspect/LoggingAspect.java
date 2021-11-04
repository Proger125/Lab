package edu.epam.esm.task.aspect;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("within(edu.epam.esm.task.service.impl.*)")
    public void certificateMethods(){}

    @AfterReturning(value = "certificateMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        logger.log(Level.INFO, joinPoint.getSignature() + " returns " + result.toString());
    }

    @AfterThrowing(value = "certificateMethods()", throwing = "ex")
    public void logAfterException(JoinPoint joinPoint, Throwable ex){
        logger.log(Level.ERROR, joinPoint.getSignature() + " throws " + ex.getMessage());
    }
}
