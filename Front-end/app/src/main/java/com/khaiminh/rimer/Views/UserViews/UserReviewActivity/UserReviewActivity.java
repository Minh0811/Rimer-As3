package com.khaiminh.rimer.Views.UserViews.UserReviewActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Controllers.ReviewControllers.ReviewControllers;

public class UserReviewActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText commentBox;
    private Button submitButton;
    private ReviewControllers reviewControllers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review);

        ratingBar = findViewById(R.id.ratingBar);
        commentBox = findViewById(R.id.commentBox);
        submitButton = findViewById(R.id.submitButton);

        reviewControllers = new ReviewControllers();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                String comment = commentBox.getText().toString();

                if (rating == 0 && comment.isEmpty()) {
                    Toast.makeText(UserReviewActivity.this, "No review to submit", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: Replace with actual user ID and driver ID
                String userId = "65a15c84ef49e925afb1187a";
                String driverId = "65a2e988d97c8b4f65db1d2b";

                reviewControllers.submitReview(userId, driverId, String.valueOf(rating), comment);

                // Optionally, clear the fields after submission
                ratingBar.setRating(0);
                commentBox.setText("");
            }
        });
    }
}
