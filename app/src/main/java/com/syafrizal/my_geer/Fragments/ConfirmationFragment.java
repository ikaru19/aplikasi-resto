package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.syafrizal.my_geer.Adapter.OrdersAdapter;
import com.syafrizal.my_geer.Model.OrderMenu;
import com.syafrizal.my_geer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmationFragment extends Fragment {
    List<OrderMenu> orders = new ArrayList<>();
    String restaurantName;

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
        OrdersAdapter adapter = new OrdersAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        TextView txtResName = view.findViewById(R.id.tv_conf_resname);
        txtResName.setText(restaurantName);
        adapter.setOrders(orders);
        

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);



        return view;
    }

}
