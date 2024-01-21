package com.khaiminh.rimer.Views.UserViews.UserReviewActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Controllers.ReviewControllers.ReviewControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Model.Booking;
import com.khaiminh.rimer.Views.UserViews.UserHomeActivity.UserHomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;


public class UserReviewActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText commentBox;
    private Button submitButton;
    private ReviewControllers reviewControllers;
    private RetrofitControllers retrofitControllers;
    private String bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review);

        ratingBar = findViewById(R.id.ratingBar);
        commentBox = findViewById(R.id.commentBox);
        submitButton = findViewById(R.id.submitButton);

        reviewControllers = new ReviewControllers();
        retrofitControllers = new RetrofitControllers();

        // Get the booking ID from the intent
        bookingId = getIntent().getStringExtra("bookingId");
        Log.d("Booking ID", "Booking ID: " + bookingId);

        // Fetch booking details and handle review submission
        fetchBookingDetailsAndHandleReview();
    }

    private void fetchBookingDetailsAndHandleReview() {
        retrofitControllers.retrofitController();
        Call<Booking> call = retrofitControllers.getRetrofitInterface().getBookingDetails(bookingId);
        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Booking booking = response.body();
                    String userId = booking.getUserId();
                    String driverId = booking.getDriverId();

                    handleReviewSubmission(userId, driverId);
                } else {
                    Log.e("Fetch Booking", "Failed to fetch booking details. Response code: " + response.code());
                    Toast.makeText(UserReviewActivity.this, "Failed to fetch booking details.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                Log.e("Fetch Booking", "Error fetching booking details: " + t.getMessage());
                Toast.makeText(UserReviewActivity.this, "Error fetching booking details.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleReviewSubmission(String userId, String driverId) {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                String comment = commentBox.getText().toString();

                if (rating == 0 && comment.isEmpty()) {
                    Toast.makeText(UserReviewActivity.this, "No review to submit", Toast.LENGTH_SHORT).show();
                    return;
                }

                reviewControllers.submitReview(userId, driverId, String.valueOf(rating), comment);
                Intent intent = new Intent(UserReviewActivity.this, UserHomeActivity.class);
                startActivity(intent);
                // Optionally, clear the fields after submission
                ratingBar.setRating(0);
                commentBox.setText("");

            }
        });
    }
}
