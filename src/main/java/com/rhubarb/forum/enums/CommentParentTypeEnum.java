package com.rhubarb.forum.enums;

/**
 * @author: sunxun
 * @date: 10/22/21 9:55 PM
 * @description:
 */
public enum CommentParentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public static boolean has(Integer parentType) {
        CommentParentTypeEnum[] values = CommentParentTypeEnum.values();
        for (CommentParentTypeEnum value : values) {
            if (value.getType() == parentType) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentParentTypeEnum(Integer type) {
        this.type = type;
    }
}
