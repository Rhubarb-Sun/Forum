package com.rhubarb.forum.dto;

import com.rhubarb.forum.model.User;
import lombok.Data;

/**
 * @author: sunxun
 * @date: 10/8/21 12:57 PM
 * @description: index首页需要获取User的头像。而Question类没有User对象，由于它与数据库对应，不适宜加入User字段。
 */
@Data
public class QuestionDTO {

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
    private User user;
}
