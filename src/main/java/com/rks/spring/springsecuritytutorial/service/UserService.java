package com.rks.spring.springsecuritytutorial.service;

import com.rks.spring.springsecuritytutorial.modal.request.UserRequest;
import com.rks.spring.springsecuritytutorial.modal.response.UserResponse;

public interface UserService {
    UserResponse addUser(UserRequest userRequest);
    UserResponse retrieveUser(String username);
    String deleteUser(String username);

}
