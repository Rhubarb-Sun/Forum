package com.rhubarb.forum.controller;

import com.rhubarb.forum.mapper.UserMapper;
import com.rhubarb.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: sunxun
 * @date: 10/6/21 9:51 PM
 * @description:
 */

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取 cookie，有token说明登录过，通过token获取数据库中对应的User记录，放在Session中，方便后端获取。
     * @param request
     * @return
     */
    @GetMapping("/")
    public String index(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
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
        return "index";
    }

}
