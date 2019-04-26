package com.syafrizal.my_geer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syafrizal.my_geer.Model.OrderMenu;
import com.syafrizal.my_geer.R;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    Context context;
    List<OrderMenu> orders;

    public OrdersAdapter(Context context) {
        this.context = context;
    }

    public void setOrders(List<OrderMenu> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_order,viewGroup,false);
        return new OrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder viewHolder, int i) {
        OrderMenu orderMenu = orders.get(i);
        viewHolder.txtName.setText(orderMenu.getName());
        viewHolder.txtQty.setText(Integer.toString(orderMenu.getTotal()) + "x");
        viewHolder.txtPrice.setText("Rp."+Integer.toString(orderMenu.getPrice()));
    }

    @Override
    public int getItemCount() {
        return (orders == null) ? 0 : orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtQty ;
        TextView txtName;
        TextView txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tv_conf_name);
            txtQty = itemView.findViewById(R.id.tv_conf_qty);
            txtPrice = itemView.findViewById(R.id.tv_conf_price);
        }
    }
}
