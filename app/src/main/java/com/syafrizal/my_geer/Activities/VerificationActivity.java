package com.syafrizal.my_geer.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.syafrizal.my_geer.Model.CheckResult;
import com.syafrizal.my_geer.Model.Code;
import com.syafrizal.my_geer.Model.ConfSend;
import com.syafrizal.my_geer.Model.EmailSend;
import com.syafrizal.my_geer.Model.Register;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.apihelper.RestoApi;
import com.syafrizal.my_geer.apihelper.UserClient;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity {
    Register register;
    EditText txtConf;
    private UserClient service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        register = (Register) getIntent().getSerializableExtra("register");
        txtConf = findViewById(R.id.editTextVerification);
        service = RestoApi.createService(UserClient.class);
    }

    public void verificationOnClick(View view) {
        Code code = new Code();
        String codetext = txtConf.getText().toString();
        code.setCode(codetext);
        Call<ArrayList<CheckResult>> resultCall = service.getConfirmation(code);
        resultCall.enqueue(new Callback<ArrayList<CheckResult>>() {
            @Override
            public void onResponse(Call<ArrayList<CheckResult>> call, Response<ArrayList<CheckResult>> response) {
                if (response.body().toString().equalsIgnoreCase("[]")){
                    Toast.makeText(VerificationActivity.this,"Incorrect Code",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(VerificationActivity.this,ProfileRegisterActivity.class);
                    intent.putExtra("register", register);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<CheckResult>> call, Throwable t) {
                String message = t.getMessage();
                Log.d("failure", message);

            }
        });

    }

    public void resendOnClick(View view) {
        EmailSend emailSend = new EmailSend();
        emailSend.setEmail(register.getEmail());
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Please Wait");
        progress.setMessage("Sending Verification Code To Your Email");
        progress.setCancelable(false);
        progress.show();
        Call<ConfSend> sendCall = service.sendConfirmation(emailSend);
        sendCall.enqueue(new Callback<ConfSend>() {
            @Override
            public void onResponse(Call<ConfSend> call, Response<ConfSend> response) {
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ConfSend> call, Throwable t) {
                Toast.makeText(VerificationActivity.this,"Check Your Connection",Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}
