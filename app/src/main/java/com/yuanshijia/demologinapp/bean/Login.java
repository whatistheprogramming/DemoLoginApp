package com.yuanshijia.demologinapp.bean;

/**
 * Created by 49118 on 2018/3/18.
 */

public class Login{
    /**
     * msg : success
     * data : {"username":"yuan","password":"123"}
     */

    private String msg;
    private DataBean data;

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public DataBean getData()
    {
        return data;
    }

    public void setData(DataBean data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        /**
         * username : yuan
         * password : 123
         */

        private String username;
        private String password;

        public String getUsername()
        {
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getPassword()
        {
            return password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }
    }
}