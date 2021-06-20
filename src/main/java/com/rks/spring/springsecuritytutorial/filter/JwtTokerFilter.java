package com.rks.spring.springsecuritytutorial.filter;

import com.rks.spring.springsecuritytutorial.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.rks.spring.springsecuritytutorial.constant.ApplicationConstant.BEARER;
import static com.rks.spring.springsecuritytutorial.constant.ApplicationConstant.JWT_IN_MEMORY_SECURITY;

@Profile(JWT_IN_MEMORY_SECURITY)
@Component
public class JwtTokerFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * This method is used to set authentication token only for valid JWT token which provide in http header with key as authorization
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        // header does have authorization or authorization does begin with  bearer the skip the below process
        if(StringUtils.isEmpty(header) || ! header.startsWith(BEARER)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        String token = header.substring(BEARER.length()-1);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // validate the JWT token if invalid the skip the below process
        if(!jwtTokenUtil.validateToken(token, userDetails)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);;
            return;
        }
        // JWT token is valid then authentication token to skip spring authentication process.
        if(Objects.nonNull(userDetails)){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                    userDetails.getPassword(),userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

}
