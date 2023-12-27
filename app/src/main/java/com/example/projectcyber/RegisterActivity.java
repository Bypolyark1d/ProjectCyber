package com.example.projectcyber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText EmailUser;
    Activity activity;
    EditText pass;
    EditText passrepit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        activity = this;
        EmailUser = findViewById(R.id.editTextTextEmailAddressRegister);
        pass = findViewById(R.id.editTextTextPasswordRegister);
        passrepit = findViewById(R.id.editTextRepitPasswordRegister);
    }

    public void RegisterClick(View view)
    {
        String h = EmailUser.getText().toString();
        String passf = pass.getText().toString();
        String passS = passrepit.getText().toString();
        if(h.matches("") )
        {
            Toast toast = Toast.makeText(RegisterActivity.this,"Пустое поле", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            if(!passf.equals(passS))
            {
                Toast toast = Toast.makeText(RegisterActivity.this,"Пароль не совпадает", Toast.LENGTH_LONG);
                toast.show();
            }
            else
            {
                ResponseRequster newUser = new ResponseRequster(h,pass.getText().toString());
                MyApi myApi = RetrofitClientInstance.getInstance();
                Call<User> call = myApi.addUser(newUser);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.code() != 200) {
                            return;
                        }
                        User curUser = response.body();

                        Intent intentregister = new Intent(activity,activity_dashboards.class);
                        JoinActivity.CurrentUser = curUser;
                        startActivity(intentregister);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        }
    }
}