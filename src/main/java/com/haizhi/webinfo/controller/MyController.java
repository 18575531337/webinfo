package com.haizhi.webinfo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haizhi on 2017/9/2.
 */
@RestController
public class MyController {

    @RequestMapping("/getTest")
    public String test(){
        return "Hello world";
    }
}
