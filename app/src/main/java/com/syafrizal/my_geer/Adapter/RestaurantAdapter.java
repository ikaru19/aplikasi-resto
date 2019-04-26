package com.syafrizal.my_geer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syafrizal.my_geer.Model.Dish;
import com.syafrizal.my_geer.Model.Menu;
import com.syafrizal.my_geer.Model.Order;
import com.syafrizal.my_geer.Model.OrderMenu;
import com.syafrizal.my_geer.R;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    Context context;
    List<Dish> menus;
    public OnAdapterClickListener listener;

    public RestaurantAdapter(Context context) {
        this.context = context;
    }

    public void setMenus(List<Dish> menus) {
        this.menus = menus;
        notifyDataSetChanged();
    }

    public interface OnAdapterClickListener{
        void DetailonClick(Dish menu);
    }

    @NonNull
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_menu,viewGroup,false);
        return new RestaurantAdapter.ViewHolder(view);
    }

    public void setListener(OnAdapterClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.ViewHolder viewHolder, int i) {
        Dish dish = menus.get(i);
        viewHolder.txtJudul.setText(dish.getName());
        viewHolder.txtDesc.setText(dish.getDescription());
        viewHolder.txtHarga.setText("Rp. " + Integer.toString(dish.getPrice()));

    }

    @Override
    public int getItemCount() {
        return (menus == null) ? 0 : menus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul , txtDesc , txtHarga;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.tv_judul_menu);
            txtDesc = itemView.findViewById(R.id.tv_desc_menu);
            txtHarga = itemView.findViewById(R.id.tv_harga_menu);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.DetailonClick(menus.get(getAdapterPosition()));
                }
            });
        }
    }
}
