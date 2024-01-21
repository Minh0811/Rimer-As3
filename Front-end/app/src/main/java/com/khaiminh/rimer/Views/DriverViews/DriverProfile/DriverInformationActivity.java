package com.example.rimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DriverInformationActivity extends AppCompatActivity {

    TextView drivername, drivercarmodel, drivercarplate;

    ImageView driverinformationimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_information);

        drivername = findViewById(R.id.drivernameinfo);
        drivercarmodel = findViewById(R.id.drivercarmodelinfo);
        drivercarplate = findViewById(R.id.drivercarplateinfo);
        driverinformationimg = findViewById(R.id.driverinformationimage);
    }
}