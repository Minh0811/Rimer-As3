package com.khaiminh.rimer.Views.UserViews.TripWaitingConfirmationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.Model.Booking;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.DriverViews.DriverHomeActivity.ListTripAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripWaitingConfirmationActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvWaitingForConfirmation;
    private Button btnCancelRide;
    String currUserId;
    Booking appendingBooking;
    private RetrofitInterface retrofitInterface;
    private RetrofitControllers retrofitControllers = new RetrofitControllers();

    public void retrofitHandle(){
        retrofitControllers.retrofitController();
        this.retrofitInterface = retrofitControllers.getRetrofitInterface();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_waiting_confirmation);

        // Initialize views
        imageView = findViewById(R.id.imageView);
        tvWaitingForConfirmation = findViewById(R.id.tvWaitingForConfirmation);
        btnCancelRide = findViewById(R.id.btnCancelRide);

        Intent intent = getIntent();
        currUserId = intent.getStringExtra("currUserId");

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
        retrofitHandle();

        Call<List<Booking>> call = retrofitInterface.getUserAppendingBookings(currUserId);
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Booking> bookingList = response.body();
                    String id = bookingList.get(0).getId();
                    Call<Void> deleteCall = retrofitInterface.deleteBooking(id);
                    deleteCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> deleteCall, Response<Void> deleteResponse) {
                            if (deleteResponse.isSuccessful() && deleteResponse.body() != null) {

                            }
                        }
                        @Override
                        public void onFailure(Call<Void> deleteCall, Throwable t) {
                            // Handle failure
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                // Handle failure
            }
        });
        // Optionally, close the activity
        finish();
    }
}
