package com.khaiminh.rimer.Views.UserViews.TripWaitingConfirmationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.UserViews.UserHomeActivity.UserHomeActivity;

public class TripWaitingConfirmationActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvWaitingForConfirmation;
    private Button btnCancelRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_waiting_confirmation);

        // Initialize views
        imageView = findViewById(R.id.imageView);
        tvWaitingForConfirmation = findViewById(R.id.tvWaitingForConfirmation);
        btnCancelRide = findViewById(R.id.btnCancelRide);

        // Set up the Cancel Ride button click listener
        btnCancelRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the cancel ride action
                cancelRide();
            }
        });
    }

    private void cancelRide() {
        // TODO: Implement what happens when the ride is cancelled
        // For example, notify the backend server, show a message to the user, etc.
        Intent intent = new Intent(TripWaitingConfirmationActivity.this, UserHomeActivity.class);
        startActivityForResult(intent, 900);
        // Optionally, close the activity
        finish();
    }
}
