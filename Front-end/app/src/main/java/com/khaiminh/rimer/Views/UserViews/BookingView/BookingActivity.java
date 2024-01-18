package com.khaiminh.rimer.Views.UserViews.BookingView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.khaiminh.rimer.Controllers.UserControllers.UserControllers;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
    ArrayList<User> drivers = new ArrayList<>();
    double distance, price;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Intent intent = getIntent();
        distance = Double.parseDouble(intent.getStringExtra("distance"));
        price = Double.parseDouble(intent.getStringExtra("price"));
        drivers = (ArrayList<User>) intent.getSerializableExtra("drivers");
        userId = (String) intent.getStringExtra("user_id");

        RecyclerView recyclerView = findViewById(R.id.listAvailableDrivers);
        ListDriverAdapter driverAdapter = new ListDriverAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(BookingActivity.this, RecyclerView.VERTICAL, false));

        driverAdapter.setData(BookingActivity.this, userId, drivers, R.layout.driver_available, "Appending...", distance, price, "startPoint", "endPoint");
        recyclerView.setAdapter(driverAdapter);
    }
}