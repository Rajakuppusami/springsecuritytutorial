package com.rks.spring.springsecuritytutorial.exception;

import com.rks.spring.springsecuritytutorial.exception.builder.ApiErrorBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This method is used to handle the RestException and customized to APIError object
     * @param request
     * @param error
     * @return
     */
    @ExceptionHandler(RestException.class)
    public ResponseEntity<ApiError> handleRestError(@NotNull HttpServletRequest request, @NotNull RestException error) {
        ApiError apiError = ApiErrorBuilder.getInstance()
                .setErrorList(error.getErrorList())
                .setTimestamp(error.getTimestamp())
                .setPath(request.getRequestURI())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is used to handle the @valid exception and customized to APIError object
     * @param error
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException error, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<RestError> errorList = error.getBindingResult().getFieldErrors().stream().map(this::buildRestError).collect(Collectors.toList());
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        ApiError apiError = ApiErrorBuilder.getInstance()
                .setErrorList(errorList)
                .setTimestamp(OffsetDateTime.now())
                .setPath(servletWebRequest.getRequest().getRequestURI())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
    }

    public RestError buildRestError(FieldError fieldError) {
        RestError error = new RestError();
        error.setErrorMessage(fieldError.getDefaultMessage());
        error.setFieldName(fieldError.getField());
        error.setErrorTitle(RestErrorRegister.API_REQUEST_VALIDATION_FAILED.getErrorTitle());
        error.setErrorCode(RestErrorRegister.API_REQUEST_VALIDATION_FAILED.getErrorCode());
        return error;
    }
}
