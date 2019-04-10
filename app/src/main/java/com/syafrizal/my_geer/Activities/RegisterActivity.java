package com.syafrizal.my_geer.Activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.syafrizal.my_geer.Model.Regist;
import com.syafrizal.my_geer.Model.Register;
import com.syafrizal.my_geer.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText txtUsername , txtPassword , txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtUsername = findViewById(R.id.etUsernameRegister);
        txtEmail = findViewById(R.id.etEmailRegister);
        txtPassword = findViewById(R.id.etPasswordRegister);

    }

    public void registerConOnClick(View view) {
        Intent intent = new Intent(this,VerificationActivity.class);
        Register register = new Register();
        String password = txtPassword.getText().toString();
        String email = txtEmail.getText().toString();
        String username = txtUsername.getText().toString();

        if (isEmailValid(email)){
            register.setEmail(email);
            if (password.length()>=6){
                register.setPassword(password);
                register.setPassword_confirm(password);
                register.setUsername(username);
                intent.putExtra("register", register);
                startActivity(intent);
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
