package com.haizhi.webinfo.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by haizhi on 2017/9/2.
 */
@Component
public class User {

    @PostConstruct
    public void init(){
        System.out.println("A init");
    }
}
