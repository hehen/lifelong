package com.cit.lifelong.mapper;

import com.cit.lifelong.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,avatar_url) values (#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findUserByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findUserById(int id);
}
