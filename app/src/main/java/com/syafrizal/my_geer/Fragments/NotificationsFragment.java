package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syafrizal.my_geer.Adapter.NotificationsAdapter;
import com.syafrizal.my_geer.Model.Notification;
import com.syafrizal.my_geer.R;
import com.syafrizal.my_geer.config.Constants;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Notification> notifications = new ArrayList<>();


    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        recyclerView = view.findViewById(R.id.rv_notifications);
        initDataset();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        NotificationsAdapter adapter = new NotificationsAdapter(getContext());
        adapter.setNotifications(notifications);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



        return view;
    }

    public void initDataset(){
        List<Notification> notificationList = Paper.book().read(Constants.PaperDB.NOTIFICATIONS);
        notifications.addAll(notificationList);
    }

}
