package com.haizhi.webinfo.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Created by haizhi on 2017/9/2.
 */
@ComponentScan(basePackages = "com.haizhi.webinfo")
@EnableAspectJAutoProxy
@Configuration
@Import({
        SpringConfigSecurity.class,
        SpringConfigMVC.class
})
public class SpringConfig  {

}
