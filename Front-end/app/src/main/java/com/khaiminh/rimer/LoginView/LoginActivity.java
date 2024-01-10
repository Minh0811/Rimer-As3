package com.khaiminh.rimer.LoginView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khaiminh.rimer.Controllers.LoginController.ILoginController;
import com.khaiminh.rimer.Controllers.LoginController.IUserControllers;
import com.khaiminh.rimer.Controllers.LoginController.LoginController;
import com.khaiminh.rimer.Controllers.LoginController.userControllers;
import com.khaiminh.rimer.MainActivity;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.SignupView.SignupActivity;

public class LoginActivity extends AppCompatActivity implements InterfaceLogin{

    private EditText accountInput;
    private EditText passwordInput;
    private EditText emailInput;
    ILoginController loginController;
    IUserControllers userControllers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountInput = (EditText) findViewById(R.id.accountInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        loginController = new LoginController(LoginActivity.this);
        userControllers = new userControllers();

        Button login = (Button) findViewById(R.id.loginSubmit);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loginController.OnLogin(accountInput.getText().toString(),passwordInput.getText().toString());
                userControllers.login(accountInput.getText().toString(), passwordInput.getText().toString(), LoginActivity.this);
            }
        });

        Button signup = (Button) findViewById(R.id.loginButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        Button authGoogle = (Button) findViewById(R.id.authGoogle);
        authGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userControllers.authGoogle();
            }
        });

        Button forgetPassword = (Button) findViewById(R.id.helpButton);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onForgetPassword();
            }
        });
    }

    private void onForgetPassword(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_forget_password);

        Button sendingEmail = dialog.findViewById(R.id.emailInput);
        sendingEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        dialog.show();
    }

    @Override
    public void OnLoginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnLoginError(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }
}