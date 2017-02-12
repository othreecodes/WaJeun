package com.othree.wajeun.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.othree.wajeun.R;
import com.othree.wajeun.models.Notification;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 2/12/17.
 */

public class NotificationFragmentAdapter extends RecyclerView.Adapter<NotificationFragmentAdapter.ViewHolder>{

    List<Notification> notifications;
    public NotificationFragmentAdapter(List<Notification> notifications){
        this.notifications = notifications;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_notification, parent, false);
        return new NotificationFragmentAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.body.setText(notifications.get(position).getMessage());
        holder.title.setText(notifications.get(position).getTitle());
        String date = notifications.get(position).getDate();
//        SimpleDateFormat format = new SimpleDateFormat(date);
//        format.applyPattern("%d %m %y");
        holder.date.setText(date.substring(0,20));


    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.body)
        TextView body;
        @Bind(R.id.date) TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);


        }
    }



}
