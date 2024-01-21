package com.khaiminh.rimer.Views.DriverViews.TripConfirmationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khaiminh.rimer.Controllers.BookingControllers.BookingControllers;
import com.khaiminh.rimer.Controllers.BookingControllers.UpdateBookingStatusCallback;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.DriverViews.DriverTripDetailActivity.DriverTripDetailActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Log.d("TripConfirmationActivity", "BOOKING ID: " + bookingId);
        if (bookingId == null) {
            Log.e("TripConfirmationActivity", "Booking ID is null");
            // Handle the null case appropriately
        }

        // Initialize Retrofit
        RetrofitControllers retrofitControllers = new RetrofitControllers();
        retrofitControllers.retrofitController();
        retrofitInterface = retrofitControllers.getRetrofitInterface();

        Button buttonConfirm = findViewById(R.id.btnAcceptRide);
        Button buttonCancel = findViewById(R.id.btnCancelRide);

        BookingControllers bookingControllers = new BookingControllers();

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingControllers.updateBookingStatus(bookingId, "accepted", new UpdateBookingStatusCallback(){
                    @Override
                    public void onSuccess() {
                        Toast.makeText(TripConfirmationActivity.this, "Ride accepted successfully", Toast.LENGTH_SHORT).show();
                        // Navigate to DriverTripDetailActivity
                        Intent intent = new Intent(TripConfirmationActivity.this, DriverTripDetailActivity.class);
                        intent.putExtra("bookingId", bookingId); // Pass the booking ID to the next activity
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(TripConfirmationActivity.this, "Failed to accept ride: " + errorMessage, Toast.LENGTH_SHORT).show();
                        // Handle failure to update booking status
                    }
                });
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingControllers.updateBookingStatus(bookingId, "declined", new UpdateBookingStatusCallback(){
                    @Override
                    public void onSuccess() {
                        Toast.makeText(TripConfirmationActivity.this, "Ride declined successfully", Toast.LENGTH_SHORT).show();
                        // Handle successful booking status update (e.g., navigate to another activity)
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(TripConfirmationActivity.this, "Failed to declined ride: " + errorMessage, Toast.LENGTH_SHORT).show();
                        // Handle failure to update booking status
                    }
                });
                finish();
            }
        });
    }
}
