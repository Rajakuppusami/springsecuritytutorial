package com.rks.spring.springsecuritytutorial.modal.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.rks.spring.springsecuritytutorial.constant.ValidationErrorConstant.*;

public class UpdateCredentialRequest implements Serializable {

    @NotBlank(message = USER_NAME_NOT_NULL)
    private String username;

    @NotBlank(message = PASSWORD_NOT_NULL)
    @Size(min = 3, message = PASSWORD_MIN_3_CHAR)
    private String oldPassword;

    @NotBlank(message = PASSWORD_NOT_NULL)
    @Size(min = 3, message = PASSWORD_MIN_3_CHAR)
    private String newPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateCredentialRequest{");
        sb.append("username='").append(username).append('\'');
        sb.append(", oldPassword='").append(oldPassword).append('\'');
        sb.append(", newPassword='").append(newPassword).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
