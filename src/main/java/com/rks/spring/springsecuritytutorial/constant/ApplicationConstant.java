package com.rks.spring.springsecuritytutorial.constant;

public class ApplicationConstant {

//    profile constants
    public static final String SECURITY_PROFILE = "security";
    public static final String NON_SECURITY_PROFILE = "non_security";
    public static final String DEFAULT_JDBC_SECURITY_PROFILE = "default_jdbc_security";
    public static final String JWT_IN_MEMORY_SECURITY = "jwt_in_memory_security";

//    url end point constants
    public static final String REST_PATH = "/rest/api";
    public static final String HELLO_WORLD_END_POINT = "/hello-world";
    public static final String REGISTER_USER_END_POINT = "/register-user";
    public static final String RETRIEVE_USER_END_POINT = "/get-user";
    public static final String DELETE_USER_END_POINT = "/delete-user";
    public static final String UPDATE_CREDENTIAL_END_POINT = "/update-credential";
//    JWT implementation
    public static final String REST_PUBLIC_PATH = "/rest/api/public";
    public static final String AUTHENTICATION_PATH = "/authenticate";

    public static final String WEB_PATH = "/web/api";
    public static final String REGISTER_PAGE_URL = "/register";
    public static final String HOME_PAGE_URL = "/home";

    public static final String BEARER = "Bearer ";

    private ApplicationConstant() {
    }

}
