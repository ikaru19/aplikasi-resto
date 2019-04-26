package com.syafrizal.my_geer.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.syafrizal.my_geer.Activities.EmptyActivity;
import com.syafrizal.my_geer.Activities.MainActivity;
import com.syafrizal.my_geer.Adapter.OrdersAdapter;
import com.syafrizal.my_geer.Model.Booking;
import com.syafrizal.my_geer.Model.BookingDishes;
import com.syafrizal.my_geer.Model.Constant;
import com.syafrizal.my_geer.Model.OrderMenu;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.apihelper.RestoApi;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmationFragment extends Fragment implements View.OnClickListener {
    List<OrderMenu> orders = new ArrayList<>();
    String restaurantName;
    Spinner spinnerPayment,spinnerServe;
    TextView editText;
    Integer locationId ;
    SharedPreferences preferences;
    Button buttonOrder;

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    RecyclerView recyclerView;

    public void setOrders(List<OrderMenu> orders) {
        this.orders = orders;
    }

    public ConfirmationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_confirmation, container, false);
        recyclerView = view.findViewById(R.id.rv_orders);
        buttonOrder = view.findViewById(R.id.btn_conf_order);
        OrdersAdapter adapter = new OrdersAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        TextView txtResName = view.findViewById(R.id.tv_conf_resname);
        editText = view.findViewById(R.id.editText);
        spinnerPayment = view.findViewById(R.id.spinnerPayment);
        spinnerServe = view.findViewById(R.id.spinnerServe);
        txtResName.setText(restaurantName);
        adapter.setOrders(orders);

        buttonOrder.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        preferences = this.getActivity().getSharedPreferences(Constant.SHARED_PREF, Context.MODE_PRIVATE);


        return view;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Override
    public void onClick(View view) {
        int total = 0;
        for(OrderMenu o : orders){
            total += o.getPrice() *o.getTotal();
        }
        final Booking booking = new Booking();
        booking.setLocations_id(locationId);
        booking.setTotal_price(total);
        booking.setStatus("placed");
        booking.setNotes(editText.getText().toString());
        booking.setServing_type(spinnerServe.getSelectedItem().toString());
        booking.setPayment_type(spinnerPayment.getSelectedItem().toString());
        Call<Booking> bookingCall = RestoApi.services().books(booking, (String) Paper.book().read(Constant.TOKEN_PREF));
        bookingCall.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.code()==401){
                    preferences.edit().clear().commit();
                    Toast.makeText(getContext(),"You need to login again",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), EmptyActivity.class);
                    startActivity(intent);
                    getActivity().finish();

                }else{
                    bookingDish(0,response.body().getId());
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                Toast.makeText(getContext(),"Check your connection",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void bookingDish(int index, final int bookingId){

            OrderMenu o = orders.get(index);
            final BookingDishes bookingDishes = new BookingDishes();
            bookingDishes.setDish_id(o.getId());
            bookingDishes.setBooking_id(bookingId);
            bookingDishes.setQuantity(o.getTotal());
            Call<BookingDishes> bookingDishesCall = RestoApi.services().bookDishes(bookingDishes);
            final int finalIndex =index +1;
            if(index == orders.size() -1){
                bookingDishesCall.enqueue(new Callback<BookingDishes>() {
                    @Override
                    public void onResponse(Call<BookingDishes> call, Response<BookingDishes> response) {
                        //todo success response jika berhasil order disini
                        Toast.makeText(getContext(),"Order Placed",Toast.LENGTH_SHORT).show();
                        ((MainActivity)getActivity()).addFragment("list");
                    }

                    @Override
                    public void onFailure(Call<BookingDishes> call, Throwable t) {
                        Toast.makeText(getContext(),"Check your connection",Toast.LENGTH_SHORT).show();

                    }
                });
            }else {
                bookingDishesCall.enqueue(new Callback<BookingDishes>() {
                    @Override
                    public void onResponse(Call<BookingDishes> call, Response<BookingDishes> response) {
                        bookingDish(finalIndex, bookingId);
                    }

                    @Override
                    public void onFailure(Call<BookingDishes> call, Throwable t) {
                        Toast.makeText(getContext(),"Check your connection",Toast.LENGTH_SHORT).show();

                    }
                });
            }
    }

}
