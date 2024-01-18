package com.khaiminh.rimer.Views.DriverViews.TripConfirmationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.R;

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
        HashMap<String, String> map = new HashMap<>();
        map.put("status", newStatus);

        Call<Void> call = retrofitInterface.updateBookingStatus(bookingId, map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TripConfirmationActivity.this, "Booking updated successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, close the activity or navigate the user away
                } else {
                    Toast.makeText(TripConfirmationActivity.this, "Failed to update booking", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TripConfirmationActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteBooking(String bookingId) {
        Call<Void> call = retrofitInterface.deleteBooking(bookingId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TripConfirmationActivity.this, "Booking deleted successfully", Toast.LENGTH_SHORT).show();
                    // Close the activity and return to the previous one
                    finish();
                } else {
                    Toast.makeText(TripConfirmationActivity.this, "Failed to delete booking", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TripConfirmationActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
