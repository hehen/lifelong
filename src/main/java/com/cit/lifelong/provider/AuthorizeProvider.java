package com.cit.lifelong.provider;

import com.alibaba.fastjson.JSON;
import com.cit.lifelong.dto.AccessTokenDTO;
import com.cit.lifelong.dto.UserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName AuthorizeProvider
 * @Description: github接口
 * @Author hehen
 * @Date 2020/3/8
 * @Version V1.0
 **/
@Component
public class AuthorizeProvider {
    public static final MediaType MEDIATYPE
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), MEDIATYPE);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            /*
             * response.body().string() 只能取一次结果？
             */
            String result = response.body().string();
            System.out.println("accessToken = " + result);
            return result.substring(result.indexOf("=")+1,result.indexOf("&")+1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDTO getUser(String accessToken) {
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            System.out.println("user = " + result);
            return JSON.parseObject(result, UserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
