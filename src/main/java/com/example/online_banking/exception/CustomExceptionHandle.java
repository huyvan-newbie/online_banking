package com.example.online_banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandle extends DefaultExceptionAdvice {

    @ExceptionHandler(DataInvalidException.class)
    public ResponseEntity<Object> handleException(DataInvalidException e) {
        return this.defHandler(this.getHttpStatus(String.valueOf(HttpStatus.BAD_REQUEST.value())), e.getErrorCode(), e.getErrorDesc());
    }
}