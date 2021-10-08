package com.rhubarb.forum.model;

import lombok.Data;

/**
 * @author: sunxun
 * @date: 10/7/21 1:59 PM
 * @description:
 */
@Data
public class User {

    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
    private String bio;
}
