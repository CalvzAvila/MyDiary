package com.diary.project.mydiary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diary.project.mydiary.model.LoginModel;
import com.diary.project.mydiary.model.RegisterModel;
import com.diary.project.mydiary.service.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private EditText etUser, etPass;
    private Button btnLogin;
    private TextView linkReg;
//    private static String URL_LOGIN="";
    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser=findViewById(R.id.username);
        etPass=findViewById(R.id.password);

        linkReg= findViewById(R.id.linkReg);
        btnLogin=findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
                Login();
                //startActivity(new Intent(Login.this, MainActivity.class));
            }
        });

        linkReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });



    }

    private boolean validate() {
        String user, pass1;

        user = etUser.getText().toString();
        pass1 = etPass.getText().toString();


        if (user.length() == 0 ) {
            etUser.setError("Please input a username");
            return false;
        } else if(pass1.length()==0){
            etPass.setError("Please input a password");
            return false;
        }
        else{
            return true;
        }
    }
    private void Login(){

        String user1 = etUser.getText().toString();
        String pass1 = etPass.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAPI api = retrofit.create(ServiceAPI.class);
        Call<LoginModel> callback = api.isLogin(user1,pass1);
        callback.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, retrofit2.Response<LoginModel> response) {
                Log.d(TAG, "onResponse: "+response.body());
                if (response.body().getSuccess() == 1){
                    Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }
                else{
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });


    }

}
