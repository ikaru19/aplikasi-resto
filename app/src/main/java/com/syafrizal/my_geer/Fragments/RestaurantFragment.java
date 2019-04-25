package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.syafrizal.my_geer.Adapter.RestaurantAdapter;
import com.syafrizal.my_geer.Model.Dish;
import com.syafrizal.my_geer.Model.Menu;
import com.syafrizal.my_geer.Model.Restaurant;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.apihelper.RestaurantServices;
import com.syafrizal.my_geer.apihelper.RestoApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment {

    RecyclerView recyclerView;
    List<Dish> menus = new ArrayList<>();
    Restaurant restaurant;
    private RestaurantServices service;
    private RestaurantAdapter adapter;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        recyclerView = view.findViewById(R.id.rv_menus);

        TextView textView = view.findViewById(R.id.tv_restaurant_name_menu);
        textView.setText(restaurant.getName());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new RestaurantAdapter(getContext());
        getData();


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = RestoApi.createService(RestaurantServices.class);

    }

    public void getData(){

        Call<Restaurant> restaurantCall = service.getRestaurant(restaurant.getId());
        restaurantCall.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                    Restaurant restaurant = response.body();
                    List<Dish> menus = restaurant.getDishes();
                    adapter.setMenus(menus);
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Toast.makeText(getContext(),"Check your connection",Toast.LENGTH_SHORT).show();

            }
        });

    }

}
