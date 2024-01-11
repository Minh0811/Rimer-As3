package com.khaiminh.rimer.Controllers.UserControllers;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public interface IUserControllers {
    void authGoogle(GoogleSignInOptions gso, GoogleSignInClient gsc);
    void login(String email, String password, Context context);
    void signup(String name, String email, String password, Context context);
}
