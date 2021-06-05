package com.rks.spring.springsecuritytutorial.service.impl;

import com.rks.spring.springsecuritytutorial.modal.request.UserRequest;
import com.rks.spring.springsecuritytutorial.modal.response.UserResponse;
import com.rks.spring.springsecuritytutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl{

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse addUser(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        return userResponse;
    }

}
