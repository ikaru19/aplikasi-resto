package com.syafrizal.my_geer.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.syafrizal.my_geer.Model.ConfSend;
import com.syafrizal.my_geer.Model.EmailSend;
import com.syafrizal.my_geer.Model.Regist;
import com.syafrizal.my_geer.Model.Register;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.apihelper.RestoApi;
import com.syafrizal.my_geer.apihelper.UserClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText txtUsername , txtPassword , txtEmail , txtPhone;
    private UserClient service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtUsername = findViewById(R.id.etUsernameRegister);
        txtEmail = findViewById(R.id.etEmailRegister);
        txtPassword = findViewById(R.id.etPasswordRegister);
        txtPhone = findViewById(R.id.etPhoneNumberReg);
        service = RestoApi.createService(UserClient.class);

    }

    public void registerConOnClick(View view) {

        final Register register = new Register();
        final String password = txtPassword.getText().toString();
        String email = txtEmail.getText().toString();
        String username = txtUsername.getText().toString();
        String phone = txtPhone.getText().toString();

        if (isEmailValid(email)){
            register.setEmail(email);
            if (password.length()>=6){
                register.setPassword(password);
                register.setPassword_confirm(password);
                register.setUsername(username);
                register.setPhone(phone);
                EmailSend emailSend = new EmailSend();
                emailSend.setEmail(email);
                final ProgressDialog progress = new ProgressDialog(this);
                progress.setTitle("Please Wait");
                progress.setMessage("Sending Verification Code To Your Email");
                progress.setCancelable(false);
                progress.show();

                Call<ConfSend> sendCall = service.sendConfirmation(emailSend);
                sendCall.enqueue(new Callback<ConfSend>() {
                    @Override
                    public void onResponse(Call<ConfSend> call, Response<ConfSend> response) {
                        Intent intent = new Intent(RegisterActivity.this,VerificationActivity.class);
                        intent.putExtra("register", register);
                        startActivity(intent);
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ConfSend> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this,"Check Your Connection",Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                });


            }else{
                Toast.makeText(RegisterActivity.this,"Password minimum 6 character",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(RegisterActivity.this,"Your Email is Not Email Format",Toast.LENGTH_LONG).show();
        }

    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
