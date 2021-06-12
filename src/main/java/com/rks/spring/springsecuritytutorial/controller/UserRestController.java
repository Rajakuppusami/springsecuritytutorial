package com.rks.spring.springsecuritytutorial.controller;

import com.rks.spring.springsecuritytutorial.constant.ApplicationConstant;
import com.rks.spring.springsecuritytutorial.modal.request.UpdateCredentialRequest;
import com.rks.spring.springsecuritytutorial.modal.request.UserRequest;
import com.rks.spring.springsecuritytutorial.modal.response.UserResponse;
import com.rks.spring.springsecuritytutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Profile(ApplicationConstant.DEFAULT_JDBC_SECURITY_PROFILE)
@RestController
@RequestMapping(ApplicationConstant.REST_PATH)
public class UserRestController {

    @Autowired
    @Qualifier("jdbcUserService")
    private UserService userService;

    /**
     * This API is used to create/register user in spring security database
     * @param userRequest
     * @return
     */
    @PostMapping(ApplicationConstant.REGISTER_USER_END_POINT)
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.addUser(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /**
     * This API is used to get user date from spring security database
     * @param username
     * @return
     */
    @GetMapping(ApplicationConstant.RETRIEVE_USER_END_POINT+"/{username}")
    public ResponseEntity<UserResponse> getUserDetail(@PathVariable("username") String username) {
        UserResponse userResponse = userService.retrieveUser(username);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /**
     * This API is used to delete/remove the user from spring security database
     * @param username
     * @return
     */
    @DeleteMapping(ApplicationConstant.DELETE_USER_END_POINT+"/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        return userService.deleteUser(username);
    }

    /**
     * This API is used to update the user credential from spring security database
     * @param updateCredentialRequest
     * @return
     */
    @PostMapping(ApplicationConstant.UPDATE_CREDENTIAL_END_POINT)
    public String updateCredential( @Valid @RequestBody UpdateCredentialRequest updateCredentialRequest) {
        return userService.changePassword(updateCredentialRequest);
    }

}
