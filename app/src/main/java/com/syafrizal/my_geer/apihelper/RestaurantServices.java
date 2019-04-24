package com.syafrizal.my_geer.apihelper;

import com.syafrizal.my_geer.Model.Location;
import com.syafrizal.my_geer.Model.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestaurantServices {

    @GET("/restaurants")
    Call<Restaurant> getRestaurant();

    @GET("/search")
    Call<List<Location>> search(@Query("q") String query);
}
