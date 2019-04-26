package com.syafrizal.my_geer.apihelper;

import com.syafrizal.my_geer.Model.Booking;
import com.syafrizal.my_geer.Model.BookingDishes;
import com.syafrizal.my_geer.Model.Location;
import com.syafrizal.my_geer.Model.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantServices {

//    @GET("/restaurants")
//    Call<Restaurant> getRestaurant();

    @GET("/restaurants/{id}")
    Call<Restaurant> getRestaurant(@Path("id") String id);

    @GET("/search")
    Call<List<Location>> search(@Query("q") String query);

    @POST("/bookings")
    Call<Booking> books(@Body Booking booking, @Header("Authorization") String auth);

    @POST("/booking_dishes")
    Call<BookingDishes> bookDishes(@Body BookingDishes booking);

    @GET("/my_bookings")
    Call<Booking> myBookings(@Header("Authorization") String auth);
}
