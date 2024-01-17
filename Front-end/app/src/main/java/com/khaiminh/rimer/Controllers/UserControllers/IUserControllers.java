package com.khaiminh.rimer.Controllers.UserControllers;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.khaiminh.rimer.Model.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface IUserControllers {
    void login(String email, String password, Context context);
    void signup(GoogleSignInAccount acct, String name, String email, String password, Context context);
    void driverSignup(String userType, String name, String email, String password, Context context);
    ArrayList<User> getListDrivers();
}