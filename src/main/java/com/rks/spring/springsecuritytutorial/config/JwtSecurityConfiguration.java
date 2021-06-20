package com.rks.spring.springsecuritytutorial.config;

import com.rks.spring.springsecuritytutorial.filter.JwtTokerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.rks.spring.springsecuritytutorial.constant.ApplicationConstant.JWT_IN_MEMORY_SECURITY;

@Configuration
@EnableWebSecurity
@Profile(JWT_IN_MEMORY_SECURITY)
public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired(required = true)
    private JwtTokerFilter jwtTokerFilter;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    /**
     * This method is used to create in memory user
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("pass"))
                .authorities("ROLE_USER");
    }

    /**
     * This method is used to customizing http security based on requirement
     * Private API need to authenticate via JWT token / in memory spring security user
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // configuration for h2 database console access
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().sameOrigin();
        httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll().and();

        // configuration for JWT authorization & spring in memory authentication
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        httpSecurity.authorizeRequests().antMatchers("/actuator/**", "/rest/api/public/**").permitAll()
                .antMatchers("/web/api/**").authenticated().and().httpBasic().and()
                .authorizeRequests().anyRequest().authenticated().and();
        httpSecurity.addFilterBefore(jwtTokerFilter, UsernamePasswordAuthenticationFilter.class);
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
