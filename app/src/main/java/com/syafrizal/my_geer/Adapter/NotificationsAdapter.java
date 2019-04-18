package com.syafrizal.my_geer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syafrizal.my_geer.Model.Notification;
import com.syafrizal.my_geer.R;

import org.w3c.dom.Text;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private List<Notification> notifications;
    private Context context;

    public NotificationsAdapter(Context context) {
        this.context = context;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_notifications,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Notification notification = notifications.get(i);
        viewHolder.txtTitle.setText(notification.getTitle());
        viewHolder.txtBody.setText(notification.getBody());

    }

    @Override
    public int getItemCount() {
        return (notifications != null) ? notifications.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtBody;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvNotifTitle);
            txtBody = itemView.findViewById(R.id.tvNotifBody);
        }
    }
}
