package com.rks.spring.springsecuritytutorial.controller;

import com.rks.spring.springsecuritytutorial.exception.RestError;
import com.rks.spring.springsecuritytutorial.exception.RestErrorRegister;
import com.rks.spring.springsecuritytutorial.exception.builder.RestExceptionBuilder;
import com.rks.spring.springsecuritytutorial.modal.request.AuthRequest;
import com.rks.spring.springsecuritytutorial.modal.response.JwtTokenResponse;
import com.rks.spring.springsecuritytutorial.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static com.rks.spring.springsecuritytutorial.constant.ApplicationConstant.*;

@RestController
@RequestMapping(path = REST_PUBLIC_PATH)
@Profile(JWT_IN_MEMORY_SECURITY)
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * This method is used to provide the JWT token only for valid users
     * @param authRequest
     * @return
     */
    @PostMapping(path = AUTHENTICATION_PATH)
    public ResponseEntity<JwtTokenResponse> authenticate(@RequestBody @Valid AuthRequest authRequest) {
        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword());
            authenticationManager.authenticate(authenticationToken);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return new ResponseEntity<JwtTokenResponse>(new JwtTokenResponse(token), HttpStatus.OK);
        }catch (BadCredentialsException exception){
            List< RestError > errorList = Collections.singletonList(RestErrorRegister.buildRestError(RestErrorRegister.AUTHENTICATION_FAILED));
            throw RestExceptionBuilder
                    .getInstance()
                    .setTimestamp(OffsetDateTime.now())
                    .setErrorList(errorList)
                    .build();
        }
    }
}
