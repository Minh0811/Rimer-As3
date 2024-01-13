package com.khaiminh.rimer.Views.AuthenticationViews.DriverSignupView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.khaiminh.rimer.Controllers.UserControllers.UserControllers;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.AuthenticationViews.LoginView.LoginActivity;
import com.khaiminh.rimer.Views.AuthenticationViews.SignupView.SignupActivity;

public class DriverSignupActivity extends AppCompatActivity {
    private EditText driverAccountInput;
    private EditText driverPasswordInput;
    private EditText driverEmailInput;

    UserControllers userControllers = new UserControllers();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_signup);


        driverAccountInput = (EditText) findViewById(R.id.driverAccountInput);
        driverPasswordInput = (EditText) findViewById(R.id.driverPasswordInput);
        driverEmailInput = (EditText) findViewById(R.id.driverEmailInput);

        Button registering = (Button) findViewById(R.id.driverRegisterButton);
        registering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userControllers.driverSignup("driver", driverAccountInput.getText().toString(), driverPasswordInput.getText().toString(), driverEmailInput.getText().toString(), DriverSignupActivity.this);
            }
        });

        Button login = (Button) findViewById(R.id.driverLoginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverSignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}