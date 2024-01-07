package com.khaiminh.rimer.Controllers.LoginController;

import com.khaiminh.rimer.LoginView.InterfaceLogin;
import com.khaiminh.rimer.Model.UserLogin.UserLogin;

public class LoginController implements ILoginController{
    InterfaceLogin interfaceLogin;
    public LoginController (InterfaceLogin interfaceLogin){
        this.interfaceLogin = interfaceLogin;
    }

    @Override
    public void OnLogin(String account, String password) {
        UserLogin user = new UserLogin(account, password);
        int numCase = user.isValid();

        if (numCase == 1){
            interfaceLogin.OnLoginError("Enter your account");
        } else if (numCase == 2) {
            interfaceLogin.OnLoginError("Enter password");
        } else if (numCase == 3) {
            interfaceLogin.OnLoginError("Password requites more than 6 words");
        } else {
            interfaceLogin.OnLoginSuccess("Login");
        }
    }
}
