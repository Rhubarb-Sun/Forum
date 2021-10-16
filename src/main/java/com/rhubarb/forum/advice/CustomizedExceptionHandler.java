package com.rhubarb.forum.advice;

import com.rhubarb.forum.exception.CustomizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: sunxun
 * @date: 10/16/21 2:59 PM
 * @description:
 */
@ControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleControllerException(Throwable ex, Model model) {
        if (ex instanceof CustomizedException) {
            model.addAttribute("message", ex.getMessage());
        } else {
            model.addAttribute("message", "服务器出问题啦！！");
        }
        return new ModelAndView("error");
    }
}
