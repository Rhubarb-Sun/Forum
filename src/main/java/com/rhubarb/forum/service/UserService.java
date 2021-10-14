package com.rhubarb.forum.service;

import com.rhubarb.forum.mapper.UserMapper;
import com.rhubarb.forum.model.User;
import com.rhubarb.forum.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: sunxun
 * @date: 10/10/21 4:06 PM
 * @description:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     *
     * @param user 从Github获取的User，有可能是更新过的 昵称，头像地址，简介 等。
     */
    public void updateOrInsert(User user) {

        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);

        if (users.size() == 0) {

            long currentTimeMillis = System.currentTimeMillis();
            user.setGmtCreate(currentTimeMillis);
            user.setGmtModified(currentTimeMillis);

            userMapper.insert(user);
        } else {
            User dbUser = users.get(0);
            if (!user.getName().equals(dbUser.getName()) || !user.getAvatarUrl().equals(dbUser.getAvatarUrl())
                    || !user.getBio().equals(dbUser.getBio()) || !user.getToken().equals(dbUser.getToken())) {
                user.setGmtModified(System.currentTimeMillis());
                UserExample userExample = new UserExample();
                userExample.createCriteria()
                        .andIdEqualTo(dbUser.getId());
                userMapper.updateByExampleSelective(user, userExample);
//                userMapper.update(user);
            }
        }
    }
}
