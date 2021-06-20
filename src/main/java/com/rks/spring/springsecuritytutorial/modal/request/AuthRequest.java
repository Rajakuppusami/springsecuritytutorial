package com.rks.spring.springsecuritytutorial.modal.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static com.rks.spring.springsecuritytutorial.constant.ValidationErrorConstant.PASSWORD_NOT_NULL;
import static com.rks.spring.springsecuritytutorial.constant.ValidationErrorConstant.USER_NAME_NOT_NULL;

public class AuthRequest implements Serializable {

    @NotBlank(message = USER_NAME_NOT_NULL)
    private String username;
    @NotBlank(message = PASSWORD_NOT_NULL)
    private String password;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthRequest{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
