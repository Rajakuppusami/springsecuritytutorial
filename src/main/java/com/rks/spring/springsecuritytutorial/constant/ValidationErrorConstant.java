package com.rks.spring.springsecuritytutorial.constant;

public class ValidationErrorConstant {

    public static final String USER_NAME_NOT_NULL = "username cannot be empty/null.";
    public static final String PASSWORD_NOT_NULL = "password cannot be empty/null.";
    public static final String PASSWORD_MIN_3_CHAR = "password should have minimum three characters.";
    public static final String ROLES_NOT_NULL = "role list cannot be empty/null.";

    private ValidationErrorConstant() {

    }
}
