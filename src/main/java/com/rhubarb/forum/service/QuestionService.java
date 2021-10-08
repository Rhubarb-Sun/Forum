package com.rhubarb.forum.service;

import com.rhubarb.forum.dto.PaginationDTO;
import com.rhubarb.forum.dto.QuestionDTO;
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

    public PaginationDTO getList(Integer pageNo, Integer size) {

        Integer offset = (pageNo - 1) * size;

        List<Question> list = questionMapper.getList(offset, size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO pagination = new PaginationDTO();

        for (Question question : list) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            User user = userMapper.findById(question.getCreatorId());
            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);

        Integer count = questionMapper.count();

        pagination.setAll(count, pageNo, size);

        return pagination;
    }
}
