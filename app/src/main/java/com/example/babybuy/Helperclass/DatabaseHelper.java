package com.example.babybuy.Helperclass;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.babybuy.DAO.UserDao;
import com.example.babybuy.models.LatLongModel;
import com.example.babybuy.models.LoginModel;
import com.example.babybuy.models.Products;
import com.example.babybuy.models.PurchaseList;
import com.example.babybuy.models.UserModel;
import com.example.babybuy.models.Wishlist;

@Database(entities = {UserModel.class, LoginModel.class, Products.class, Wishlist.class, PurchaseList.class, LatLongModel.class}, exportSchema = false, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    public static final String DB_NAME = "babybuydb";
    public static DatabaseHelper instance;

    public static synchronized DatabaseHelper getDB(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context, DatabaseHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
    public abstract UserDao userDao();
}
