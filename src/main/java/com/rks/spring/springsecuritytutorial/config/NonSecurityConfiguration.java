package com.rks.spring.springsecuritytutorial.config;

import com.rks.spring.springsecuritytutorial.constant.ApplicationConstant;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile(ApplicationConstant.NON_SECURITY_PROFILE)
@Configuration
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class NonSecurityConfiguration {

}
