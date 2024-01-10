package com.khaiminh.rimer.Model.UserLogin;

import android.text.TextUtils;


public class UserLogin implements IUserLogin{
    private String account, password;
    public UserLogin(String account, String password){
        this.account = account;
        this.password = password;
    }
    public UserLogin(){
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int isValid() {
        if (TextUtils.isEmpty(getAccount())){
            return 1;
        }else if (TextUtils.isEmpty(getPassword())){
            return 2;
        } else if (getPassword().length() <= 6) {
            return 3;
        } else {
            return 0;
        }
    }
}
