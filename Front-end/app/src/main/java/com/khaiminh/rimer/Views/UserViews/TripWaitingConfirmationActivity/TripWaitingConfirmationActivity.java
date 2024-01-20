package com.khaiminh.rimer.Views.UserViews.TripWaitingConfirmationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.Model.DriverResponse;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.UserViews.TripDetailActivity.TripDetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.widget.Toast;

public class TripWaitingConfirmationActivity extends AppCompatActivity {
    private final Handler handler = new Handler();
    private final int delay = 5000; // 5000ms = 5s
    private String bookingId;
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

        // Get the driver ID from the intent
        String driverId = getIntent().getStringExtra("driverId");

        // Simulate checking the driver's response (you'll implement the actual logic based on your backend)
        checkDriverResponse(driverId);
        // Set up the Cancel Ride button click listener
        btnCancelRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the cancel ride action
                cancelRide();
            }
        });
        // Get the booking ID from the intent
        bookingId = getIntent().getStringExtra("bookingId");

        // Start the polling
        handler.postDelayed(new Runnable() {
            public void run() {
                // Check the driver's response
                checkDriverResponse(bookingId);

                // Schedule the next execution
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    private void cancelRide() {
        // TODO: Implement what happens when the ride is cancelled
        // For example, notify the backend server, show a message to the user, etc.

        // Optionally, close the activity
        finish();
    }
    private void checkDriverResponse(String bookingId) {
        RetrofitControllers retrofitControllers = new RetrofitControllers();
        retrofitControllers.retrofitController(); // Ensure this is called to initialize retrofitInterface
        RetrofitInterface retrofitInterface = retrofitControllers.getRetrofitInterface();

        if (retrofitInterface != null) {
            Call<DriverResponse> call = retrofitInterface.checkDriverResponse(bookingId);
            call.enqueue(new Callback<DriverResponse>() {
                @Override
                public void onResponse(Call<DriverResponse> call, Response<DriverResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        DriverResponse driverResponse = response.body();
                        if (driverResponse.isAccepted()) {
                            // If the driver accepts the ride, navigate to TripDetailActivity
                            Intent intent = new Intent(TripWaitingConfirmationActivity.this, TripDetailActivity.class);
                            intent.putExtra("bookingId", bookingId); // Pass the booking ID to the next activity
                            startActivity(intent);

                            // Stop the handler from making further requests
                            handler.removeCallbacksAndMessages(null);
                        }
                    } else {
                        // Handle the scenario where the response is not successful
                        Toast.makeText(TripWaitingConfirmationActivity.this, "Failed to get the driver's response.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DriverResponse> call, Throwable t) {
                    // Handle the scenario where the network request itself fails
                    Toast.makeText(TripWaitingConfirmationActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Error initializing network interface", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        // Stop the handler from making further requests
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
