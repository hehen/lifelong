package com.cit.lifelong.controller;

import com.cit.lifelong.dto.AccessTokenDTO;
import com.cit.lifelong.dto.UserDTO;
import com.cit.lifelong.provider.AuthorizeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName AuthorizeController
 * @Description: guthub授权
 * @Author hehen
 * @Date 2020/3/8
 * @Version V1.0
 **/
@Controller
public class AuthorizeController {
    @Autowired
    AuthorizeProvider authorizeProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code
            , @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("a0e7e5891c4c7ef54bba");
        accessTokenDTO.setClient_secret("5ee12d0bea2911e801dd63e1c5534840df3c17d2");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8087/callback");
        accessTokenDTO.setState(state);
        String accessToken = authorizeProvider.getAccessToken(accessTokenDTO);
        UserDTO userDTO = authorizeProvider.getUser(accessToken);
        System.out.println("username = " + userDTO.getName());
        return "index";
    }
}
