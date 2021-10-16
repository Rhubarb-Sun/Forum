package com.rhubarb.forum.service;

import com.rhubarb.forum.dto.PaginationDTO;
import com.rhubarb.forum.dto.QuestionDTO;
import com.rhubarb.forum.exception.CustomizedErrorCode;
import com.rhubarb.forum.exception.CustomizedException;
import com.rhubarb.forum.mapper.QuestionMapper;
import com.rhubarb.forum.mapper.UserMapper;
import com.rhubarb.forum.model.Question;
import com.rhubarb.forum.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: sunxun
 * @date: 10/8/21 12:59 PM
 * @description: 同时使用QuestionMapper和UserMapper，引入Service层。
 */

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * // 根据页码和大小 获取分页信息
     *
     * @param pageNo 页码。
     * @param size   单页问题数。
     * @return
     */
    public PaginationDTO getList(Integer pageNo, Integer size) {

        PaginationDTO pagination = new PaginationDTO();

        Integer count = questionMapper.count();
        pagination.setAll(count, pageNo, size);

        if (pageNo > pagination.getTotalPage()) {
            pageNo = pagination.getTotalPage();
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        Integer offset = (pageNo - 1) * size;

        List<Question> list = questionMapper.getList(offset, size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : list) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            User user = userMapper.selectByPrimaryKey(question.getCreatorId());
            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);

        return pagination;
    }

    public PaginationDTO getListByUserId(Integer userId, Integer pageNo, Integer size) {

        PaginationDTO pagination = new PaginationDTO();

        Integer count = questionMapper.countByUserId(userId);
        pagination.setAll(count, pageNo, size);

        if (pageNo > pagination.getTotalPage()) {
            pageNo = pagination.getTotalPage();
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        Integer offset = (pageNo - 1) * size;

        List<Question> list = questionMapper.getListByUserId(userId, offset, size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        User user = userMapper.selectByPrimaryKey(userId);
        for (Question question : list) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);

        return pagination;
    }

    public QuestionDTO getQuestionById(Integer id) {

        Question question = questionMapper.getQuestionById(id);
        if (question == null) {
            throw new CustomizedException(CustomizedErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);

        User user = userMapper.selectByPrimaryKey(question.getCreatorId());
        questionDTO.setUser(user);

        return questionDTO;
    }

    public void createOrUpdateQuestion(Question q) {
        if (q.getId() == null) {
            long currentTimeMillis = System.currentTimeMillis();
            q.setGmtCreate(currentTimeMillis);
            q.setGmtModified(currentTimeMillis);

            questionMapper.createQuestion(q);
        } else {
            Question dbQ = questionMapper.getQuestionById(q.getId());
            if (q.getCreatorId() != dbQ.getCreatorId() || !q.getContent().equals(dbQ.getContent()) ||
                    !q.getTag().equals(dbQ.getTag()) || !q.getTitle().equals(dbQ.getTitle())) {
                q.setGmtModified(System.currentTimeMillis());
                int updateQuestion = questionMapper.updateQuestion(q);

                if (updateQuestion != 1) {
                    throw new CustomizedException(CustomizedErrorCode.QUESTION_NOT_FOUND);
                }
            }
        }
    }
}
