package com.rhubarb.forum.model;

import lombok.Data;

/**
 * @author: sunxun
 * @date: 10/8/21 1:31 AM
 * @description:
 */

@Data
public class Question {

    private Integer id;
    private String title;
    private String content;
    private Integer creatorId;
    private Integer readCount;
    private Integer likeCount;
    private Integer commentCount;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
}
