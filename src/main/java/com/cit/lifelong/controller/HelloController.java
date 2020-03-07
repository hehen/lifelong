package com.cit.lifelong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 第一个controller
 * http://localhost:8087/hello?name=zwl
 */
@Controller
public class HelloController {
    //TODO    @GetMapping(name = "/hello") 解释这种错误的原因
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
