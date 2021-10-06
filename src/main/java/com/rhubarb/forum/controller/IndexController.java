package com.rhubarb.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: sunxun
 * @date: 10/6/21 9:51 PM
 * @description:
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
