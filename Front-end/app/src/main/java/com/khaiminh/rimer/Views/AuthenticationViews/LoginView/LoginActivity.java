package com.khaiminh.rimer.Views.AuthenticationViews.LoginView;

import androidx.annotation.NonNull;
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
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.Controllers.UserControllers.IUserControllers;
import com.khaiminh.rimer.Controllers.UserControllers.UserControllers;
import com.khaiminh.rimer.Model.Booking;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.AuthenticationViews.SignupView.SignupActivity;
import com.khaiminh.rimer.Views.UserViews.UserHomeActivity.UserHomeActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements InterfaceLogin{
    private EditText passwordInput;
    private EditText emailInput;
    IUserControllers userControllers = new UserControllers();
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private RetrofitInterface retrofitInterface;
    private RetrofitControllers retrofitControllers = new RetrofitControllers();

    public void retrofitHandle(){
        retrofitControllers.retrofitController();
        this.retrofitInterface = retrofitControllers.getRetrofitInterface();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        Button login = (Button) findViewById(R.id.loginSubmitButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListDrivers();
            }
        });

        Button signup = (Button) findViewById(R.id.signUpButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

//        login by google
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
        Intent intent = new Intent(LoginActivity.this, UserHomeActivity.class);
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
    public void getListDrivers() {
        retrofitHandle();
        ArrayList<User> allDrivers = new ArrayList<>();
        Call<List<User>> call = retrofitInterface.executeListDrivers();
        call.enqueue(new Callback<List<User>>() {
            ArrayList<User> drivers = new ArrayList<>();
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                List<User> driversList = response.body();
                for (int i = 0; i < driversList.size(); i++) {
                    String name = driversList.get(i).getName();
                    String email = driversList.get(i).getEmail();
                    String password = driversList.get(i).getPassword();
                    String userType = driversList.get(i).getUserType();
                    String userId = driversList.get(i).getId();
                    Booking onGoingBooking = driversList.get(i).getOnGoingBooking();
                    List<Booking> bookingHistory = driversList.get(i).getBookingHistory();

                    User driver = new User(name, email, password, userType, userId, onGoingBooking, bookingHistory);
                    allDrivers.add(driver);
                }
                userControllers.login(emailInput.getText().toString(), passwordInput.getText().toString(), LoginActivity.this, allDrivers);
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
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