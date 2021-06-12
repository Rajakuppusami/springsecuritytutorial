package com.rks.spring.springsecuritytutorial.exception.builder;

import com.rks.spring.springsecuritytutorial.exception.RestError;
import com.rks.spring.springsecuritytutorial.exception.RestException;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * This builder class for RestException
 * @author Rajakuppusami
 */
public class RestExceptionBuilder {
    private OffsetDateTime timestamp;
    private Integer status;
    private List<RestError> errorList;
    private String path;

    private RestExceptionBuilder() { }

    public static RestExceptionBuilder getInstance(){
        return new RestExceptionBuilder();
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public RestExceptionBuilder setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public RestExceptionBuilder setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public List<RestError> getErrorList() {
        return errorList;
    }

    public RestExceptionBuilder setErrorList(List<RestError> errorList) {
        this.errorList = errorList;
        return this;
    }

    public String getPath() {
        return path;
    }

    public RestExceptionBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public RestException build() {
        RestException exception = new RestException();
        exception.setStatus(this.status);
        exception.setTimestamp(this.timestamp);
        exception.setErrorList(this.errorList);
        exception.setPath(this.path);
        return exception;
    }
}
