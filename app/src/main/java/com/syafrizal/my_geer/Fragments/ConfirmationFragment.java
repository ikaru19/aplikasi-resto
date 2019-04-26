package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.syafrizal.my_geer.Adapter.OrdersAdapter;
import com.syafrizal.my_geer.Model.Booking;
import com.syafrizal.my_geer.Model.OrderMenu;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.apihelper.RestoApi;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmationFragment extends Fragment implements View.OnClickListener, Callback<Booking> {
    List<OrderMenu> orders = new ArrayList<>();
    String restaurantName;
    Spinner spinnerPayment,spinnerServe;
    TextView editText;
    Integer locationId ;
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
        Booking booking = new Booking();
        booking.setLocations_id(locationId);
        booking.setTotal_price(total);
        booking.setStatus("placed");
        booking.setNotes(editText.getText().toString());
        booking.setServing_type(spinnerServe.getSelectedItem().toString());
        booking.setPayment_type(spinnerPayment.getSelectedItem().toString());
        Call<Booking> bookingCall = RestoApi.services().books(booking);
        bookingCall.enqueue(this );
    }

    @Override
    public void onResponse(Call<Booking> call, Response<Booking> response) {
//        !todo beri response handler
    }

    @Override
    public void onFailure(Call<Booking> call, Throwable t) {
        // !todo beri error handler
    }
}
