package com.rks.spring.springsecuritytutorial.exception.builder;

import com.rks.spring.springsecuritytutorial.exception.ApiError;
import com.rks.spring.springsecuritytutorial.exception.RestError;
import com.rks.spring.springsecuritytutorial.exception.RestException;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * This Builder class for APIError
 *
 * @author Rajakuppusami
 */
public class ApiErrorBuilder {

    private OffsetDateTime timestamp;
    private Integer status;
    private List<RestError> errorList;
    private String path;

    private ApiErrorBuilder() { }

    public static ApiErrorBuilder getInstance() {
        return new ApiErrorBuilder();
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public ApiErrorBuilder setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ApiErrorBuilder setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public List<RestError> getErrorList() {
        return errorList;
    }

    public ApiErrorBuilder setErrorList(List<RestError> errorList) {
        this.errorList = errorList;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ApiErrorBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public ApiError build() {
        ApiError apiError = new ApiError();
        apiError.setStatus(this.status);
        apiError.setTimestamp(this.timestamp);
        apiError.setErrorList(this.errorList);
        apiError.setPath(this.path);
        return apiError;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApiErrorBuilder{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", status=").append(status);
        sb.append(", errorList=").append(errorList);
        sb.append(", path='").append(path).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
