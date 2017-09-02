package com.haizhi.webinfo.controller;

import com.haizhi.webinfo.bean.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haizhi on 2017/9/2.
 */
@RestController
public class MyController {

    @Autowired
    A a;

    @RequestMapping("/getTest")
    public String test(){
        return "Hello world";
    }
}
