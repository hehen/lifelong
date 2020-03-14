package com.cit.lifelong.controller;

import com.cit.lifelong.mapper.QuestionMapper;
import com.cit.lifelong.mapper.UserMapper;
import com.cit.lifelong.model.Question;
import com.cit.lifelong.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName QuestionController
 * @Description: 问题发布
 * @Author hehen
 * @Date 2020/3/14
 * @Version V1.0
 **/
@Controller
public class QuestionController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "/publish";
    }

    @PostMapping("/publish")
    public String doPublish(@Param("title")String title,
                            @Param("content")String content,
                            HttpServletRequest httpServletRequest,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("content",content);
        if(StringUtils.isEmpty(title)){
            model.addAttribute("error","title 不能为空");
            return "/publish";
        }
        if(StringUtils.isEmpty(content)){
            model.addAttribute("error","content 不能为空");
            return "/publish";
        }
        if(httpServletRequest.getCookies()!=null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    User user = userMapper.findUserByToken(cookie.getValue());
                    if (user != null) {
                        Question question = new Question();
                        question.setTitle(title);
                        question.setContent(content);
                        question.setGmtCreate(System.currentTimeMillis());
                        question.setGmtModified(question.getGmtCreate());
                        question.setCreator(user.getId());
                        questionMapper.insert(question);
                        return "redirect:/";
                    }
                    break;
                }
            }
        }
        model.addAttribute("error","请重新登录");
        return "/publish";
    }
}
