package com.diary.project.mydiary;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.diary.project.mydiary.model.RegisterModel;
import com.diary.project.mydiary.service.ServiceAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    private EditText etUser, etPass,etCPass;
    private Button btnRegister;
    private static final String TAG = "Register";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUser  = findViewById(R.id.username);
        etPass  =findViewById(R.id.password);
        etCPass = findViewById(R.id.cPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    Register();
                    clearText();
                }
            }
        });

    }
    private void clearText()
    {
        etUser.setText("");
        etPass.setText("");
        etCPass.setText("");
    }
    private boolean validate() {
        String user, pass1, pass2;

        user = etUser.getText().toString();
        pass1 = etPass.getText().toString();
        pass2 = etCPass.getText().toString();

        if (user.length() == 0 ) {
            etUser.setError("Please input a username");
            return false;
        } else if(pass1.length()==0){
            etPass.setError("Please input a password");
            return false;
        } else if (pass2.length() == 0){
            etCPass.setError("Please confirm the password");
            return false;
        }
        else{
            return true;
        }
    }



    private void Register(){

        String user = etUser.getText().toString();
        String pass = etPass.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAPI api = retrofit.create(ServiceAPI.class);
        Call<RegisterModel> callback = api.isRegister(user,pass);
        callback.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, retrofit2.Response<RegisterModel> response) {
                Log.d(TAG, "onResponse: "+response.body());
                if (response.body().getSuccess() == 1){
                    Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }


}
