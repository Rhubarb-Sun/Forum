package com.rhubarb.forum.controller;

import com.rhubarb.forum.dto.QuestionDTO;
import com.rhubarb.forum.mapper.QuestionMapper;
import com.rhubarb.forum.model.Question;
import com.rhubarb.forum.model.User;
import com.rhubarb.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: sunxun
 * @date: 10/7/21 11:14 PM
 * @description:
 */

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String editQuestion(@PathVariable("id") Integer id,
                               Model model) {
        QuestionDTO question = questionService.getQuestionById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("content", question.getContent());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());

        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("content") String content,
                            @RequestParam("tag") String tag,
                            @RequestParam(value = "id", required = false) Integer id,
                            HttpServletRequest request, Model model) {
//        Question q = new Question();
//        q.setContent(content);
//        q.setTag(tag);
//        q.setTitle(title);
//        model.addAttribute("q", q);
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("tag", tag);

        if (title == null || "".equals(title)) {
            model.addAttribute("error", "Title shall not be empty! ");
            return "publish";
        }
        if (content == null || "".equals(content)) {
            model.addAttribute("error", "Content shall not be empty! ");
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("error", "Tag shall not be empty! ");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "You haven't login yet!");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setTag(tag);
        question.setContent(content);
//        question.setCreatorId(user.getId());
//        question.setGmtCreate(System.currentTimeMillis());
//        question.setGmtModified(question.getGmtCreate());
        question.setCreatorId(user.getId());
        question.setId(id);
//        questionMapper.createQuestion(question);
        questionService.createOrUpdateQuestion(question);
        return "redirect:/";

    }

}
