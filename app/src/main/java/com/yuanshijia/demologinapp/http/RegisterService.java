package com.yuanshijia.demologinapp.http;

import com.yuanshijia.demologinapp.bean.SendUser;
import com.yuanshijia.demologinapp.bean.User;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by 49118 on 2018/3/18.
 */

public interface RegisterService
{
    //采用@Post表示Post方法进行请求（传入部分url地址）
    // 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
    // 需要配合@Field 向服务器提交需要的字段
//    @FormUrlEncoded
//    @Headers({"Content-Type: application/json","Accept: application/json"})
//
//    @POST("/Login/servlet/doRegister")
//    Call<User> getRegister(@Body SendUser use);


    /**
     * private String username;
     * private String password;
     * private String email;
     * private String gender;
     * private Date birthday;
     * private String[] favorite;
     * private String introduce;
     * private boolean accept;
     */
//    @POST("/Login/servlet/doRegister")
//    Call<User> getRegister(
//            @Field("username")String username,
//            @Field("password")String password
////            @Field("email")String email,
////            @Field("gender")String gender,
////            @Field("birthday")Date date,
////            @Field("favorite")String[] favorite,
////            @Field("introduce")String introduce
//            );

    @FormUrlEncoded //表单请求
    @POST("/Login/servlet/doRegister")
    Call<User> getRegister(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("gender") String gender,
            @Field("introduce") String introduce,
            @Field("favorite") String[] favorite,
            @Field("birthday") String date
    );
}
