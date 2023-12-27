package com.example.projectcyber;

import java.io.Serializable;

public class ResponseRequster
{
    String login;
    String password;

    public ResponseRequster(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
