package com.helpme.helpme.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.helpme.helpme.R;
import com.helpme.helpme.session.SessionManager;

import org.json.JSONArray;

import java.util.HashMap;

public class MyAccountActivity extends AppCompatActivity {

    TextView tvTestNama, tvTestUsername;
    Button btnTestLogout;

    SessionManager mSessionManager;
    JSONArray mJSONArray = null;
    String nama, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        mSessionManager = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(),"Login Success " + mSessionManager.isLoggedIN(),
                Toast.LENGTH_SHORT).show();

        mSessionManager.checkLogin();
        HashMap<String,String> mUser = mSessionManager.getUserDetails();
        nama = mUser.get(mSessionManager.KEY_NAME);
        username = mUser.get(mSessionManager.KEY_USERNAME);


        tvTestNama = (TextView) findViewById(R.id.tv_testNama);
        tvTestUsername = (TextView) findViewById(R.id.tv_testUsername);
        btnTestLogout = (Button) findViewById(R.id.btn_testLogout);

        tvTestNama.setText(Html.fromHtml("Welcome <b>" + nama + "</b>"));

        btnTestLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSessionManager.logoutUser();
                finish();
            }
        });
    }
}
