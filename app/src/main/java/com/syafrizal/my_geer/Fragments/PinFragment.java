package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class PinFragment extends Fragment  implements PinAdapter.OnAdapterClickListener, Callback<List<Location>> {
    RecyclerView recyclerView;
    List<Location> locations = new ArrayList<>();

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
        initData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new PinAdapter(getContext());

        adapter.setLocations(locations);
        adapter.setListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);



        return view;
    }

    public void initData(){
        Call<List<Location>> request =RestoApi.services().search("");
        request.enqueue(this);
    }

    @Override
    public void DetailonClick(Location restaurant) {
        ((MainActivity) getActivity()).addFragment("home");
    }

    @Override
    public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
        locations.addAll(response.body());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onFailure(Call<List<Location>> call, Throwable t) {
        Log.d("tes", String.valueOf("fail"));
    }
}
