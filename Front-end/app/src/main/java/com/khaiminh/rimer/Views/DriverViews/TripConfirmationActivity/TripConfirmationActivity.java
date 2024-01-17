package com.khaiminh.rimer.Views.DriverViews.TripConfirmationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.khaiminh.rimer.R;

public class TripConfirmationActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvWaitingForConfirmation;
    private Button btnAcceptRide, btnCancelRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_confirmation);

        // Initialize views
        imageView = findViewById(R.id.imageView);
        tvWaitingForConfirmation = findViewById(R.id.tvWaitingForConfirmation);
        btnAcceptRide = findViewById(R.id.btnAcceptRide);
        btnCancelRide = findViewById(R.id.btnCancelRide);

        // Set up the Accept Ride button click listener
        btnAcceptRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the accept ride action
                acceptRide();
            }
        });

        // Set up the Cancel Ride button click listener
        btnCancelRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the cancel ride action
                cancelRide();
            }
        });
    }

    private void acceptRide() {
        // TODO: Implement what happens when the ride is accepted
        // For example, notify the backend server, show a message to the user, etc.
    }

    private void cancelRide() {
        // TODO: Implement what happens when the ride is cancelled
        // For example, notify the backend server, show a message to the user, etc.

        // Optionally, close the activity
        finish();
    }
}
