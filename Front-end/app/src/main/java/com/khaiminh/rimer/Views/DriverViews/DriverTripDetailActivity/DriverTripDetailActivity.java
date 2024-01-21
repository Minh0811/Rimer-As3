package com.khaiminh.rimer.Views.DriverViews.DriverTripDetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khaiminh.rimer.Controllers.BookingControllers.BookingControllers;
import com.khaiminh.rimer.Controllers.BookingControllers.UpdateBookingStatusCallback;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.Model.Booking;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.DriverViews.DriverHomeActivity.ListTripActivity;
import com.khaiminh.rimer.Views.DriverViews.TripConfirmationActivity.TripConfirmationActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverTripDetailActivity extends AppCompatActivity {
    private String bookingId;
    private TextView finishedBtn;
    private TextView driverName;
    private TextView tripPickupPoint;
    private TextView tripDestination;
    private TextView customerName;
    private TextView price;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_trip_detail);

        // Initialize views
        finishedBtn = findViewById(R.id.finishedRideButton);
        driverName = findViewById(R.id.driverName);
        tripPickupPoint = findViewById(R.id.tripPickupPoint);
        tripDestination = findViewById(R.id.tripDestination);
        customerName = findViewById(R.id.customerName);
        price = findViewById(R.id.price);

        // Retrieve the booking ID from the intent
        bookingId = getIntent().getStringExtra("bookingId");
        Log.e("DriverTripDetailActivity", "Booking ID: " + bookingId);
        if (bookingId == null) {
            Log.e("DriverTripDetailActivity", "Booking ID is null");
            // Handle the null case appropriately
            return;
        }

        // Initialize Retrofit
        RetrofitControllers retrofitControllers = new RetrofitControllers();
        retrofitControllers.retrofitController();
        retrofitInterface = retrofitControllers.getRetrofitInterface();

        // Fetch booking details
        fetchBookingDetails(bookingId);

        BookingControllers bookingControllers = new BookingControllers();
        finishedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingControllers.updateBookingStatus(bookingId, "completed", new UpdateBookingStatusCallback(){
                    @Override
                    public void onSuccess() {
                        Toast.makeText(DriverTripDetailActivity.this, "Ride completed", Toast.LENGTH_SHORT).show();
                        // Navigate to ListTripActivity
                        Intent intent = new Intent(DriverTripDetailActivity.this, ListTripActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(DriverTripDetailActivity.this, "Failed to complete ride: " + errorMessage, Toast.LENGTH_SHORT).show();
                        // Handle failure to update booking status
                    }
                });
            }
        });
    }

    private void fetchBookingDetails(String bookingId) {
        Call<Booking> call = retrofitInterface.getBookingDetails(bookingId);
        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Booking booking = response.body();
                    Log.d("DriverTripDetailActivity", "Booking details fetched successfully.");

                    // Log and check driverId and userId
                    String driverId = booking.getDriverId();
                    String userId = booking.getUserId();
                    Log.d("DriverTripDetailActivity", "Driver ID: " + driverId + ", User ID: " + userId);

                    // Fetch and display driver's name if driverId is not null
                    if (driverId != null) {
                        fetchUserName(driverId, driverName);
                    } else {
                        Log.e("DriverTripDetailActivity", "Driver ID is null");
                        driverName.setText("Driver name not available");
                    }

                    // Fetch and display customer's name if userId is not null
                    if (userId != null) {
                        fetchUserName(userId, customerName);
                    } else {
                        Log.e("DriverTripDetailActivity", "User ID is null");
                        customerName.setText("Customer name not available");
                    }

                    // Update UI with other booking details
                    tripPickupPoint.setText(booking.getStartPoint());
                    tripDestination.setText(booking.getEndPoint());
                    price.setText(String.format("$%.2f", booking.getPrice()));
                } else {
                    Log.e("DriverTripDetailActivity", "Failed to fetch booking details. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                Log.e("DriverTripDetailActivity", "Error fetching booking details: " + t.getMessage());
            }
        });
    }

    private void fetchUserName(String userId, TextView textViewToUpdate) {
        Call<User> call = retrofitInterface.getUserDetails(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    Log.d("DriverTripDetailActivity", "User details fetched successfully.");
                    textViewToUpdate.setText(user.getName()); // Assuming 'getName()' method exists in your User model
                } else {
                    Log.e("DriverTripDetailActivity", "Failed to fetch user details. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("DriverTripDetailActivity", "Error fetching user details: " + t.getMessage());
            }
        });
    }

}
