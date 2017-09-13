package com.haizhi.webinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Created by haizhi on 2017/9/2.
 */
@ComponentScan(basePackages = "com.haizhi.webinfo")
@Configuration
@Import({
        SpringConfigSecurity.class,
        SpringConfigMVC.class
})
public class SpringConfig  {

}
