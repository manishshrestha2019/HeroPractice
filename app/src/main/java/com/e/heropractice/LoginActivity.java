package com.e.heropractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import api.HeroesAPI;
import model.LoginSignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.Url;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername,etPassword;
    private Button btnLogin,btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnUserLogin);
        btnSignup=findViewById(R.id.btnUSerRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();

            }
        });

    }
    private void checkUser(){

        HeroesAPI heroesAPI= Url.getInstance().create(HeroesAPI.class);

        String username=etUsername.getText().toString().trim();
        String password=etUsername.getText().toString().trim();

      Call<LoginSignupResponse> usersCall =heroesAPI.checkUser(username,password);

      usersCall.enqueue(new Callback<LoginSignupResponse>() {
          @Override
          public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
              if(!response.isSuccessful()){
                  Toast.makeText(LoginActivity.this, "Either Username and Password is incorrect", Toast.LENGTH_SHORT).show();
                  return;
              }
              else{
                  if(response.body().getSuccess()){
                      Url.Cookie=response.headers().get("Set-Cookie");
                      Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                      startActivity(intent);
                      finish();
                  }

              }
          }

          @Override
          public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
              Toast.makeText(LoginActivity.this, "Error "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

          }
      });

    }
}
