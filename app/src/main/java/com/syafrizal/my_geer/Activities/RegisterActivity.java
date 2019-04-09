package com.syafrizal.my_geer.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.syafrizal.my_geer.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerConOnClick(View view) {
        Intent intent = new Intent(this,VerificationActivity.class);
        startActivity(intent);
    }
}
