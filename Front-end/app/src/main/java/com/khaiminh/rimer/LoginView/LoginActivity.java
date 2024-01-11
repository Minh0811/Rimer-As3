package com.khaiminh.rimer.LoginView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.khaiminh.rimer.Controllers.LoginController.ILoginController;
import com.khaiminh.rimer.Controllers.UserControllers.IUserControllers;
import com.khaiminh.rimer.Controllers.LoginController.LoginController;
import com.khaiminh.rimer.Controllers.UserControllers.userControllers;
import com.khaiminh.rimer.MainActivity;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.SignupView.SignupActivity;

public class LoginActivity extends AppCompatActivity implements InterfaceLogin{
    private EditText accountInput;
    private EditText passwordInput;
    private EditText emailInput;
    ILoginController loginController;
    IUserControllers userControllers = new userControllers();
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        loginController = new LoginController(LoginActivity.this);

        Button login = (Button) findViewById(R.id.loginSubmitButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loginController.OnLogin(accountInput.getText().toString(),passwordInput.getText().toString());
                userControllers.login(emailInput.getText().toString(), passwordInput.getText().toString(), LoginActivity.this);
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
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }

        authGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
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

    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void onForgetPassword(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_forget_password);

        Button sendingEmail = dialog.findViewById(R.id.sendButton);
        sendingEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        dialog.show();
    }

    @Override
    public void OnLoginError(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}