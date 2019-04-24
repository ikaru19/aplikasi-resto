package com.syafrizal.my_geer.apihelper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Restaurant {

    @GET("/restaurants")
    Call<Restaurant> getRestaurant();
}
