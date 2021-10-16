package com.rhubarb.forum.exception;

/**
 * @author: sunxun
 * @date: 10/16/21 3:09 PM
 * @description:
 */
public enum CustomizedErrorCode implements ICustomizedErrorCode {

    QUESTION_NOT_FOUND("你找的问题不存在或已被删除，要不换个试试？");

    @Override
    public String getErrorMessage() {
        return this.message;
    }

    private String message;

    CustomizedErrorCode(String message) {
        this.message = message;
    }
}
