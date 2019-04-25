package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.syafrizal.my_geer.Activities.MainActivity;
import com.syafrizal.my_geer.Adapter.PinAdapter;
import com.syafrizal.my_geer.Model.Location;
import com.syafrizal.my_geer.Model.Restaurant;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.apihelper.RestoApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PinFragment extends Fragment  implements PinAdapter.OnAdapterClickListener, Callback<List<Location>> , SearchView.OnQueryTextListener{
    RecyclerView recyclerView;
    List<Location> locations = new ArrayList<>();
    SearchView searchView;

    PinAdapter adapter;
    public PinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pin, container, false);
        recyclerView = view.findViewById(R.id.rv_locations);
//        initData();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new PinAdapter(getContext());

        adapter.setLocations(locations);
        adapter.setListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        searchView = view.findViewById(R.id.pin_search);
        searchView.setOnQueryTextListener(this);



        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData("");


    }

    public void initData(String key){
        Call<List<Location>> request =RestoApi.services().search(key);
        request.enqueue(this);
    }

    @Override
    public void DetailonClick(Location location) {
        Fragment fragment = new RestaurantFragment();
        ((RestaurantFragment) fragment).setRestaurant(location.getRestaurant());
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack("tag").commit();
    }

    @Override
    public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
        locations.clear();
        locations.addAll(response.body());
        adapter.setLocations(locations);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onFailure(Call<List<Location>> call, Throwable t) {
        Log.d("tes", String.valueOf("fail"));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        recyclerView.setVisibility(View.GONE);
        initData(newText);

        recyclerView.setVisibility(View.VISIBLE);


        return false;
    }
}
