package com.haizhi.webinfo.config;

import com.haizhi.webinfo.auth.AllUserDetails;
import com.haizhi.webinfo.auth.Sha1AuthenticationProvider;
import com.haizhi.webinfo.auth.handler.LogoutHandler;
import com.haizhi.webinfo.constants.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.web.accept.ContentNegotiationStrategy;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by JuniFire on 2017/9/5.
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true
        //prePostEnabled = true
)
public class SpringConfigSecurity extends WebSecurityConfigurerAdapter {
/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("aaa").password("bbb").roles(Role.USER);

    }
*/

/**/
    @Bean
    public UserDetailsService getUserDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("aaa").password("bbb").roles(Role.USER).build());
        return manager;
    }

/*
    @Bean
    public Sha1AuthenticationProvider getSha1AuthenticationProvider() {
        return new Sha1AuthenticationProvider();
    }

    @Bean
    public AllUserDetails springDataUserDetailsService() {
        return new AllUserDetails();
    }
    */
/*
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    /**/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.and()
        .logout().invalidateHttpSession(true).logoutUrl("/logout").logoutSuccessHandler(new LogoutHandler())
                .and()
		.httpBasic();
    }


}
