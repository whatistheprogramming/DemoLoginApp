package com.yuanshijia.demologinapp.http;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by 49118 on 2018/3/17.
 */

public interface Service
{
    @GET("/") // 因为我们是解析首页，也就是根目录，所以这边写"/"
    Call<String> getBaidu();
}
