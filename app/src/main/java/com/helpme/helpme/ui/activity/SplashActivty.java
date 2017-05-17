package com.helpme.helpme.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.helpme.helpme.R;

public class SplashActivty extends AppCompatActivity {

    private static final int TIME_SPLASH = 3 * 1000; // 3 detik

    ImageView ivHmLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash_activty);

        ivHmLogo = (ImageView) findViewById(R.id.iv_hmLogo);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mIntent = new Intent(getApplicationContext(), MyAccountActivity.class);
                startActivity(mIntent);

                finish();
            }
        },TIME_SPLASH);
    }
}
