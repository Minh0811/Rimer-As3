package com.khaiminh.rimer.Controllers.UserControllers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.LoginView.LoginActivity;
import com.khaiminh.rimer.MainActivity;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class userControllers extends AppCompatActivity implements IUserControllers {
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

                    Intent newIntent = new Intent(context, MainActivity.class);
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
    public void signup(String name, String email, String password, Context context){
        retrofitHandle();

        HashMap<String, String> map = new HashMap<>();

        map.put("name", name);
        map.put("email", email);
        map.put("password", password);

        Call<Void> call = retrofitInterface.executeSignup(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 201) {
                    Toast.makeText(context,
                            "Signed up successfully", Toast.LENGTH_LONG).show();
                    Intent newIntent = new Intent(context, LoginActivity.class);
                    context.startActivity(newIntent);
                } else {
                    Toast.makeText(context,
                            response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void authGoogle(){

    }
}
