package com.example.babybuy.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "login")
public class LoginModel {
    @PrimaryKey (autoGenerate = true)
    private int loginID;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name="password")
    private String password;
    @ColumnInfo(name = "userid")
    private int userid;

    public LoginModel(int loginID, String username, String password, int userid) {
        this.loginID = loginID;
        this.username = username;
        this.password = password;
        this.userid = userid;
    }
    @Ignore
    public LoginModel(String username, String password, int userid) {
        this.username = username;
        this.password = password;
        this.userid = userid;
    }

    public LoginModel() {
    }

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
