package com.example.assignment3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        // Initialize your views and set up click listeners if necessary
        // For example:
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        Button buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(view -> {
            // Logic to save personal information
        });
    }

    // Additional methods for personal information handling can be added here
}
