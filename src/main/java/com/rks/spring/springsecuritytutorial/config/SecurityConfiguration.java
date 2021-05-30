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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile(ApplicationConstant.SECURITY_PROFILE)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * This method is used to create in memory user
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass"))
                .authorities("ROLE_USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/actuator/**","/h2-console/**").permitAll()
                .antMatchers("/rest/**").authenticated().and().httpBasic().and().authorizeRequests()
                .antMatchers("/web/**").authenticated().and().formLogin().permitAll().and().logout().permitAll();
        http.csrf().ignoringAntMatchers("/h2-console/**", "/rest/**");
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
