package com.cit.lifelong.controller;

import com.cit.lifelong.dto.AccessTokenDTO;
import com.cit.lifelong.dto.UserDTO;
import com.cit.lifelong.mapper.UserMapper;
import com.cit.lifelong.model.User;
import com.cit.lifelong.provider.AuthorizeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    @Autowired
    UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) {
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
            User user = new User();
            user.setAccount_id(String.valueOf(userDTO.getId()));
            user.setName(userDTO.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
            httpServletResponse.addCookie(new Cookie("token",user.getToken()));
//            //TODO 为什么不是httpsession
//            httpServletRequest.getSession().setAttribute("user",userDTO);
        } //登陆失败 重新登陆

        return "redirect:/";
    }
}
