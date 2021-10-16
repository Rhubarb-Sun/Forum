package com.rhubarb.forum.controller;

import com.rhubarb.forum.dto.CommentDTO;
import com.rhubarb.forum.mapper.CommentMapper;
import com.rhubarb.forum.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sunxun
 * @date: 10/16/21 6:06 PM
 * @description:
 */
@RestController
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @PostMapping("/comment")
    public Object post(@RequestBody CommentDTO commentDTO) {

        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setParentId(commentDTO.getParentId());
        comment.setParentType(commentDTO.getParentType());
        comment.setCommenterId(1);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0);

        commentMapper.insert(comment);
        return null;

    }
}

/*
{
    "parentId": 2,
    "comment":"回复问题的内容",
    "parentType":1
}
 */
