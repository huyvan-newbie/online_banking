package com.example.online_banking.exception;

import com.example.online_banking.rest.model.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ResponseBody
public class DefaultExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionAdvice.class);
    public DefaultExceptionAdvice() {
    }

    protected HttpStatus getHttpStatus(String error) {
        try {
            return HttpStatus.valueOf(Integer.parseInt(error));
        } catch (Exception var4) {
            logger.error("getHttpStatus error ", var4);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception e, HttpServletRequest request) {
        return this.defHandler(HttpStatus.INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
    }

    protected ResponseEntity<Object> defHandler(HttpStatus status, String errorCode, String errorDesc) {
        return new ResponseEntity(ResponseData.failed(status, errorCode, errorDesc), status);
    }

    protected ResponseEntity<Object> defHandler(Object data, HttpStatus status, String errorCode, String errorDesc) {
        return new ResponseEntity(ResponseData.failed(data, status, errorCode, errorDesc), status);
    }

    protected Integer parseHttpStatus(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception var3) {
            logger.error("Err", var3);
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }
}
