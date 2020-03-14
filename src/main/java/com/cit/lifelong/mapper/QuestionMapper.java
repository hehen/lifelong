package com.cit.lifelong.mapper;

import com.cit.lifelong.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionMapper {
    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param("offset") int offset,@Param("size")  int size);

    @Insert("insert into question (title,content,gmt_create,gmt_modified,creator) " +
            "values (#{title},#{content},#{gmtCreate},#{gmtModified},#{creator})")
    void insert(Question question);

    @Select("select count(1) from question")
    int count();

    @Select("select count(1) from question where creator = #{userId}")
    int countByCreator(@Param("userId") int userId);

    @Select("select * from question where creator = #{userId} limit #{offset}, #{size}")
    List<Question> listByCreator(@Param("userId") int userId, @Param("offset") int offset,@Param("size")  int size);
}
