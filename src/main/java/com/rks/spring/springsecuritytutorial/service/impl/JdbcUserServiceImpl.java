package com.rks.spring.springsecuritytutorial.service.impl;

import com.rks.spring.springsecuritytutorial.constant.ApplicationConstant;
import com.rks.spring.springsecuritytutorial.exception.RestError;
import com.rks.spring.springsecuritytutorial.exception.RestErrorRegister;
import com.rks.spring.springsecuritytutorial.exception.builder.RestExceptionBuilder;
import com.rks.spring.springsecuritytutorial.modal.request.UpdateCredentialRequest;
import com.rks.spring.springsecuritytutorial.modal.request.UserRequest;
import com.rks.spring.springsecuritytutorial.modal.response.UserResponse;
import com.rks.spring.springsecuritytutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;


import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Profile(ApplicationConstant.DEFAULT_JDBC_SECURITY_PROFILE)
@Service("jdbcUserService")
public class JdbcUserServiceImpl implements UserService {

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * This method is used to create the user based on username using jdbcUserDetailsManager
     * @param userRequest
     * @return
     */
    @Override
    public UserResponse addUser(UserRequest userRequest) {
        if(jdbcUserDetailsManager.userExists(userRequest.getUsername())){
            List<RestError> errorList = Collections.singletonList(RestErrorRegister.buildRestError(RestErrorRegister.USER_ALREADY_EXIST));
            throw RestExceptionBuilder
                    .getInstance()
                    .setTimestamp(OffsetDateTime.now())
                    .setErrorList(errorList)
                    .build();
        }
        List<GrantedAuthority> grantedAuthorityList = userRequest.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        User user = new User(userRequest.getUsername(), passwordEncoder.encode(userRequest.getPassword()), grantedAuthorityList);
        jdbcUserDetailsManager.createUser(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(user.getUsername());
        userResponse.setRoles(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return userResponse;
    }

    /**
     * This method is used to retrieve the user based on username using jdbcUserDetailsManager
     * @param username
     * @return
     */
    @Override
    public UserResponse retrieveUser(String username) {
        userNotPresentThenThrowError(username);
        UserDetails userDetails = jdbcUserDetailsManager.loadUserByUsername(username);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(userDetails.getUsername());
        userResponse.setRoles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return userResponse;
    }

    /**
     * This method is used to delete the user based on username using jdbcUserDetailsManager
     * @param username
     * @return
     */
    @Override
    public String deleteUser(String username){
        userNotPresentThenThrowError(username);
        jdbcUserDetailsManager.deleteUser(username);
        return "Successfully removed user :)";
    }

    /**
     * This method is used to update the credential of given user if their password matches.
     * @param updateCredentialRequest
     * @return
     */
    @Override
    public String changePassword(UpdateCredentialRequest updateCredentialRequest) {
        userNotPresentThenThrowError(updateCredentialRequest.getUsername());
        UserDetails userDetails = jdbcUserDetailsManager.loadUserByUsername(updateCredentialRequest.getUsername());
        if(!passwordEncoder.matches(updateCredentialRequest.getOldPassword(),userDetails.getPassword())) {
            List<RestError> errorList = Collections.singletonList(RestErrorRegister.buildRestError(RestErrorRegister.OLD_PASSWORD_INCORRECT));
            throw RestExceptionBuilder
                    .getInstance()
                    .setTimestamp(OffsetDateTime.now())
                    .setErrorList(errorList)
                    .build();
        }
        String[] roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);
        jdbcUserDetailsManager.updateUser(User
                .withUsername(userDetails.getUsername())
                .password(passwordEncoder.encode(updateCredentialRequest.getNewPassword()))
                .roles(roles)
                .build());

        return "Successfully updated credential :)";
    }

    /**
     * This method is used to check if user not present then throw user not found exception
     * @param username
     */
    private void userNotPresentThenThrowError(String username) {
        if (!jdbcUserDetailsManager.userExists(username)) {
            List<RestError> errorList = Collections.singletonList(RestErrorRegister.buildRestError(RestErrorRegister.USER_NOT_FOUND));
            throw RestExceptionBuilder
                    .getInstance()
                    .setTimestamp(OffsetDateTime.now())
                    .setErrorList(errorList)
                    .build();
        }
    }

}
