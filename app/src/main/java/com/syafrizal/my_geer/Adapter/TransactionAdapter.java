package com.syafrizal.my_geer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syafrizal.my_geer.Model.BookingDishes;
import com.syafrizal.my_geer.Model.Dish;
import com.syafrizal.my_geer.R;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    Context context;
    List<BookingDishes> bookingDishes;
    List<Dish> dishes;

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public TransactionAdapter(Context context) {
        this.context = context;
    }

    public void setBookingDishes(List<BookingDishes> bookingDishes) {
        this.bookingDishes = bookingDishes;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_order,viewGroup,false);
        return new TransactionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder viewHolder, int i) {
        BookingDishes booking = bookingDishes.get(i);
        Dish dish = getDishById(booking.getDish_id());

        viewHolder.txtName.setText(dish.getName());
        viewHolder.txtQty.setText(Integer.toString(booking.getQuantity()));
        viewHolder.txtPrice.setText(Integer.toString(dish.getPrice()));
    }

    @Override
    public int getItemCount() {return (bookingDishes == null) ? 0 : bookingDishes.size();
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

    private Dish getDishById(int id){
        for (Dish dish:dishes){
            if (dish.getId() == id){
                return dish;
            }
        }

        return null;
    }
}
