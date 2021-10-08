package com.rhubarb.forum.controller;

import com.rhubarb.forum.mapper.QuestionMapper;
import com.rhubarb.forum.mapper.UserMapper;
import com.rhubarb.forum.model.Question;
import com.rhubarb.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: sunxun
 * @date: 10/7/21 11:14 PM
 * @description:
 */

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("content") String content,
                            @RequestParam("tag") String tag,
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

        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    //<li class="dropdown" th:if="${session.user != null}">
                }
                break;
            }
        }
        if (user == null) {
            model.addAttribute("error", "You haven't login yet!");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setTag(tag);
        question.setContent(content);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        question.setCreatorId(user.getId());
        questionMapper.createQuestion(question);
        return "redirect:/";
    }

}
