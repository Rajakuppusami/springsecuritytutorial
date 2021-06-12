package com.rks.spring.springsecuritytutorial.exception;

import java.io.Serializable;

public class RestError implements Serializable {
    private String errorTitle;
    private String errorCode;
    private String errorMessage;
    private String fieldName;

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RestError{");
        sb.append("errorTitle='").append(errorTitle).append('\'');
        sb.append(", errorCode='").append(errorCode).append('\'');
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append(", fieldName='").append(fieldName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
