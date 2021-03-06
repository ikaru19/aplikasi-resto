package com.syafrizal.my_geer.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.syafrizal.my_geer.Model.Constant;
import com.syafrizal.my_geer.R;

public class EmptyActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        SharedPreferences preferences = getSharedPreferences(Constant.SHARED_PREF,MODE_PRIVATE);
//        getSupportActionBar().hide();

        String token = preferences.getString(Constant.TOKEN_PREF,null);

        if (token != null){
            intent = new Intent(this,MainActivity.class);
        }else{
            intent = new Intent(this,LoginActivity.class);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },2000);




    }
}
