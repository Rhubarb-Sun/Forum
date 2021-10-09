package com.rhubarb.forum.controller;

import com.rhubarb.forum.dto.PaginationDTO;
import com.rhubarb.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: sunxun
 * @date: 10/6/21 9:51 PM
 * @description:
 */

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    /**
     * 获取 cookie，有token说明登录过，通过token获取数据库中对应的User记录，放在Session中，方便后端获取。
     * 没有 token 的话，显示登录按钮。
     *
     * @param pageNo  url传入页码，默认为1
     * @param size    url传入单页问题数，默认尺寸为5。
     * @return
     */
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

        PaginationDTO pagination = questionService.getList(pageNo, size); // 获取
        model.addAttribute("pagination", pagination);

        return "index";
    }

}
