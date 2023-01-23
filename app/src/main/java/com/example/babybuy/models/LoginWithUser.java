package com.example.babybuy.models;

import androidx.room.Embedded;
import androidx.room.Relation;

public class LoginWithUser {
    @Embedded
    UserModel userModel;

    @Relation(parentColumn = "id", entityColumn = "userid", entity = LoginModel.class)
    public LoginModel loginModel;

    public LoginWithUser(UserModel userModel, LoginModel loginModel) {
        this.userModel = userModel;
        this.loginModel = loginModel;
    }

    public LoginWithUser() {
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }
}
