package com.yuanshijia.demologinapp.bean;

import java.util.List;

/**
 * Created by 49118 on 2018/3/18.
 */

public class User
{

    /**
     * msg : success
     * data : {"username":"787878","password":"787878","email":"491187718@qq.com","birthday":"1997-06-02","sex":"男","introduce":"4545245","favorite":["nba"]}
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
         * username : 787878
         * password : 787878
         * email : 491187718@qq.com
         * birthday : 1997-06-02
         * sex : 男
         * introduce : 4545245
         * favorite : ["nba"]
         */

        private String username;
        private String password;
        private String email;
        private String birthday;
        private String sex;
        private String introduce;
        private List<String> favorite;

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

        public String getEmail()
        {
            return email;
        }

        public void setEmail(String email)
        {
            this.email = email;
        }

        public String getBirthday()
        {
            return birthday;
        }

        public void setBirthday(String birthday)
        {
            this.birthday = birthday;
        }

        public String getSex()
        {
            return sex;
        }

        public void setSex(String sex)
        {
            this.sex = sex;
        }

        public String getIntroduce()
        {
            return introduce;
        }

        public void setIntroduce(String introduce)
        {
            this.introduce = introduce;
        }

        public List<String> getFavorite()
        {
            return favorite;
        }

        public void setFavorite(List<String> favorite)
        {
            this.favorite = favorite;
        }
    }
}
