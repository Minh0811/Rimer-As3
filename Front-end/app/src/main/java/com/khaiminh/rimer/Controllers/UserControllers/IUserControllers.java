package com.khaiminh.rimer.Controllers.UserControllers;

import android.content.Context;

public interface IUserControllers {
    void authGoogle();
    void login(String email, String password, Context context);
    void signup(String name, String email, String password, Context context);
}
