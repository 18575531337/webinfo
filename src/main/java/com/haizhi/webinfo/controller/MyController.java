package com.haizhi.webinfo.controller;

import com.haizhi.webinfo.bean.A;
import com.haizhi.webinfo.response.RespData;
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
    public String getTest(){
        return "海致";
    }

    @RequestMapping("/getT")
    public RespData getT(){
        return RespData.SUCCESS().setData("你好");
    }
}
