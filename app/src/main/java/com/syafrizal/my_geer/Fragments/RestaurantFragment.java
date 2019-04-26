package com.syafrizal.my_geer.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.syafrizal.my_geer.Adapter.RestaurantAdapter;
import com.syafrizal.my_geer.Model.Dish;
import com.syafrizal.my_geer.Model.Location;
import com.syafrizal.my_geer.Model.Menu;
import com.syafrizal.my_geer.Model.OrderMenu;
import com.syafrizal.my_geer.Model.Restaurant;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.apihelper.RestaurantServices;
import com.syafrizal.my_geer.apihelper.RestoApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.DEFAULT_KEYS_DIALER;
import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment implements RestaurantAdapter.OnAdapterClickListener {

    RecyclerView recyclerView;
    TextView txtQty;
    Button btnConfirm;

    List<Dish> menus = new ArrayList<>();
    List<OrderMenu> orders = new ArrayList<>();

    Location  location;
    Restaurant  restaurant;


    private RestaurantServices service;
    private RestaurantAdapter adapter;

    int qty;

    public RestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        recyclerView = view.findViewById(R.id.rv_menus);
        service = RestoApi.createService(RestaurantServices.class);
        TextView textView = view.findViewById(R.id.tv_restaurant_name_menu);
        textView.setText(restaurant.getName());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new RestaurantAdapter(getContext());
        adapter.setListener(this);
        getData();



        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        btnConfirm = view.findViewById(R.id.btn_conf_menu);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ConfirmationFragment();
                ((ConfirmationFragment) fragment).setOrders(orders);
                ((ConfirmationFragment) fragment).setRestaurantName(restaurant.getName());
                ((ConfirmationFragment) fragment).setLocationId(location.getId());
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,fragment)
                        .addToBackStack("tag").commit();
            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Override
    public void DetailonClick(final Dish menu) {
        View alertLayout = getLayoutInflater().inflate(R.layout.dialog_qty,null);
        final Button btnplus = alertLayout.findViewById(R.id.buttonPlus);
        final Button btnmin = alertLayout.findViewById(R.id.buttonMinus);
        txtQty = alertLayout.findViewById(R.id.textViewQuantity);
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(alertLayout);

        btnmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty > 0){
                    qty = qty-1;
                    txtQty.setText(Integer.toString(qty));
                }
            }
        });

        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                qty = qty+1;
                txtQty.setText(Integer.toString(qty));

            }
        });


        alert.setTitle("Quantity");
        alert.setMessage("Enter Quantity");

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                orders.add(new OrderMenu(menu.getId(),menu.getName(),qty,menu.getPrice()));
                qty = 0;
                Toast.makeText(getContext(),"Added to Cart",Toast.LENGTH_SHORT).show();
            }
        });


        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // do nothing
            }
        });
        alert.show();
    }


    public void setLocation(Location location) {
        this.location = location;
        this.restaurant = location.getRestaurant();
    }
}
