package com.example.projectcyber;

import com.google.gson.annotations.SerializedName;

public class User
{
    Integer id;
    private String login;
    private String password;

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
