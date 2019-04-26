package com.syafrizal.my_geer.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syafrizal.my_geer.Model.Location;
import com.syafrizal.my_geer.Model.Booking;
import com.syafrizal.my_geer.R;

import java.util.List;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ViewHolder> {
    private Context context;
    private List<Booking> bookings;
    public OnAdapterClickListener listener;

    public ListsAdapter(Context context) {
        this.context = context;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
        notifyDataSetChanged();
    }

    public interface OnAdapterClickListener{
        void DetailonClick(Booking booking);
    }

    public void setListener(OnAdapterClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_list,viewGroup,false);
        return new ListsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListsAdapter.ViewHolder viewHolder, int i) {
        Booking booking = bookings.get(i);
        if (booking.getStatus().equalsIgnoreCase("placed")){
            viewHolder.txtStatus.setTextColor(Color.rgb(201,226,101));
        }
        viewHolder.txtStatus.setText(getStatusText(booking.getStatus()));
        viewHolder.txtRestoName.setText(booking.getRestaurant().getName());
        viewHolder.txtAddres.setText(booking.getLocation().getAddress());

    }

    private String getStatusText(String status) {
        switch (status){
            case "placed":
                return "Order Placed";
            case "completed":
                return "Order Completed";
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return (bookings != null) ? bookings.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStatus;
        TextView txtRestoName;
        TextView txtAddres;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStatus = itemView.findViewById(R.id.tvListStatus);
            txtRestoName = itemView.findViewById(R.id.tvListName);
            txtAddres = itemView.findViewById(R.id.tvListAddres);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.DetailonClick(bookings.get(getAdapterPosition()));
                }
            });

        }
    }
}
