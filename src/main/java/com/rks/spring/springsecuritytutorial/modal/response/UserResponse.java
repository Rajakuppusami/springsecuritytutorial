package com.rks.spring.springsecuritytutorial.modal.response;

import java.io.Serializable;
import java.util.List;

public class UserResponse implements Serializable {
    private String userName;
    private List<String> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserResponse{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
