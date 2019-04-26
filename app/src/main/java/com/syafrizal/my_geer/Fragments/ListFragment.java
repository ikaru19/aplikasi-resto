package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.syafrizal.my_geer.Activities.MainActivity;
import com.syafrizal.my_geer.Adapter.ListsAdapter;
import com.syafrizal.my_geer.Model.Order;
import com.syafrizal.my_geer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements ListsAdapter.OnAdapterClickListener{

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


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        ListsAdapter adapter = new ListsAdapter(getContext());

        adapter.setOrders(orders);
        adapter.setListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    public void initData(){
        Call<Order>
    }

    @Override
    public void DetailonClick(Order order) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,new TransDetailFragment())
                .addToBackStack("tag").commit();
    }
}
