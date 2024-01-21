package com.khaiminh.rimer.Views.Admin.DriverListView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;

import java.util.List;

public class DriverDataAdapter extends RecyclerView.Adapter<DriverDataAdapter.ViewHolder>{

    private List<User> userList;
    public DriverDataAdapter(List<User> userList) {
        this.userList = userList;
    }
    @NonNull
    @Override
    public DriverDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_driver_list,parent,false);
        return new DriverDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverDataAdapter.ViewHolder holder, int position) {
        User driver = userList.get(position);

        holder.driverName.setText(driver.getName());
        holder.driverId.setText(driver.getId());
//        holder.userImg
//        holder.driverImg

        holder.driverEmail.setText(String.valueOf(driver.getEmail()));

        holder.driverType.setText(driver.getUserType());
        holder.driverPassword.setText(driver.getPassword());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView driverName, driverId, driverEmail, driverType, driverPassword;
        ImageView driverImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            driverName = itemView.findViewById(R.id.userName);
            driverId = itemView.findViewById(R.id.userId);
            driverEmail = itemView.findViewById(R.id.emailTextView);
            driverType = itemView.findViewById(R.id.userType);
            driverPassword = itemView.findViewById(R.id.passwordView);

            driverImg = itemView.findViewById(R.id.userImage);

        }
    }
}
