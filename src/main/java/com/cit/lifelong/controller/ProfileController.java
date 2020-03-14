package com.cit.lifelong.controller;

import com.cit.lifelong.dto.PaginationDTO;
import com.cit.lifelong.mapper.UserMapper;
import com.cit.lifelong.model.User;
import com.cit.lifelong.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IndexController
 * @Description: 个人资料页
 * @Author hehen
 * @Date 2020/3/8
 * @Version V1.0
 **/
@Controller
public class ProfileController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          HttpServletRequest httpServletRequest, Model model) {
        User user = null;
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    user = userMapper.findUserByToken(cookie.getValue());
                    if (user != null) {
                        httpServletRequest.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user == null) {
            return "redirect:/";
        }
        if ("question".equals(action)){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的问题");
            PaginationDTO paginationDTO = questionService.getQuestionByCreator(user.getId(), page, size);
            model.addAttribute("paginationDTO", paginationDTO);
        }else if("comment".equals(action)){
            model.addAttribute("section","comment");
            model.addAttribute("sectionName","最新评论");
        }
        return "/profile";
    }
}
