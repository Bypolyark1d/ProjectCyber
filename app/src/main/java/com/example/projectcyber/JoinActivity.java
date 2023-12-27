package com.example.projectcyber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    EditText EmailText;
    EditText PassText;
   static User CurrentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        EmailText = findViewById(R.id.editTextTextEmailAddress);
        PassText = findViewById((R.id.editTextTextPassword));
    }

    public void RegisterNow(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
    public void GiveUser(String log, String pass)
    {
        JoinActivity joinActivity = this;
        //instance for interface
        MyApi myApi = RetrofitClientInstance.getInstance();
        ResponseRequster Userdata = new ResponseRequster(log,pass);
        Call<User>call = myApi.getUser(Userdata);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() != 200) {
                    EmailText.setTextColor(getResources().getColor(R.color.red, null));
                    PassText.setTextColor(getResources().getColor(R.color.red,null));
                    return;
                }
                response.body();
                CurrentUser = response.body();
                Intent intent = new Intent(joinActivity,activity_dashboards.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void JoinCkick(View view) {
        String CurrentEmail = EmailText.getText().toString();
        String CurrentPass = PassText.getText().toString();
        GiveUser(CurrentEmail,CurrentPass);
        EmailText.setTextColor(getResources().getColor(R.color.white, null));
        PassText.setTextColor(getResources().getColor(R.color.white,null));
    }
}