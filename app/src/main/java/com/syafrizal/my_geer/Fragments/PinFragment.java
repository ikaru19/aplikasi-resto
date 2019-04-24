package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syafrizal.my_geer.Activities.MainActivity;
import com.syafrizal.my_geer.Adapter.PinAdapter;
import com.syafrizal.my_geer.Model.Restaurant;
import com.syafrizal.my_geer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PinFragment extends Fragment  implements PinAdapter.OnAdapterClickListener{
    RecyclerView recyclerView;
    List<Restaurant> restaurants = new ArrayList<>();

    public PinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pin, container, false);
        recyclerView = view.findViewById(R.id.rv_locations);
        initData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        PinAdapter adapter = new PinAdapter(getContext());

        adapter.setRestaurants(restaurants);
        adapter.setListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);



        return view;
    }

    public void initData(){
        restaurants.add(new Restaurant("bakso","jl Panderman","test"));
        restaurants.add(new Restaurant("bakso","jl Panderman","test"));
        restaurants.add(new Restaurant("bakso","jl Panderman","test"));
        restaurants.add(new Restaurant("bakso","jl Panderman","test"));
        restaurants.add(new Restaurant("bakso","jl Panderman","test"));
        restaurants.add(new Restaurant("bakso","jl Panderman","test"));
        restaurants.add(new Restaurant("bakso","jl Panderman","test"));
        restaurants.add(new Restaurant("bakso","jl Panderman","test"));
    }

    @Override
    public void DetailonClick(Restaurant restaurant) {
        ((MainActivity) getActivity()).addFragment("home");
    }
}
