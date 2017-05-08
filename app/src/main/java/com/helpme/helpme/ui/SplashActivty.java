package com.helpme.helpme.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.helpme.helpme.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class SplashActivty extends AppCompatActivity {

    TextView tvTitleSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash_activty);

        tvTitleSplash = (TextView) findViewById(R.id.tv_titleSplash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivities(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        },3000L);
    }
}
