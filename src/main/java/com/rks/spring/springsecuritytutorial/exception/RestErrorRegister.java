package com.rks.spring.springsecuritytutorial.exception;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RestErrorRegister {
    API_REQUEST_VALIDATION_FAILED("API_REQUEST_VALIDATION_FAILED", "1", null, null),
    USER_NOT_FOUND("USER_NOT_FOUND", "2", "username is invalid", "username"),
    USER_ALREADY_EXIST("USER_ALREADY_EXIST","3","username is already exist, please try another username","username"),
    OLD_PASSWORD_INCORRECT("OLD_PASSWORD_INCORRECT","4","old password doesn't matched please enter correct password", "oldPassword"),
    AUTHENTICATION_FAILED("AUTHENTICATION_FAILED", "5", "username/password incorrect", null);

    private String errorTitle;
    private String errorCode;
    private String errorMessage;
    private String fieldName;

    RestErrorRegister(final String errorTitle, final String errorCode, final String errorMessage, String fieldName) {
        this.errorTitle = errorTitle;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.fieldName = fieldName;
    }

    public static List<RestError> buildRestErrorList(RestErrorRegister... errorRegisters) {
        return Arrays.stream(errorRegisters).map(RestErrorRegister::buildRestError).collect(Collectors.toList());
    }

    public static RestError buildRestError(RestErrorRegister errorRegister) {
        RestError error = new RestError();
        error.setErrorCode(errorRegister.errorCode);
        error.setErrorTitle(errorRegister.errorTitle);
        error.setErrorMessage(errorRegister.errorMessage);
        error.setFieldName(errorRegister.fieldName);
        return error;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }
}
