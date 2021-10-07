package com.rhubarb.forum.controller;

import com.rhubarb.forum.dto.AccessTokenDTO;
import com.rhubarb.forum.dto.GitHubUser;
import com.rhubarb.forum.mapper.UserMapper;
import com.rhubarb.forum.model.User;
import com.rhubarb.forum.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author: sunxun
 * @date: 10/7/21 1:39 AM
 * @description:
 */

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback") // github 要求
    public String callback(@RequestParam(name = "code") String code, // SpringBoot 自动在上下文中寻找 request和response
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);

        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        if (gitHubUser != null) {

            User user = new User();
            String token = UUID.randomUUID().toString();

            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setName(gitHubUser.getName());
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);

            response.addCookie(new Cookie("token", token)); // 将token放在cookie中，再进入index.html页面时，就可以获取。

//            request.getSession().setAttribute("gitHubUser", gitHubUser);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
