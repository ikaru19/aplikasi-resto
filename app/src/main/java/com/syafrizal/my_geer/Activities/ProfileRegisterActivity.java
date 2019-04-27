package com.syafrizal.my_geer.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.syafrizal.my_geer.Model.Constant;
import com.syafrizal.my_geer.Model.Regist;
import com.syafrizal.my_geer.Model.Register;
import com.syafrizal.my_geer.Model.User;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.apihelper.RestoApi;
import com.syafrizal.my_geer.apihelper.UserClient;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRegisterActivity extends AppCompatActivity {
    final Calendar myCalendar = Calendar.getInstance();
    EditText birthdayTxt,nameTxt;
    Spinner spinnerGender;
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    Register register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_register);
        birthdayTxt = findViewById(R.id.et_birthdayregister);
        nameTxt = findViewById(R.id.et_nameregister);
        spinnerGender = findViewById(R.id.spinnerGender);
        register = (Register) getIntent().getSerializableExtra("register");
    }

    public void birthdayOnClick(View view) {
        new DatePickerDialog(this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        birthdayTxt.setText(sdf.format(myCalendar.getTime()));
    }


    public void registerFinal(View view) {
        String name = nameTxt.getText().toString();

        if (checkName(name)){
            Toast.makeText(ProfileRegisterActivity.this,"Please Enter your name",Toast.LENGTH_LONG).show();
        }else {
            register.setName(nameTxt.getText().toString());
            register.setGender(String.valueOf(spinnerGender.getSelectedItem()));
            register.setRole("user");
            register.setStatus("status");

            UserClient service = RestoApi.createService(UserClient.class);
            Call<Regist> userCall = service.register(register);
            userCall.enqueue(new Callback<Regist>() {
                @Override
                public void onResponse(Call<Regist> call, Response<Regist> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ProfileRegisterActivity.this, "Register Succes", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfileRegisterActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(ProfileRegisterActivity.this, jObjError.getString("errors"), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(ProfileRegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Regist> call, Throwable t) {
                    Toast.makeText(ProfileRegisterActivity.this, "Check Your Connection", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    public boolean checkName(String name){
        if (name.matches("")){
            return true;
        }else{
            return false;
        }
    }
}
