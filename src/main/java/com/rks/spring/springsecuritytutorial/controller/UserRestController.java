package com.rks.spring.springsecuritytutorial.controller;

import com.rks.spring.springsecuritytutorial.constant.ApplicationConstant;
import com.rks.spring.springsecuritytutorial.modal.request.UserRequest;
import com.rks.spring.springsecuritytutorial.modal.response.UserResponse;
import com.rks.spring.springsecuritytutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile(ApplicationConstant.DEFAULT_JDBC_SECURITY_PROFILE)
@RestController
@RequestMapping(ApplicationConstant.REST_PATH)
public class UserRestController {

    @Autowired
    @Qualifier("jdbcUserService")
    private UserService userService;

    @PostMapping(ApplicationConstant.REGISTER_USER_END_POINT)
    public UserResponse registerUser(@RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @PostMapping(ApplicationConstant.RETRIEVE_USER_END_POINT)
    public UserResponse getUserDetail(@RequestBody String username) {
        return userService.retrieveUser(username);
    }

    @PostMapping(ApplicationConstant.DELETE_USER_END_POINT)
    public String deleteUser(@RequestBody String username) {
        return userService.deleteUser(username);
    }

}
