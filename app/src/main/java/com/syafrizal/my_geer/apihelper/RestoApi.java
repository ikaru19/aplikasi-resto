package com.syafrizal.my_geer.apihelper;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RestoApi {
    private static final String API_URL = "xxxx";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Service service = retrofit.create(Service.class);

    public interface Service {
//  METHOD
//        @GET("apks/resource/")
//        Call<Results> fetch();

    }
}
