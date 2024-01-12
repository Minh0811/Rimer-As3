package com.khaiminh.rimer.Controllers.UserControllers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Views.AuthenticationViews.LoginView.LoginActivity;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.Views.UserViews.UserHomeActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserControllers extends AppCompatActivity implements IUserControllers {
    private RetrofitInterface retrofitInterface;
    private RetrofitControllers retrofitControllers = new RetrofitControllers();

    public void retrofitHandle(){
        retrofitControllers.retrofitController();
        this.retrofitInterface = retrofitControllers.getRetrofitInterface();
    }

    @Override
    public void login(String email, String password, Context context){
        retrofitHandle();

        HashMap<String, String> map = new HashMap<>();

        map.put("email", email);
        map.put("password", password);

        Call<User> call = retrofitInterface.executeLogin(map);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.code() == 200) {
                    User result = response.body();
                    String name = result.getName();
                    String email = result.getEmail();
                    String password = result.getPassword();

                    User user = new User(name, email, password);

                    Toast.makeText(context, "Logged in", Toast.LENGTH_LONG).show();

                    Intent newIntent = new Intent(context, UserHomeActivity.class);
                    newIntent.putExtra("username", name);
                    context.startActivity(newIntent);

                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void signup(GoogleSignInAccount acct, String name, String email, String password, Context context){
        retrofitHandle();

        HashMap<String, String> map = new HashMap<>();

        // Set userType to "user" for Google Sign-In
        map.put("userType", "user");
        map.put("name", name);
        map.put("email", email);
        map.put("password", password); // Note: For Google Sign-In, this might not be necessary

        Call<Void> call = retrofitInterface.executeSignup(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 201) {
                    Toast.makeText(context, "Signed up successfully", Toast.LENGTH_LONG).show();
                    Intent newIntent = new Intent(context, LoginActivity.class);
                    context.startActivity(newIntent);
                } else if (response.code() == 400){
                    if (acct == null){
                        Toast.makeText(context, "Already registered", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void driverSignup(String userType, String name, String email, String password, Context context){
        retrofitHandle();

        HashMap<String, String> map = new HashMap<>();

        map.put("userType", userType); // Add userType to the map
        map.put("name", name);
        map.put("email", email);
        map.put("password", password);

        Call<Void> call = retrofitInterface.executeSignup(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 201) {
                    Toast.makeText(context, "Signed up successfully", Toast.LENGTH_LONG).show();
                    Intent newIntent = new Intent(context, LoginActivity.class);
                    context.startActivity(newIntent);
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
