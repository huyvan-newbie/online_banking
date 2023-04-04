package com.example.online_banking.rest.model;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {

    public static final String ACCOUNT_NOT_EXIST = "001";
    public static final String ACCOUNT_BALANCE_INVALID = "002";
    public static final String NO_BANK = "003";
    public static final String NO_AMOUNT = "004";
    public static final String NO_LOANS_PACKAGE = "005";
    public static final String NO_SAVING_PACKAGE = "006";
    public static final String USER_NOT_EXIST = "007";
    public static final String TOO_MUCH_MONEY = "008";



    public static final Map<String, String> errorCodeMap = new HashMap<>();

    static {
        errorCodeMap.put(ACCOUNT_NOT_EXIST, "Account is not exist");
        errorCodeMap.put(ACCOUNT_BALANCE_INVALID, "Account balance is insufficient");
        errorCodeMap.put(NO_BANK, "Please choose bank");
        errorCodeMap.put(NO_AMOUNT, "Please insert amount money");
        errorCodeMap.put(NO_LOANS_PACKAGE, "Please choose loans package, please");
        errorCodeMap.put(NO_SAVING_PACKAGE, "Please choose saving package, please");
        errorCodeMap.put(USER_NOT_EXIST, "User not exist");
        errorCodeMap.put(TOO_MUCH_MONEY, "You must deposit less than 1.000.000VND");


    }

    public static String getErrorMessage(String errorCode) {
        return errorCodeMap.get(errorCode);
    }
}
