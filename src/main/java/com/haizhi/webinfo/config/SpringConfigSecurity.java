package com.haizhi.webinfo.config;

import com.haizhi.webinfo.auth.handler.AccountLogoutHandler;
import com.haizhi.webinfo.constants.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        .logout().invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessHandler(new AccountLogoutHandler())
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response,
                                       Authentication authentication) {
                        System.out.println("log out .......");
                    }
                })
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .and()
		.httpBasic();
    }


}
