package com.rhubarb.forum.controller;

import com.rhubarb.forum.dto.CommentDTO;
import com.rhubarb.forum.dto.ResultDTO;
import com.rhubarb.forum.exception.CustomizedErrorResult;
import com.rhubarb.forum.mapper.CommentMapper;
import com.rhubarb.forum.model.Comment;
import com.rhubarb.forum.model.User;
import com.rhubarb.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author: sunxun
 * @date: 10/16/21 6:06 PM
 * @description:
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) { // 自动解析json格式，封装到 CommentDTO 中

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizedErrorResult.USER_NOT_LOGIN);
        }

        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setParentId(commentDTO.getParentId());
        comment.setParentType(commentDTO.getParentType()); // 根据comment 的类型是评论还是回复。
        comment.setCommenterId(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0);

        commentService.insert(comment);
        return ResultDTO.okOf();

    }
}

/*
{
    "parentId": 2,
    "comment":"回复问题的内容",
    "parentType":1
}
 */
