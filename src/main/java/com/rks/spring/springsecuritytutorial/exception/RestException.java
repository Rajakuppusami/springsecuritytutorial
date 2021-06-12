package com.rks.spring.springsecuritytutorial.exception;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public class RestException extends RuntimeException implements Serializable {

    private OffsetDateTime timestamp;
    private Integer status;
    private List<RestError> errorList;
    private String path;

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<RestError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<RestError> errorList) {
        this.errorList = errorList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RestException{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", status=").append(status);
        sb.append(", restErrorList=").append(errorList);
        sb.append(", path='").append(path).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
