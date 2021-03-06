package com.rhubarb.forum.mapper;

import com.rhubarb.forum.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> getList(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator_id = #{user_id}")
    Integer countByUserId(@Param(value = "user_id") Integer userId);

    @Select("select * from question where creator_id = #{user_id} limit #{offset}, #{size}")
    List<Question> getListByUserId(@Param(value = "user_id") Integer userId,
                                   @Param(value = "offset") Integer offset,
                                   @Param(value = "size")Integer size);

    @Select("select * from question where id = #{id}")
    Question getQuestionById(Integer id);

    @Update("update question set title = #{title}, content = #{content}, tag = #{tag}, gmt_modified = #{gmtModified} " +
            "where id = #{id}")
    int updateQuestion(Question q);

    @Update("update question set read_count = #{readCount} + 1 where id = #{id}")
    void incView(Integer id, Integer readCount);

    @Update("update question set comment_count = #{commentCount} + 1 where id = #{id}")
    void incCommentCount(Question question);
}
