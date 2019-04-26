package com.syafrizal.my_geer.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.syafrizal.my_geer.Model.Constant;
import com.syafrizal.my_geer.Model.Login;
import com.syafrizal.my_geer.Model.User;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.apihelper.RestoApi;
import com.syafrizal.my_geer.apihelper.UserClient;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText et_mail;
    EditText et_password;
    private UserClient service;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_mail = findViewById(R.id.editTextEmail);
        et_password = findViewById(R.id.editTextPassword);
        preferences = getSharedPreferences(Constant.SHARED_PREF,MODE_PRIVATE);
        Paper.init(this);
        if (preferences.getString(Constant.TOKEN_PREF,null) !=null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void loginOnClick(View view) {
        String email , password;
        email = et_mail.getText().toString();
        password = et_password.getText().toString();

        Login login = new Login(email,password);
        service = RestoApi.createService(UserClient.class);
        doLogin(login);
    }


    public void doLogin(Login login){
        Call<User> userCall = service.login(login);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                    User user = response.body();
                    Paper.book().write(Constant.TOKEN_PREF,user.getToken());
                    preferences.edit()
                            .putString(Constant.TOKEN_PREF,user.getToken())
                            .putString(Constant.NAME,user.getUsername())
                            .putString(Constant.EMAIL,user.getEmail())
                            .putString(Constant.PHONE,user.getPhone())
                            .apply();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Login is incorrect",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Check Your Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void registerOnClick(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
