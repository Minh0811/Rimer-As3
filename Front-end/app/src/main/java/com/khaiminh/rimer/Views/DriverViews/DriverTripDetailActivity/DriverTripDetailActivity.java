package com.khaiminh.rimer.Views.DriverViews.DriverTripDetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.Model.Booking;
import com.khaiminh.rimer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverTripDetailActivity extends AppCompatActivity {
    private String bookingId;
    private ImageView driverPicture;
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
        driverPicture = findViewById(R.id.driverPicture);
        driverName = findViewById(R.id.driverName);
        tripPickupPoint = findViewById(R.id.tripPickupPoint);
        tripDestination = findViewById(R.id.tripDestination);
        customerName = findViewById(R.id.customerName);
        price = findViewById(R.id.price);

        // Retrieve the booking ID from the intent
        bookingId = getIntent().getStringExtra("BOOKING_ID");
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
    }

    private void fetchBookingDetails(String bookingId) {
        Call<Booking> call = retrofitInterface.getBookingDetails(bookingId);
        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Booking booking = response.body();
                    Log.d("DriverTripDetailActivity", "Booking details fetched successfully.");

                    // Update UI with booking details
                    // Note: Adjust these lines according to your actual data model and UI design
                    driverName.setText(booking.getDriverId()); // Assuming you want to display the driver ID
                    tripPickupPoint.setText(booking.getStartPoint()); // Display the trip pickup point
                    tripDestination.setText(booking.getEndPoint()); // Display the trip destination
                    customerName.setText(booking.getUserId()); // Assuming you want to display the user/customer ID
                    price.setText(String.format("$%.2f", booking.getPrice())); // Display the price
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
}
