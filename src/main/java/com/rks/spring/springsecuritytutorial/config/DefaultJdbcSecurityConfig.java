package com.rks.spring.springsecuritytutorial.config;

import com.rks.spring.springsecuritytutorial.constant.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Profile(ApplicationConstant.DEFAULT_JDBC_SECURITY_PROFILE)
@EnableWebSecurity
@Configuration
public class DefaultJdbcSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /**
     * This method is used to create jdbc Authentication with default Spring Security Database Schema
     * create default user
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser(User.withUsername("user")
                        .password(passwordEncoder().encode("pass"))
                        .roles("USER"));
    }

    /**
     * This method is used to create default JbdcUserDetailsManager.
     * This instance controllers creating, retrieving, updating the userDetails.
     * @return jdbcUserDetailsManager
     */
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    /**
     * This method is used to register the filter request handle before reaching respective servlets.
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/actuator/**","/h2-console/**").permitAll()
                .antMatchers("/rest/**").authenticated().and().httpBasic().and().authorizeRequests()
                .antMatchers("/web/**").authenticated().and().formLogin().permitAll().and().logout().permitAll();
        httpSecurity.csrf().ignoringAntMatchers("/h2-console/**", "/rest/**");
        httpSecurity.headers().frameOptions().sameOrigin();
    }

    /**
     * This method is used to create password encoder bean
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
