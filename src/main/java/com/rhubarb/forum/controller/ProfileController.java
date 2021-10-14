package com.rhubarb.forum.controller;

import com.rhubarb.forum.dto.PaginationDTO;
import com.rhubarb.forum.model.User;
import com.rhubarb.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: sunxun
 * @date: 10/9/21 6:21 PM
 * @description:
 */
@Controller
public class ProfileController {


    @Autowired
    private QuestionService questionService;

    // TODO 分页点击别的页码后，model中的内容丢失。

    @GetMapping("/profile/{section}")
    public String profile(@PathVariable("section") String section,
                          HttpServletRequest request,
                          Model model,
                          @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          @RequestParam(name = "userId", defaultValue = "5") Integer userId) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "You haven't login yet!");
            return "redirect:/";
        }

        if (section.startsWith("questions")) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionVal", "我的提问");
        } else if (section.startsWith("replies")) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionVal", "回复我的");
        }

        PaginationDTO pagination = questionService.getListByUserId(user.getId(), pageNo, size); // 获取
        model.addAttribute("pagination", pagination);

        return "profile";
    }
}
