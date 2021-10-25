package com.rhubarb.forum.exception;

/**
 * @author: sunxun
 * @date: 10/16/21 3:09 PM
 * @description:
 */
public enum CustomizedErrorResult implements ICustomizedErrorResult {

    USER_NOT_LOGIN(1001, "用户未登录，请登录后重试"),

    QUESTION_NOT_FOUND(2001,"你找的问题不存在或已被删除，要不换个试试？"),
    COMMENT_PARENT_NOT_FOUND(2002,"该问题或评论不存在，操作失败"),
    SERVER_ERROR(2003, "服务器出问题啦，请稍后再试！！"),
    PARENT_TYPE_NOT_FOUND(2004, "没有该评论类型，操作失败"),
    COMMENT_NOT_FOUND(2005, "回复的评论不存在或已被删除，要不换个试试？"),
    ;


    @Override
    public String getErrorMessage() {
        return this.message;
    }

    @Override
    public Integer getErrorCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizedErrorResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
