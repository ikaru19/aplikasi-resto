package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syafrizal.my_geer.Adapter.RestaurantAdapter;
import com.syafrizal.my_geer.Model.Menu;
import com.syafrizal.my_geer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment {

    RecyclerView recyclerView;
    List<Menu> menus = new ArrayList<>();


    public RestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        recyclerView = view.findViewById(R.id.rv_menus);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RestaurantAdapter adapter = new RestaurantAdapter(getContext());
        getData();

        adapter.setMenus(menus);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }


    public void getData(){
        menus.add(new Menu("bandeng","bandeng",15000));
        menus.add(new Menu("bandeng","bandeng",15000));
        menus.add(new Menu("bandeng","bandeng",15000));
        menus.add(new Menu("bandeng","bandeng",15000));


    }

}
