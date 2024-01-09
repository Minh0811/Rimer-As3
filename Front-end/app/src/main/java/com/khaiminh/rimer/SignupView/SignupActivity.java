package com.khaiminh.rimer.SignupView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khaiminh.rimer.LoginView.LoginActivity;
import com.khaiminh.rimer.R;

public class SignupActivity extends AppCompatActivity {

    private EditText accountInput;
    private EditText passwordInput;
    private EditText emailInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        accountInput = (EditText) findViewById(R.id.accountInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        emailInput = (EditText) findViewById(R.id.emailInput);


        Button registering = (Button) findViewById(R.id.registerButton);
        registering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignupActivity.this, "Register", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}