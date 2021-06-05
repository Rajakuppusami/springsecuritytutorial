package com.rks.spring.springsecuritytutorial.service.impl;

import com.rks.spring.springsecuritytutorial.constant.ApplicationConstant;
import com.rks.spring.springsecuritytutorial.modal.request.UserRequest;
import com.rks.spring.springsecuritytutorial.modal.response.UserResponse;
import com.rks.spring.springsecuritytutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Profile(ApplicationConstant.DEFAULT_JDBC_SECURITY_PROFILE)
@Service("jdbcUserService")
public class JdbcUserServiceImpl implements UserService {

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

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
            throw new UsernameNotFoundException("Username "+ userRequest.getUsername() +" not found");
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
        if(!jdbcUserDetailsManager.userExists(username)) {
            throw new UsernameNotFoundException("Username "+username+" not found");
        }
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
        if(!jdbcUserDetailsManager.userExists(username)) {
            throw new UsernameNotFoundException("Username "+username+" not found");
        }
        jdbcUserDetailsManager.deleteUser(username);
        return "Successfully removed user :)";
    }

}
