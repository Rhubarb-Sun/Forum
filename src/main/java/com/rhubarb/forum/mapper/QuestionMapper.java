package com.rhubarb.forum.mapper;

import com.rhubarb.forum.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: sunxun
 * @date: 10/8/21 1:29 AM
 * @description:
 */
@Mapper
@Repository
public interface QuestionMapper {

    @Insert("insert into question (title, content, creator_id, tag, gmt_create, gmt_modified) " +
            "values (#{title}, #{content}, #{creatorId}, #{tag}, #{gmtCreate}, #{gmtModified})")
    void createQuestion(Question question);
}
