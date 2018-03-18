package com.yuanshijia.demologinapp.bean;


import java.util.Date;
//import java.sql.Date;

public class SendUser
{
    private String username;
    private String password;
    private String email;
    private String gender;
    private String birthday;
    private String[] favorite;
    private String introduce;
    private boolean accept;

    public SendUser()
    {
        
    }

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

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String[] getFavorite()
    {
        return favorite;
    }

    public void setFavorite(String[] favorite)
    {
        this.favorite = favorite;
    }

    public String getIntroduce()
    {
        return introduce;
    }

    public void setIntroduce(String introduce)
    {
        this.introduce = introduce;
    }

    public boolean getAccept()
    {
        return accept;
    }

    public void setAccept(boolean accept)
    {
        this.accept = accept;
    }
}
