package com.khaiminh.rimer.Controllers.LoginController;

import android.content.Context;

public interface IUserControllers {
    void authGoogle();
    void login(String email, String password, Context context);
}
