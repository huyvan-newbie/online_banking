package com.example.online_banking.exception;

import com.example.online_banking.rest.model.ErrorCode;
import com.example.online_banking.utils.CommonUtils;
import lombok.Data;

@Data
public class DataInvalidException extends Exception {
    private String errorCode;
    private String errorDesc;

    public DataInvalidException(String errorCode) {
        this.errorCode = errorCode;
        this.errorDesc = ErrorCode.getErrorMessage(errorCode);
    }

    public DataInvalidException(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = CommonUtils.isNull(errorDesc) ? errorDesc : ErrorCode.getErrorMessage(errorCode);
    }
}
