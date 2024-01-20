package com.khaiminh.rimer.Views.History;

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
import com.khaiminh.rimer.Views.DriverViews.DriverHomeActivity.ListTripActivity;
import com.khaiminh.rimer.Views.DriverViews.DriverProfile.DriverProfile;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.listHistoryBooking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Booking> historyList = getData();

        historyAdapter = new HistoryAdapter(historyList);
        recyclerView.setAdapter(historyAdapter);


        ImageButton movingBack = (ImageButton) findViewById(R.id.backBtn);
        movingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(HistoryActivity.this, .class);
//                startActivity(intent);
            }
        });

    }

    private List<Booking> getData() {

        List<Booking> bookingHistory = new ArrayList<>();
        bookingHistory.add(new Booking("2","user2","driver1","booking",3,12.4,"District 7,District 7,District 7,District 7,District 7,District 7,District 7,District 7","District 4"));
        bookingHistory.add(new Booking("2","user3","driver2","booking",3,2.4,"District 1","District 3"));
        bookingHistory.add(new Booking("2","user123","driver3","booking",3,10,"thedfs","drg"));
        bookingHistory.add(new Booking("2","user23","driver4","booking",3,187,"thedfs","drg"));
        bookingHistory.add(new Booking("2","user23","driver4","booking",3,187,"thedfs","drg"));

//        List<User> user = new ArrayList<>();
//        // Example: tripList.add(new Trip("DriverName", "CarPlate", ...));
//        user.add(new User("Name","Email1","Pass1", "user", "123", "sdasd", bookingHistory));
//        user.add(new User("Name1","Email1","Pass1", "user", "123", "sdasd", bookingHistory));
//        user.add(new User("Name2","Email1","Pass1", "user", "123", "sdasd", bookingHistory));
//        user.add(new User("Name3","Email1","Pass1", "user", "123", "sdasd", bookingHistory));

        return bookingHistory;
    }
}