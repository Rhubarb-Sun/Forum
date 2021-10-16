package com.rhubarb.forum.exception;

import java.util.Objects;

/**
 * @author: sunxun
 * @date: 10/16/21 3:03 PM
 * @description:
 */
public class CustomizedException extends RuntimeException {

    private String message;

    public CustomizedException(String message) {
        this.message = message;
    }

    public CustomizedException(ICustomizedErrorCode errorCode) {
        this.message = errorCode.getErrorMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
