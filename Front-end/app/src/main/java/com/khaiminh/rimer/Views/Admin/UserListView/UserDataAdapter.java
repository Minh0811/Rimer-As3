package com.khaiminh.rimer.Views.Admin.UserListView;

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

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.ViewHolder>{

    private List<User> userList;
    public UserDataAdapter(List<User> userList) {
        this.userList = userList;
    }
    @NonNull
    @Override
    public UserDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_list,parent,false);
        return new UserDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDataAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);

        holder.userName.setText(user.getName());
        holder.userId.setText(user.getId());
//        holder.userImg
//        holder.driverImg

        holder.userEmail.setText(String.valueOf(user.getEmail()));

        holder.userType.setText(user.getUserType());
        holder.userPassword.setText(user.getPassword());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userId, userEmail, userType, userPassword;
        ImageView userImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            userId = itemView.findViewById(R.id.userId);
            userEmail = itemView.findViewById(R.id.emailTextView);
            userType = itemView.findViewById(R.id.userType);
            userPassword = itemView.findViewById(R.id.passwordView);

            userImg = itemView.findViewById(R.id.userImage);

        }
    }
}
