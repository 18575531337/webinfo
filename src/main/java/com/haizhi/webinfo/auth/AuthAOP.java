package com.haizhi.webinfo.auth;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by haizhi on 2017/9/19.
 */
@Aspect
@Component
public class AuthAOP {

    @Pointcut("execution(* com.haizhi.webinfo.controller.MyController.*(..))")
    public void anyMethod(){

    }

    @Before("anyMethod()")
    public void before(){
        System.out.println("aspectJ before is running ...");
    }
}
