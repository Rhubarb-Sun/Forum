package com.rhubarb.forum.controller;

import com.rhubarb.forum.dto.PaginationDTO;
import com.rhubarb.forum.dto.QuestionDTO;
import com.rhubarb.forum.mapper.UserMapper;
import com.rhubarb.forum.model.User;
import com.rhubarb.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: sunxun
 * @date: 10/6/21 9:51 PM
 * @description:
 */

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    /**
     * 获取 cookie，有token说明登录过，通过token获取数据库中对应的User记录，放在Session中，方便后端获取。
     * 没有 token 的话，显示登录按钮。
     * @param request
     * @return
     */
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

        Cookie[] cookies = request.getCookies();
        if (cookies !=null && cookies.length != 0 ) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        //<li class="dropdown" th:if="${session.user != null}">
                    }
                    break;
                }
            }
        }

        PaginationDTO pagination = questionService.getList(pageNo, size);
        model.addAttribute("pagination", pagination);

        return "index";
    }

}
