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
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("pass"))
                .authorities("ROLE_USER");
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
