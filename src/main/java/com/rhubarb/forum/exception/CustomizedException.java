package com.rhubarb.forum.exception;

/**
 * @author: sunxun
 * @date: 10/16/21 3:03 PM
 * @description:
 */
public class CustomizedException extends RuntimeException {

    private Integer code;
    private String message;

    public CustomizedException(ICustomizedErrorResult errorResult) {
        this.code = errorResult.getErrorCode();
        this.message = errorResult.getErrorMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
