package com.yuanshijia.demologinapp.http;

import com.yuanshijia.demologinapp.bean.Login;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 49118 on 2018/3/18.
 */

public interface LoginService
{
    //    @FormUrlEncoded
//    @POST("/servlet/doLogin")
//    Call<Response> getLogin(@Body Entity entity
//    );
//
//    public class Entity{
//        public String username;
//        public String password;
//    }
    @FormUrlEncoded //不要忘记写
    @POST("/Login/servlet/doLogin")
    Call<Login> getLogin(
            @Field("username") String username,
            @Field("password") String password
    );

}
