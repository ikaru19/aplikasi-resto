package com.syafrizal.my_geer.Fragments;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.syafrizal.my_geer.Adapter.TransactionAdapter;
import com.syafrizal.my_geer.Model.Booking;
import com.syafrizal.my_geer.Model.BookingDishes;
import com.syafrizal.my_geer.Model.Dish;
import com.syafrizal.my_geer.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransDetailFragment extends Fragment {
    Booking booking;
    List<BookingDishes> dishes;
    RecyclerView recyclerView;

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
        ImageView imgQRcode = view.findViewById(R.id.iv_trans_qr);
        recyclerView = view.findViewById(R.id.rv_trans);

        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy HH:mm");
        String dateString = format.format(booking.getCreated_at());

        txtResName.setText(booking.getRestaurant().getName());
        txtCode.setText(Integer.toString(booking.getId()));
        txtStatus.setText("ORDER "+booking.getStatus().toUpperCase());
        txtPrice.setText("Rp "+Integer.toString(booking.getTotal_price()));
        txtNotes.setText(booking.getNotes());
        txtDate.setText(dateString);
        txtServe.setText(booking.getServing_type());
        txtPayment.setText(booking.getPayment_type());

        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(booking.getId().toString(), BarcodeFormat.QR_CODE, 150, 150);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            imgQRcode.setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        dishes = booking.getBooking_dish();


        TransactionAdapter adapter = new TransactionAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter.setBookingDishes(dishes);
        adapter.setDishes(booking.getDish());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


        return view;
    }

}
