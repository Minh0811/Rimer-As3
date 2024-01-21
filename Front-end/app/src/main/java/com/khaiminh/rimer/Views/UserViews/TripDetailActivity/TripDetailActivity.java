package com.khaiminh.rimer.Views.UserViews.TripDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.Model.BookingStatus;
import com.khaiminh.rimer.Model.DriverResponse;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.UserViews.TripWaitingConfirmationActivity.TripWaitingConfirmationActivity;
import com.khaiminh.rimer.Views.UserViews.UserReviewActivity.UserReviewActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

public class TripDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private final Handler handler = new Handler();
    private final int delay = 5000; // 5000ms = 5s
    private GoogleMap mMap;
    private TextView tripPickupPoint, tripDestination, driverName;
    private String bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);

        // Initialize the map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Initialize views
        tripPickupPoint = findViewById(R.id.tripPickupPoint);
        tripDestination = findViewById(R.id.tripDestination);
        driverName = findViewById(R.id.driverName);


        // Get the booking ID from the intent
        bookingId = getIntent().getStringExtra("bookingId");
        Log.d("Booking ID", "Booking ID: " + bookingId);

        // Start the polling
        handler.postDelayed(new Runnable() {
            public void run() {
                // Check the booking status
                checkBookingStatus(bookingId);

                // Schedule the next execution
                handler.postDelayed(this, delay);
            }
        }, delay);

    }

    private void checkBookingStatus(String bookingId) {
        Log.d("Booking ID", "Checking booking status for ID: " + bookingId);
        RetrofitControllers retrofitControllers = new RetrofitControllers();
        retrofitControllers.retrofitController(); // Ensure this is called to initialize retrofitInterface
        RetrofitInterface retrofitInterface = retrofitControllers.getRetrofitInterface();
        if (retrofitInterface != null) {
            Call<BookingStatus> call = retrofitInterface.checkBookingStatus(bookingId);
            call.enqueue(new Callback<BookingStatus>() {
                @Override
                public void onResponse(Call<BookingStatus> call, Response<BookingStatus> response) {
                    Log.d("booking ID:", "onResponse: received for booking ID: " + bookingId);
                    if (response.isSuccessful() && response.body() != null) {
                        BookingStatus bookingStatus = response.body();
                        Log.d("bookingStatus:", "bookingStatus: " + bookingStatus.toString());
                        Log.d("bookingStatus:", "bookingStatus: " + bookingStatus.isCompleted());
                        Log.d("Response body", "Response body: " + response.body().toString());
                        if (bookingStatus.isCompleted()) {
                            Log.d("Booking completed", "Booking completed, navigating to UserReviewActivity");
                            // If the driver accepts the ride, navigate to TripDetailActivity
                            Intent intent = new Intent(TripDetailActivity.this, UserReviewActivity.class);
                            intent.putExtra("bookingId", bookingId); // Pass the booking ID to the next activity
                            startActivity(intent);

                            // Stop the handler from making further requests
                            handler.removeCallbacksAndMessages(null);
                        }
                    } else {
                        Log.e("Failed to get booking status", "Failed to get booking status. Response not successful.");
                        // Handle the scenario where the response is not successful
                        Toast.makeText(TripDetailActivity.this, "Failed to get the driver's response.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BookingStatus> call, Throwable t) {
                    Log.e("Network error while checking booking status", "Network error while checking booking status", t);
                    // Handle the scenario where the network request itself fails
                    Toast.makeText(TripDetailActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Log.e("Retrofit interface not initialized", "Retrofit interface not initialized");
            Toast.makeText(this, "Error initializing network interface", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // TODO: Customize the map
        // For example, setting the map type, adding markers, moving the camera, etc.
    }
    @Override
    protected void onDestroy() {
        // Stop the handler from making further requests
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
