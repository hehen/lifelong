package com.cit.lifelong.controller;

import com.cit.lifelong.dto.AccessTokenDTO;
import com.cit.lifelong.dto.UserDTO;
import com.cit.lifelong.provider.AuthorizeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.client.uri}")
    private String callbackUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest httpServletRequest) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(callbackUri);
        accessTokenDTO.setState(state);
        String accessToken = authorizeProvider.getAccessToken(accessTokenDTO);
        UserDTO userDTO = authorizeProvider.getUser(accessToken);
        if(userDTO!=null){
            System.out.println("username = " + userDTO.getName());
            //登陆成功 写cookies和session
            //TODO 为什么不是httpsession
            httpServletRequest.getSession().setAttribute("user",userDTO);
        } //登陆失败 重新登陆

        return "redirect:/";
    }
}
