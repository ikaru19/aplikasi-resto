package com.syafrizal.my_geer.apihelper;

import com.syafrizal.my_geer.Model.Login;
import com.syafrizal.my_geer.Model.Regist;
import com.syafrizal.my_geer.Model.Register;
import com.syafrizal.my_geer.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

    @POST("users/login")
    Call<User> login(@Body Login login);

    @POST("users")
    Call<Regist> regiter(@Body Register register);

}
