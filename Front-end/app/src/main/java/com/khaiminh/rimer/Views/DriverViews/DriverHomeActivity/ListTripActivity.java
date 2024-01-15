package com.khaiminh.rimer.Views.DriverViews.DriverHomeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;

import java.util.ArrayList;
import java.util.List;

public class ListTripActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListTripAdapter listTripAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trip);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<User> tripList = getTripData();

        listTripAdapter = new ListTripAdapter(tripList);
        recyclerView.setAdapter(listTripAdapter);
    }

    private List<User> getTripData() {
        List<User> tripList = new ArrayList<>();
        // Example: tripList.add(new Trip("DriverName", "CarPlate", ...));
        tripList.add(new User("Name","Email1","Pass1"));
        tripList.add(new User("Name1","Email","Pass2"));
        tripList.add(new User("Name2","Email4","Pass3"));
        tripList.add(new User("Name2","Email2","Pass3"));
        return tripList;
    }
}