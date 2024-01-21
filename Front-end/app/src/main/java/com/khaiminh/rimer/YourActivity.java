package com.example.assignment3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class YourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_information);

        TextView txtAccountSettings = findViewById(R.id.txtAccountSettings);
        txtAccountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Account settings" click
                handleAccountSettingsClick();
            }
        });

        TextView txtPersonalInformation = findViewById(R.id.txtPersonalInformation);
        txtPersonalInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Personal information" click
                handlePersonalInformationClick();
            }
        });
    }

    private void handleAccountSettingsClick() {
        // Create an Intent to start the AccountSettingsActivity
        Intent intent = new Intent(this, AccountSettingsActivity.class);
        // Start the new activity
        startActivity(intent);
    }

    private void handlePersonalInformationClick() {
        // Create an Intent to start the PersonalInformationActivity
        Intent intent = new Intent(this, PersonalInformationActivity.class);
        // Start the new activity
        startActivity(intent);
    }
}

