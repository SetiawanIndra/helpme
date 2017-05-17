package com.helpme.helpme.ui.activity;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.Toast;

import com.helpme.helpme.R;
import com.helpme.helpme.session.SessionManager;
import com.helpme.helpme.ui.fragment.HomeFragment;
import com.helpme.helpme.ui.fragment.MyPostFragment;
import com.helpme.helpme.ui.fragment.MyProfileFragment;
import com.helpme.helpme.ui.fragment.NotifFragment;

import org.json.JSONArray;

import java.util.HashMap;

public class MenuActivity extends AppCompatActivity {

    ImageButton ibtnHome, ibtnNotif, ibtnMyPost, ibtnMyProfile;

    SessionManager mSessionManager;

    JSONArray mJSONArray = null;
    String nama, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ibtnHome = (ImageButton) findViewById(R.id.ibtn_home);
        ibtnNotif = (ImageButton) findViewById(R.id.ibtn_notif);
        ibtnMyPost = (ImageButton) findViewById(R.id.ibtn_myPost);
        ibtnMyProfile = (ImageButton) findViewById(R.id.ibtn_profile);

        mSessionManager = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(),"Login Success " + mSessionManager.isLoggedIN(),
                Toast.LENGTH_SHORT).show();

        mSessionManager.checkLogin();
        HashMap<String,String> mUser = mSessionManager.getUserDetails();
        nama = mUser.get(mSessionManager.KEY_NAME);
        username = mUser.get(mSessionManager.KEY_USERNAME);


        final FragmentManager mFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction mFragmentTransaction =
                mFragmentManager.beginTransaction();


        HomeFragment mHomeFragment = new HomeFragment();
        mFragmentTransaction.add(R.id.fl_contentFragment, mHomeFragment);
        mFragmentTransaction.addToBackStack("mHomeFragment");
        mFragmentTransaction.commit();


        ibtnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyProfileFragment mMyProfileFragment = new MyProfileFragment();

                mFragmentManager.beginTransaction().replace(R.id.fl_contentFragment,
                        mMyProfileFragment).addToBackStack(null)
                        .setTransition(android.support.v4.app.FragmentTransaction.
                                TRANSIT_FRAGMENT_FADE).commit();
            }
        });

        ibtnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotifFragment mNotifFragment = new NotifFragment();

                mFragmentManager.beginTransaction().replace(R.id.fl_contentFragment,
                        mNotifFragment).addToBackStack(null)
                        .setTransition(android.support.v4.app.FragmentTransaction.
                                TRANSIT_FRAGMENT_FADE).commit();
            }
        });

        ibtnMyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPostFragment mMyPostFragment = new MyPostFragment();

                mFragmentManager.beginTransaction().replace(R.id.fl_contentFragment,
                        mMyPostFragment).addToBackStack(null)
                        .setTransition(android.support.v4.app.FragmentTransaction.
                                TRANSIT_FRAGMENT_FADE).commit();
            }
        });

        ibtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment mHomeFragment = new HomeFragment();

                mFragmentManager.beginTransaction().replace(R.id.fl_contentFragment,
                        mHomeFragment).addToBackStack(null)
                        .setTransition(android.support.v4.app.FragmentTransaction.
                                TRANSIT_FRAGMENT_FADE).commit();
            }
        });


    }
}
