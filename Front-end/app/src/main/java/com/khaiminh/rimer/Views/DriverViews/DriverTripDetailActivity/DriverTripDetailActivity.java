package com.khaiminh.rimer.Views.DriverViews.DriverTripDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

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
    private TextView tripDestination;
    private TextView licensePlateNumber;
    private TextView bikeDescription;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_trip_detail);

        // Initialize views
        driverPicture = findViewById(R.id.driverPicture);
        driverName = findViewById(R.id.driverName);
        tripDestination = findViewById(R.id.tripDestination);
        licensePlateNumber = findViewById(R.id.licensePlateNumber);
        bikeDescription = findViewById(R.id.bikeDescription);

        // Set up the Accept Ride button click listener
        bookingId = getIntent().getStringExtra("BOOKING_ID");
        Log.d("DriverTripDetailActivity", "BOOKING ID: " + bookingId);
        if (bookingId == null) {
            Log.e("DriverTripDetailActivity", "Booking ID is null");
            // Handle the null case appropriately
        }

        // Initialize Retrofit
        RetrofitControllers retrofitControllers = new RetrofitControllers();
        retrofitControllers.retrofitController();
        retrofitInterface = retrofitControllers.getRetrofitInterface();

        // Fetch booking details

    }

}
