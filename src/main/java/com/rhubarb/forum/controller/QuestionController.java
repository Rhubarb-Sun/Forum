package com.rhubarb.forum.controller;

import com.rhubarb.forum.dto.QuestionDTO;
import com.rhubarb.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: sunxun
 * @date: 10/10/21 12:56 PM
 * @description:
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") String id,
                           Model model) {

        QuestionDTO questionDTO =  questionService.getQuestionById(id);
        model.addAttribute("question", questionDTO);

        return "question";
    }
}
