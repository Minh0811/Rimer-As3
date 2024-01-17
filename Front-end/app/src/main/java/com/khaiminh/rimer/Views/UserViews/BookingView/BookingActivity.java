package com.khaiminh.rimer.Views.UserViews.BookingView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;

public class BookingActivity extends AppCompatActivity {
    User user;
    double distance, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        distance = Double.parseDouble(intent.getStringExtra("distance"));
        price = Double.parseDouble(intent.getStringExtra("price"));

        assert user != null;
        Toast.makeText(this, ""+user.getEmail()+" "+distance+" "+price, Toast.LENGTH_SHORT).show();
    }
}