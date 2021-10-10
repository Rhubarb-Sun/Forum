package com.rhubarb.forum.mapper;

import com.rhubarb.forum.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author: sunxun
 * @date: 10/7/21 2:01 PM
 * @description: 参数是对象时， #{}自动匹配获取对象的参数。否则，要添加 @Param 注解。
 */

@Repository
@Mapper
public interface UserMapper {

    @Insert("insert into user (account_id, name, token, gmt_create, gmt_modified, avatar_url, bio) " +
            "values (#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl}, #{bio})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    @Select("select * from user where account_id = #{accountId}")
    User getUserByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, " +
            "avatar_url = #{avatarUrl}, bio = #{bio} where account_id = #{accountId}")
    void update(User user);
}
