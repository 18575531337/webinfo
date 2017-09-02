package com.haizhi.webinfo.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by haizhi on 2017/9/1.
 */
@WebListener
public class MyListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("requestDestroyed is running .....");
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("requestInitialized is running .....");
    }
}
