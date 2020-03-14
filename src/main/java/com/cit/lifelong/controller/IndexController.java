package com.cit.lifelong.controller;

import com.cit.lifelong.dto.PaginationDTO;
import com.cit.lifelong.dto.QuestionDTO;
import com.cit.lifelong.mapper.UserMapper;
import com.cit.lifelong.model.User;
import com.cit.lifelong.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size,
                        HttpServletRequest httpServletRequest, Model model){
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
        PaginationDTO paginationDTO = questionService.getQuestionList(page,size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }
}
