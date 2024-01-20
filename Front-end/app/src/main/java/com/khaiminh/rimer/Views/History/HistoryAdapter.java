package com.khaiminh.rimer.Views.History;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khaiminh.rimer.Model.Booking;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{

//    private List<User> user;

    private List<Booking> bookingList;

    public HistoryAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
//        this.user = user;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_history_details,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking History = bookingList.get(position);

        // Need to change the getFunction
        holder.driverName.setText(History.getDriverId());
        holder.userName.setText(History.getUserId());
//        holder.userImg
//        holder.driverImg

        holder.price.setText(String.valueOf(History.getPrice()));

        holder.startPoint.setText(History.getStartPoint());
        holder.endPoint.setText(History.getEndPoint());
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }
//    @Override
//    public int getItemCount() {
//        return user.size();
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView driverName, startPoint, endPoint, userName, price;
        ImageView driverImg, userImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Driver
            driverName = itemView.findViewById(R.id.driverName);
            driverImg = itemView.findViewById(R.id.driverImage);
            startPoint = itemView.findViewById(R.id.startPoint);
            endPoint = itemView.findViewById(R.id.endPoint);

            // User
            userName = itemView.findViewById(R.id.userName);
            userImg = itemView.findViewById(R.id.userImage);

            price = itemView.findViewById(R.id.priceText);
        }
    }
}
