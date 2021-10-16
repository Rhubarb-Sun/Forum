package com.rhubarb.forum.dto;

import lombok.Data;

/**
 * @author: sunxun
 * @date: 10/16/21 6:07 PM
 * @description:
 */

@Data
public class CommentDTO {

    private Integer parentId;
    private Integer parentType;
    private String comment;
}
