package com.syafrizal.my_geer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syafrizal.my_geer.Model.Booking;
import com.syafrizal.my_geer.R;

import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransDetailFragment extends Fragment {
    Booking booking;

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public TransDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trans_detail, container, false);
        TextView txtResName = view.findViewById(R.id.tv_trans_resname);
        TextView txtCode = view.findViewById(R.id.tv_trans_code);
        TextView txtStatus = view.findViewById(R.id.tv_trans_status);
        TextView txtPrice = view.findViewById(R.id.tv_trans_price);
        TextView txtDate = view.findViewById(R.id.tv_trans_date);
        TextView txtNotes = view.findViewById(R.id.tv_trans_notes);
        TextView txtServe = view.findViewById(R.id.tv_trans_serve);
        TextView txtPayment = view.findViewById(R.id.tv_trans_payment);

        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy HH:mm");
        String dateString = format.format(booking.getCreated_at());

        txtResName.setText(booking.getRestaurant().getName());
        txtCode.setText("948813131");
        txtStatus.setText(booking.getStatus());
        txtPrice.setText("Rp "+Integer.toString(booking.getTotal_price()));
        txtNotes.setText(booking.getNotes());
        txtDate.setText(dateString);
        txtServe.setText(booking.getServing_type());
        txtPayment.setText(booking.getPayment_type());

        return view;
    }

}
