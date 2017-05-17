package com.helpme.helpme.session;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.helpme.helpme.ui.activity.LoginActivity;

import java.util.HashMap;

/**
 * Created by root on 16/05/17.
 */

@SuppressLint("CommitPrefEdits")
public class SessionManager  {

    SharedPreferences mSharedPref;

    SharedPreferences.Editor mEditor;

    Context mContext;


    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Sesi";

    public static final String IS_LOGIN = "isLoggedIn";

    public static final String KEY_NAME = "name_user";

    public static final String KEY_USERNAME = "username_user";

    public static final String KEY_ = "username_user";


    public SessionManager(Context mContext) {
        this.mContext = mContext;
        mSharedPref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mSharedPref.edit();
    }

    public void createLoginSession(String name, String username) {

        mEditor.putBoolean(IS_LOGIN,true);
        mEditor.putString(KEY_NAME, name);
        mEditor.putString(KEY_USERNAME, username);

        mEditor.commit();
    }

    public void checkLogin(){
        if(!this.isLoggedIN()){
            Intent mIntent = new Intent(mContext, LoginActivity.class);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            mContext.startActivity(mIntent);
        }
    }

    public HashMap<String,String> getUserDetails(){
        HashMap<String,String> mUser = new HashMap<String, String>();

        mUser.put(KEY_NAME, mSharedPref.getString(KEY_NAME, null));
        mUser.put(KEY_USERNAME, mSharedPref.getString(KEY_USERNAME, null));

        return mUser;
    }

    public void logoutUser(){
        mEditor.clear();
        mEditor.commit();

        Intent mIntent = new Intent(mContext,LoginActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        mContext.startActivity(mIntent);
    }

    public boolean isLoggedIN() {
        return mSharedPref.getBoolean(IS_LOGIN, false);
    }
}

