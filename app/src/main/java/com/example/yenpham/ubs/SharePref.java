package com.example.yenpham.ubs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Yenpham on 11/14/16.
 */

public class SharePref {


    SharedPreferences sharepreferences;

    public static SharePref instance = null;
    public static int currentID =0;

    public static SharePref getInstance()
    {

        if (instance == null) {
            synchronized (SharePref.class) {
                instance = new SharePref();
            }
        }
        return instance;
    }
    public void saveISLogged_IN(Context context, Boolean isLoggedin) {
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharepreferences.edit();
        editor.putBoolean("IS_LOGIN", isLoggedin);
        editor.commit();
    }

    public boolean getISLogged_IN(Context context) {
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharepreferences.getBoolean("IS_LOGIN", false);
    }
    public void saveUserID(Context context, int userID){
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharepreferences.edit();
        editor.putInt("USER_ID", userID);
        editor.commit();
    }
    public int getUserID(Context context){
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharepreferences.getInt("USER_ID", 0);
    }


    public void saveIsFirstWelcome(Context context , boolean isFirst){
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharepreferences.edit();
        editor.putBoolean("IS_FIRST", isFirst);
        editor.commit();
    }
    public boolean getIsFirstWelcome(Context context) {
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharepreferences.getBoolean("IS_FIRST", false);
    }
    public int generateUserID(Context context){
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharepreferences.edit();
        currentID++;
        editor.putInt("currentID",currentID );
        editor.commit();
        return sharepreferences.getInt("currentID", currentID);
    }


}


