package com.khaiminh.rimer.Views.DriverViews.DriverProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.khaiminh.rimer.R;

public class DriverInformationActivity extends AppCompatActivity {

    TextView drivername, driveremail, drivercarmodel, drivercarplate;

    ImageView driverinformationimg;

    Button accountsettingbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_information);

        drivername = findViewById(R.id.drivernameinfo);
        driveremail = findViewById(R.id.driveremailinfo);
        drivercarmodel = findViewById(R.id.drivercarmodelinfo);
        drivercarplate = findViewById(R.id.drivercarplateinfo);
        driverinformationimg = findViewById(R.id.driverinformationimage);
        accountsettingbtn = findViewById(R.id.accountsetbtn);

        accountsettingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}