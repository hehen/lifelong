package com.cit.lifelong.controller;

import com.cit.lifelong.mapper.UserMapper;
import com.cit.lifelong.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IndexController
 * @Description: 首页
 * @Author hehen
 * @Date 2020/3/8
 * @Version V1.0
 **/
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest){
        if(httpServletRequest.getCookies()!=null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    User user = userMapper.findUserByToken(cookie.getValue());
                    if (user != null) {
                        httpServletRequest.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}
