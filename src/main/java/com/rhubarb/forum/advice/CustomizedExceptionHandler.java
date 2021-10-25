package com.rhubarb.forum.advice;

import com.alibaba.fastjson.JSON;
import com.rhubarb.forum.dto.ResultDTO;
import com.rhubarb.forum.exception.CustomizedErrorResult;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: sunxun
 * @date: 10/16/21 2:59 PM
 * @description: 拦截器。出现自定义的 CustomizedException 异常时，进行拦截。
 * 如果是浏览器访问造成的异常被拦截到，就跳转到error页面。
 * 否则就是在用Postman进行调试，返回json即可。
 * 两者通过请求头的contentType进行区分。
 */
@ControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleControllerException(Throwable ex, Model model,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) { // postman的请求
            ResultDTO resultDTO;
            if (ex instanceof CustomizedException) {
                resultDTO = ResultDTO.errorOf((CustomizedException) ex);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizedErrorResult.SERVER_ERROR);
            }

            PrintWriter writer = null;
            try {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
            return null;
        } else { // 浏览器的请求
            if (ex instanceof CustomizedException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizedErrorResult.SERVER_ERROR.getErrorMessage());
            }
            return new ModelAndView("error");
        }
    }
}
