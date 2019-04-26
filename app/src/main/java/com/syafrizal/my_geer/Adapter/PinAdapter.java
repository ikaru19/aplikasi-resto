package com.syafrizal.my_geer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syafrizal.my_geer.Model.Menu;
import com.syafrizal.my_geer.Model.Location;
import com.syafrizal.my_geer.R;

import java.util.List;

public class PinAdapter extends RecyclerView.Adapter<PinAdapter.ViewHolder> {
    Context context;
    private List<Location> locations;
    public OnAdapterClickListener listener;

    public interface OnAdapterClickListener{
        void DetailonClick(Location location);
    }


    public PinAdapter(Context context) {
        this.context = context;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
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
        Location location = locations.get(i);
        viewHolder.txtName.setText(location.getRestaurant().getName());
        viewHolder.txtAddr.setText(location.getAddress());
        Picasso.get().load(location.getRestaurant().getImage()).into(viewHolder.imgResto);

    }

    @Override
    public int getItemCount() {
        return (locations != null) ? locations.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtAddr;
        ImageView imgResto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tv_pin_restaurant_name);
            txtAddr = itemView.findViewById(R.id.tv_pin_restaurant_addr);
            imgResto = itemView.findViewById(R.id.iv_pin_restaurant);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.DetailonClick(locations.get(getAdapterPosition()));
                }
            });

        }
    }
}
