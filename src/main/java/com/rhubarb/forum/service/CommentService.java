package com.rhubarb.forum.service;

import com.rhubarb.forum.enums.CommentParentTypeEnum;
import com.rhubarb.forum.exception.CustomizedErrorResult;
import com.rhubarb.forum.exception.CustomizedException;
import com.rhubarb.forum.mapper.CommentMapper;
import com.rhubarb.forum.mapper.QuestionMapper;
import com.rhubarb.forum.model.Comment;
import com.rhubarb.forum.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: sunxun
 * @date: 10/22/21 9:58 PM
 * @description:
 */

@Service
public class CommentService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null) {
            throw new CustomizedException(CustomizedErrorResult.COMMENT_PARENT_NOT_FOUND);
        }

        if (comment.getParentType() == null || !CommentParentTypeEnum.has(comment.getParentType())) {
            throw new CustomizedException(CustomizedErrorResult.PARENT_TYPE_NOT_FOUND);
        }

        if (comment.getParentType() == CommentParentTypeEnum.QUESTION.getType()) {
            // 回答问题
            Question question = questionMapper.getQuestionById(comment.getParentId());
            if (question == null) {
                throw new CustomizedException(CustomizedErrorResult.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            questionMapper.incCommentCount(question);

        } else {
            // 回复评论
            Comment parentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (parentComment == null) {
                throw new CustomizedException(CustomizedErrorResult.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }
    }
}
