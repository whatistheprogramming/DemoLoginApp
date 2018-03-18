package com.yuanshijia.demologinapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yuanshijia.demologinapp.bean.SendUser;
import com.yuanshijia.demologinapp.bean.User;
import com.yuanshijia.demologinapp.http.RegisterService;
import com.yuanshijia.demologinapp.http.Service;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity
{

    private EditText username;
    private EditText password;
    private EditText email;
    private EditText introduce;

    private Button register;
    private Button btnDialog;
    private TextView showData;

    private RadioGroup sex;

    private CheckBox nba;
    private CheckBox music;
    private CheckBox movie;
    private CheckBox internet;
    private List<CheckBox> checkBoxList = new ArrayList<>();

    private SendUser sendUser = new SendUser();
    private final String  TAG="RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username = findViewById(R.id.register_et_username);
        password = findViewById(R.id.register_et_password);
        email = findViewById(R.id.register_et_email);
        introduce = findViewById(R.id.register_et_introduce);

        register = findViewById(R.id.register_btn_register);
        btnDialog = findViewById(R.id.register_btn_date);
        showData = findViewById(R.id.register_tv_time);
        sex = findViewById(R.id.register_rg_sex);

        nba=findViewById(R.id.register_cb_nba);
        music = findViewById(R.id.register_cb_music);
        internet = findViewById(R.id.register_cb_internet);
        movie = findViewById(R.id.register_cb_movie);

        // 将所有的checkbox放到一个集合中
        checkBoxList.add(nba);
        checkBoxList.add(music);
        checkBoxList.add(internet);
        checkBoxList.add(movie);

        btnDialog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Calendar c = Calendar.getInstance();
                // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
                new DatePickerDialog(RegisterActivity.this,0,
                        // 绑定监听器
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                showData.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = null;
                                try
                                {
                                    //string转date
//                                    date = format.parse(showData.getText().toString());
//                                    sendUser.setBirthday(format.parse(showData.getText().toString()));
                                    sendUser.setBirthday(showData.getText().toString());
                                    
//                                    sendUser.setBirthday(format.format(showData.getText().toString()));
//                                    String format1 = format.format(sendUser.getBirthday());
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                        // 设置初始日期
                        , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                        .get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //选择性别
        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,  int checkedId)
            {
                if (checkedId == R.id.register_men)
                {
                    sendUser.setGender("男");
                }
                else if (checkedId == R.id.register_women)
                {
                    sendUser.setGender("女");
                }
            }
        });

       register.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View view)
           {
               sendUser.setUsername(username.getText().toString());
               sendUser.setPassword(password.getText().toString());
               sendUser.setEmail(email.getText().toString());
               sendUser.setIntroduce(introduce.getText().toString());

               List<String> list = new ArrayList<>();
               //遍历集合中的checkBox,判断是否选择，获取选中的文本
               for (CheckBox checkBox : checkBoxList)
               {
                   if (checkBox.isChecked())
                   {
                       list.add(checkBox.getText().toString());
                   }
               }
               //list转为string[]
               String[] fav = list.toArray(new String[0]);

               sendUser.setFavorite(fav);

               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//               Log.d(TAG, "user=: "+sendUser.getUsername());
//               Log.d(TAG, "pass=: "+sendUser.getPassword());
//               Log.d(TAG, "email=: "+sendUser.getEmail());
//               Log.d(TAG, "birthday=: " +sendUser.getBirthday());
//               Log.d(TAG, "gender=: "+sendUser.getGender());
//               String s = "";
//               for (int i = 0; i < sendUser.getFavorite().length; i++)
//               {
//                   s += sendUser.getFavorite()[i]+" ";
//               }
//               Log.d(TAG, "onClick: " + s);
//               Log.d(TAG, "into=: "+sendUser.getIntroduce());


               sendRequestWithRetrofit();
//               test();


           }
       });
    }

    private void sendRequestWithRetrofit()
    {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://yuansjia.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //创建 网络请求接口 的实例
        RegisterService service = retrofit.create(RegisterService.class);

//         对发送请求 进行封装
//        Call<User> call = service.getRegister(
        Call<User> call = service.getRegister(
                sendUser.getUsername(),
                sendUser.getPassword(),
                sendUser.getEmail(),
                sendUser.getGender(),
                sendUser.getIntroduce(),
                sendUser.getFavorite(),
                sendUser.getBirthday()
                );

        //发送网络请求(异步)
        call.enqueue(new Callback<User>()
        {

            @Override
            public void onResponse(Call<User> call, Response<User> response)
            {

                if ("success".equals(response.body().getMsg()))
                {
                    //注册成功
                    Toast.makeText(RegisterActivity.this, "注册成功：user=" + response.body().getData().getUsername(), Toast.LENGTH_SHORT).show();

                    Log.d(TAG, "onResponse: " + response.body().toString());
                    Log.d(TAG, "onResponse: " + response.body().getMsg());
                    Log.d(TAG, "onResponse: " + response.body().getData().getUsername());
                    Log.d(TAG, "onResponse: " + response.body().getData().getPassword());
                    Log.d(TAG, "onResponse: " + response.body().getData().getEmail());
                    Log.d(TAG, "onResponse: " + response.body().getData().getIntroduce());
                    Log.d(TAG, "onResponse: " + response.body().getData().getSex());

                    finish();
                }
                else if ("failure".equals(response.body().getMsg()))
                {
                    Toast.makeText(RegisterActivity.this, "账号已经存在", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t)
            {
                Log.d(TAG, "onFailure: mess=" + t.getMessage());
                
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                
            }
        });




                
    }

//    private void test()
//    {
//
//        /**
//         * 实例化Retrofit
//         * Converter 转换器 可以转换任意数据类型
//         */
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://yuansjia.cn")
////                .baseUrl("https://www.baidu.com")
//                .addConverterFactory(
//                        new Converter.Factory() {
//                            @Override
//                            public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//                                //return super.responseBodyConverter(type, annotations, retrofit);
//                                return new Converter<ResponseBody, String>() {
//                                    @Override
//                                    public String convert(ResponseBody value) throws IOException
//                                    {
//                                        //return null;
//                                        return value.string();
//                                    }
//                                };
//                            }
//                        }
//                ).build();
////        Service service = retrofit.create(Service.class); // 实例化服务
//        RegisterService service = retrofit.create(RegisterService.class); // 实例化服务
//        // 创建请求
//        Call<User> call = service.getRegister(sendUser);
////                sendUser.getUsername(),
////                sendUser.getPassword(),
////                sendUser.getEmail(),
////                sendUser.getGender(),
////                sendUser.getIntroduce(),
////                sendUser.getFavorite(),
////                sendUser.getBirthday()
////        );
//
//                call.enqueue(new Callback<User>()
//                {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response)
//                    {
////                        if ("success".equals())
//                        {
//                            Toast.makeText(RegisterActivity.this, "注册成功"+response.body().getMsg(), Toast.LENGTH_SHORT).show();
//
//                        }
////                        else
//                        {
////                            Toast.makeText(RegisterActivity.this, "注册失败，账号已经存在", Toast.LENGTH_SHORT).show();
//                        }
//                        Log.d(TAG, "onResponse: " + response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t)
//                    {
//                        Toast.makeText(RegisterActivity.this, "请求失败" + call.request().url(), Toast.LENGTH_SHORT).show();
//                        t.printStackTrace();
//                    }
//                }); // 异步请求
//
//
//    }
}
