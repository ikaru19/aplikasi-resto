package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.syafrizal.my_geer.Activities.MainActivity;
import com.syafrizal.my_geer.Adapter.ListsAdapter;
import com.syafrizal.my_geer.Model.Booking;
import com.syafrizal.my_geer.Model.Constant;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.apihelper.RestoApi;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements ListsAdapter.OnAdapterClickListener{

    RecyclerView recyclerView;
    List<Booking> bookings = new ArrayList<>();

    ListsAdapter adapter;
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
        adapter = new ListsAdapter(getContext());

        adapter.setBookings(bookings);
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
        Call<List<Booking>> bookingCall = RestoApi.services().myBookings((String) Paper.book().read(Constant.TOKEN_PREF));
        bookingCall.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                bookings.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                Toast.makeText(getContext(),"Please Check Your Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void DetailonClick(Booking booking) {
        Fragment fragment = new TransDetailFragment();
        ((TransDetailFragment) fragment).setBooking(booking);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack("tag").commit();
    }
}
