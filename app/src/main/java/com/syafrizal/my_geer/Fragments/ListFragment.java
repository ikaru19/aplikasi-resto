package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.syafrizal.my_geer.Adapter.ListsAdapter;
import com.syafrizal.my_geer.Model.Order;
import com.syafrizal.my_geer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    List<Order> orders = new ArrayList<>();


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.rvList);
        initData();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        ListsAdapter adapter = new ListsAdapter(getContext());

        adapter.setOrders(orders);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);



        return view;
    }

    public void initData(){
        orders.add(new Order("Bakso Enak","jl Kisamaun No 17 Tangerang","Order Placed"));
        orders.add(new Order("Bakso Enak","jl Kisamaun No 17 Tangerang","Order Completed"));
        orders.add(new Order("Bakso Enak","jl Kisamaun No 17 Tangerang","Order Completed"));
        orders.add(new Order("Bakso Enak","jl Kisamaun No 17 Tangerang","Order Completed"));
    }
}
