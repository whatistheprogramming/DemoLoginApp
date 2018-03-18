package com.yuanshijia.demologinapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuanshijia.demologinapp.bean.Login;
import com.yuanshijia.demologinapp.http.LoginService;
import com.yuanshijia.demologinapp.http.Service;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity
{
    private static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.login_et_username);
        password = findViewById(R.id.login_et_password);
        login = findViewById(R.id.login_btn_login);
        register = findViewById(R.id.login_btn_register);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //跳转到注册界面
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//                finish();
//                Toast.makeText(LoginActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
//                sendRequestWithOkHttp();
                sendRequestWithRetrofit();
            }
        });
    }

    private void sendRequestWithRetrofit()
    {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://yuansjia.cn")
                .addConverterFactory(GsonConverterFactory.create())    //设置使用Gson解析(记得加入依赖)
                .build();

        //创建 网络请求接口 的实例
        LoginService loginService = retrofit.create(LoginService.class);

        String user = username.getText().toString();
        String pass = password.getText().toString();

        // 对 发送请求 进行封装
        Call<Login> call = loginService.getLogin(user, pass);

        //发送网络请求(异步)
        call.enqueue(new Callback<Login>()
        {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response)
            {
                if ("success".equals(response.body().getMsg()))
                {
                    //登入成功
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    //处理返回的数据结果
                    Log.d(TAG, "msg="+response.body().getMsg());
                    Log.d(TAG, "username="+response.body().getData().getUsername());
                    Log.d(TAG, "password="+response.body().getData().getPassword());
                    Toast.makeText(LoginActivity.this, "欢迎用户：" + response.body().getData().getUsername(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t)
            {
                Log.d(TAG, "onFailure: " + "请求失败");
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "登入失败", Toast.LENGTH_SHORT).show();
            }
        });
        

        

        

        
    }

    private void test()
    {

        /**
         * 实例化Retrofit
         * Converter 转换器 可以转换任意数据类型
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com")
                .addConverterFactory(
                        new Converter.Factory() {
                            @Override
                            public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                                //return super.responseBodyConverter(type, annotations, retrofit);
                                return new Converter<ResponseBody, String>() {
                                    @Override
                                    public String convert(ResponseBody value) throws IOException
                                    {
                                        //return null;
                                        return value.string();
                                    }
                                };
                            }
                        }
                ).build();
        Service service = retrofit.create(Service.class); // 实例化服务
        Call<String> call = service.getBaidu(); // 创建请求
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {
                Toast.makeText(LoginActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                Toast.makeText(LoginActivity.this, "请求失败"+call.request().url(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        }); // 异步请求


    }

    private void sendRequestWithOkHttp()
    {
        Toast.makeText(this, "username=" + username.getText() + ",password=" + password.getText(), Toast.LENGTH_SHORT).show();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username", username.getText().toString())
                            .add("password", password.getText().toString())
                            .build();

                    String url = "http://www.yuansjia.cn/Login2/doLogin.jsp";
//                    String url = "https://www.baidu.com/";
                    Request request = new Request.Builder()
//                            .url("http://www.yuansjia.cn/Login2/servlet/ServletTest?username=123&password=123")
                            .url(url)
                            .post(requestBody)
                            .build();

                    okhttp3.Response response = client.newCall(request).execute();
                    final String responseData = response.body().string();
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(LoginActivity.this,"返回内容："+ responseData, Toast.LENGTH_SHORT).show();
//                            textView.setText(responseData);

                            Log.d(TAG, "run: " + responseData);

                        }
                    });

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
