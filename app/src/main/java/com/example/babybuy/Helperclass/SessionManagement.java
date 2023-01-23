package com.example.babybuy.Helperclass;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.babybuy.models.LoginModel;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    //Constructor for providing context to session
    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    //Saving session
    public void SaveSession(LoginModel user){
        int userid = user.getUserid();
        editor.putInt(SESSION_KEY,userid).commit();
    }
    //getting stored session
    public int getSession(){
        return sharedPreferences.getInt(SESSION_KEY,-1);
    }

    //Destroying the session
    public void removeSession(){
        editor.putInt(SESSION_KEY,-1).commit();
    }

}
