package com.rhubarb.forum.provider;

import com.alibaba.fastjson.JSON;
import com.rhubarb.forum.dto.AccessTokenDTO;
import com.rhubarb.forum.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: sunxun
 * @date: 10/7/21 1:23 AM
 * @description:
 */

@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            // access_token=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&scope=user&token_type=bearer
            String token = s.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getUser(String accessToken) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(s, GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
