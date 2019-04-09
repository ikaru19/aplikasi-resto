package com.syafrizal.my_geer.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.syafrizal.my_geer.R;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
    }

    public void verificationOnClick(View view) {
        Intent intent = new Intent(this,ProfileRegisterActivity.class);
        startActivity(intent);
    }
}
