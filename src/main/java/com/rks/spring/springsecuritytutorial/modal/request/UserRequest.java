package com.rks.spring.springsecuritytutorial.modal.request;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

import static com.rks.spring.springsecuritytutorial.constant.ValidationErrorConstant.*;


public class UserRequest implements Serializable {

    @NotBlank(message = USER_NAME_NOT_NULL)
    private String username;

    @NotBlank(message = PASSWORD_NOT_NULL)
    @Size(min = 3, message = PASSWORD_MIN_3_CHAR)
    private String password;

    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRole(List<String> role) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(UserRequest.class.getName());
        sb.append('{');
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", roles='").append(roles).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
