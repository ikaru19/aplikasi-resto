package com.syafrizal.my_geer.apihelper;

import com.syafrizal.my_geer.Model.CheckResult;
import com.syafrizal.my_geer.Model.Code;
import com.syafrizal.my_geer.Model.ConfSend;
import com.syafrizal.my_geer.Model.EmailSend;
import com.syafrizal.my_geer.Model.Login;
import com.syafrizal.my_geer.Model.Regist;
import com.syafrizal.my_geer.Model.Register;
import com.syafrizal.my_geer.Model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

    @POST("users/login")
    Call<User> login(@Body Login login);

    @POST("users")
    Call<Regist> register(@Body Register register);

    @POST("confirmation/send")
    Call<ConfSend> sendConfirmation(@Body EmailSend emailSend);

    @POST("confirmation/check")
    Call<ArrayList<CheckResult>> getConfirmation(@Body Code code);

}
