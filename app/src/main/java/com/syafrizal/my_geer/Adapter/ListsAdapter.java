package com.syafrizal.my_geer.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syafrizal.my_geer.Model.Order;
import com.syafrizal.my_geer.R;

import java.util.List;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ViewHolder> {
    private Context context;
    private List<Order> orders;

    public ListsAdapter(Context context) {
        this.context = context;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
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
        Order order = orders.get(i);
        if (order.getStatus().equalsIgnoreCase("Order Placed")){
            viewHolder.txtStatus.setTextColor(Color.rgb(201,226,101));
        }
        viewHolder.txtStatus.setText(order.getStatus());
        viewHolder.txtRestoName.setText(order.getName());
        viewHolder.txtAddres.setText(order.getAddress());

    }

    @Override
    public int getItemCount() {
        return (orders != null) ? orders.size() : 0;
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
        }
    }
}
