package com.haizhi.webinfo.config;

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
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.accept.ContentNegotiationStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JuniFire on 2017/9/5.
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        try {
            http.regexMatcher("/*");

            http.addFilterBefore(null,SecurityContextPersistenceFilter.class);

            http.securityContext().securityContextRepository(new SecurityContextRepository() {
                @Override
                public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
                    return null;
                }

                @Override
                public void saveContext(SecurityContext context, HttpServletRequest request,
                                        HttpServletResponse response) {

                }

                @Override
                public boolean containsContext(HttpServletRequest request) {
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    public void init(WebSecurity web) throws Exception {
        super.init(web);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) {
        super.setApplicationContext(context);
    }

    @Override
    public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
        super.setTrustResolver(trustResolver);
    }

    @Override
    public void setContentNegotationStrategy(ContentNegotiationStrategy contentNegotiationStrategy) {
        super.setContentNegotationStrategy(contentNegotiationStrategy);
    }

    @Override
    public void setObjectPostProcessor(ObjectPostProcessor<Object> objectPostProcessor) {
        super.setObjectPostProcessor(objectPostProcessor);
    }

    @Override
    public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
        super.setAuthenticationConfiguration(authenticationConfiguration);
    }
}
