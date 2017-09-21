package com.haizhi.webinfo.auth;

import com.haizhi.webinfo.constants.Role;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

/**
 * Created by haizhi on 2017/9/19.
 */
@Aspect
@Component
public class AuthAOP {

    @Pointcut("execution(* com.haizhi.webinfo.controller.UserController.getTest(..))")
    public void anyMethod(){

    }


    @Before("anyMethod()")
    public void before(){
        System.out.println("aspectJ before is running ...");
    }
}
