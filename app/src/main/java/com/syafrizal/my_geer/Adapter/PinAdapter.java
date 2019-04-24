package com.syafrizal.my_geer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syafrizal.my_geer.Model.Menu;
import com.syafrizal.my_geer.Model.Restaurant;
import com.syafrizal.my_geer.R;

import java.util.List;

public class PinAdapter extends RecyclerView.Adapter<PinAdapter.ViewHolder> {
    Context context;
    private List<Restaurant> restaurants;
    public OnAdapterClickListener listener;

    public interface OnAdapterClickListener{
        void DetailonClick(Restaurant restaurant);
    }


    public PinAdapter(Context context) {
        this.context = context;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    public void setListener(OnAdapterClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_location,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Restaurant restaurant = restaurants.get(i);
        viewHolder.txtName.setText(restaurant.getName());
        viewHolder.txtAddr.setText(restaurant.getAddress());

    }

    @Override
    public int getItemCount() {
        return (restaurants != null) ? restaurants.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtAddr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tv_pin_restaurant_name);
            txtAddr = itemView.findViewById(R.id.tv_pin_restaurant_addr);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.DetailonClick(restaurants.get(getAdapterPosition()));
                }
            });

        }
    }
}
