package com.khaiminh.rimer.Views.DriverViews.TripConfirmationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.R;

public class TripConfirmationActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvWaitingForConfirmation;
    private Button btnAcceptRide, btnCancelRide;
    private String bookingId;
    private RetrofitInterface retrofitInterface;

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
        bookingId = getIntent().getStringExtra("BOOKING_ID");

        // Initialize Retrofit
        RetrofitControllers retrofitControllers = new RetrofitControllers();
        retrofitControllers.retrofitController();
        retrofitInterface = retrofitControllers.getRetrofitInterface();

        Button buttonConfirm = findViewById(R.id.btnAcceptRide);
        Button buttonCancel = findViewById(R.id.btnCancelRide);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBookingStatus(bookingId, "ongoing");
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBooking(bookingId);
            }
        });
    }

    private void updateBookingStatus(String bookingId, String newStatus) {
        // Implement the logic to update the booking status
        // Make a network request to your backend to update the status of the booking
    }

    private void deleteBooking(String bookingId) {
        // Implement the logic to delete the booking
        // Make a network request to your backend to delete the booking
    }
}
