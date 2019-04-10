package com.syafrizal.my_geer.Activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.syafrizal.my_geer.Model.Register;
import com.syafrizal.my_geer.R;

public class VerificationActivity extends AppCompatActivity {
    Register register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        register = (Register) getIntent().getSerializableExtra("register");
    }

    public void verificationOnClick(View view) {
        Intent intent = new Intent(this,ProfileRegisterActivity.class);
        intent.putExtra("register", register);
        startActivity(intent);
    }
}
