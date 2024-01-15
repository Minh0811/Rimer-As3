package com.khaiminh.rimer.Views.UserViews.UserRevewActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;

public class UserReviewActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText commentBox;
    private Button submitButton;
    private RetrofitControllers retrofitControllers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review);

        ratingBar = findViewById(R.id.ratingBar);
        commentBox = findViewById(R.id.commentBox);
        submitButton = findViewById(R.id.submitButton);

        retrofitControllers = new RetrofitControllers();
        retrofitControllers.retrofitController();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                String comment = commentBox.getText().toString();

                if (rating == 0 && comment.isEmpty()) {
                    Toast.makeText(UserReviewActivity.this, "No review to submit", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: Implement the API call to submit the review
                // Example: retrofitControllers.getRetrofitInterface().submitReview(driverId, rating, comment);
            }
        });
    }
}
