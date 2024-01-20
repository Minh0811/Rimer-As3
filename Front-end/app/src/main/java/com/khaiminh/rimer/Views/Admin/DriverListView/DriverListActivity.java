package com.khaiminh.rimer.Views.Admin.DriverListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.khaiminh.rimer.Model.Booking;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.Admin.UserListView.UserDataAdapter;
import com.khaiminh.rimer.Views.Admin.UserListView.UserListActivity;

import java.util.ArrayList;
import java.util.List;

public class DriverListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserDataAdapter userDataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);

        ImageButton movingBack = (ImageButton) findViewById(R.id.movingBackBtn);
        movingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(UserListActivity.this, .class);
//                startActivity(intent);
            }
        });

        Button movingUsers = (Button) findViewById(R.id.movingUsersBtn);
        movingUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverListActivity.this, UserListActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.driverList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<User> userList = getData();

        userDataAdapter = new UserDataAdapter(userList);
        recyclerView.setAdapter(userDataAdapter);
    }

    private List<User> getData() {
        List<Booking> bookingHistory = new ArrayList<>();
        bookingHistory.add(new Booking("2","user3","driver2","booking",3,2.4,"District 1","District 3"));

        List<User> user = new ArrayList<>();
        user.add(new User("Nawme","Email1","Pass1", "Driver", "123", "sdasd", bookingHistory));
        user.add(new User("Name1","Email1","Pass1", "Drier2", "123", "sdasd", bookingHistory));
        user.add(new User("Name2","Email1","Pass1", "Driver5", "123", "sdasd", bookingHistory));
        user.add(new User("Name3","Email1","Pass1", "Driver7", "123", "sdasd", bookingHistory));

        return user;
    }
}