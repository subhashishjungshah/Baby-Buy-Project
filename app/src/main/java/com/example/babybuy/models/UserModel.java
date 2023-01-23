package com.example.babybuy.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "User_tbl")
public class UserModel {
    @PrimaryKey (autoGenerate = false)
    private int id;
    @ColumnInfo(name = "Fullname")
    private String Fullname;
    @ColumnInfo(name = "Email")
    private String email;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "country")
    private String country;

    public UserModel(int id, String fullname, String gender, String country) {
        this.id = id;
        Fullname = fullname;
        this.gender = gender;
        this.country = country;
    }

    public UserModel(int id, String fullname, String email, String gender, String country) {
        this.id = id;
        Fullname = fullname;
        this.email = email;
        this.gender = gender;
        this.country = country;
    }

    @Ignore
    public UserModel(String fullname, String gender, String country) {
        Fullname = fullname;
        this.gender = gender;
        this.country = country;
    }

    public UserModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
