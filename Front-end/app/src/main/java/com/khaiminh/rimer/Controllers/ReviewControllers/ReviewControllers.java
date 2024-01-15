package com.khaiminh.rimer.Controllers.ReviewControllers;

import android.util.Log;

import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewControllers implements IReviewControllers {

    private RetrofitInterface retrofitInterface;

    public ReviewControllers() {
        RetrofitControllers retrofitControllers = new RetrofitControllers();
        retrofitControllers.retrofitController();
        this.retrofitInterface = retrofitControllers.getRetrofitInterface();
    }

    public void submitReview(String userId, String driverId, String rating, String comment) {
        HashMap<String, Object> reviewDetails = new HashMap<>();
        reviewDetails.put("userId", userId);
        reviewDetails.put("driverId", driverId);
        reviewDetails.put("rating", rating);
        reviewDetails.put("reviewText", comment);

        // Check if both rating and comment are empty
        if (rating.isEmpty() && comment.isEmpty()) {
            // Do not create a review if both rating and comment are empty
            return;
        }

        Call<Void> call = retrofitInterface.submitReview(reviewDetails);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Log success or show a success message
                    Log.d("Review Submission", "Review submitted successfully.");
                } else {
                    // Log failure or show an error message
                    Log.e("ReviewSubmission", "Failed to submit review. Response code: " + response.code());

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle network failure or other errors
                Log.e("ReviewSubmission", "Error submitting review: " + t.getMessage());
            }
        });
    }
}
