package com.helpme.helpme.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.helpme.helpme.R;
import com.helpme.helpme.jsonparser.JSONParser;
import com.helpme.helpme.session.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText etUsename, etPassword;
    Button btnLogin;
    TextView tvRegister;

    String url, success;

    SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        mSessionManager = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status : " + mSessionManager.isLoggedIN(),
                Toast.LENGTH_SHORT).show();

        etUsename = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvRegister = (TextView) findViewById(R.id.tv_register);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(mIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://helpme-test.pe.hu/login/login.php?" + "username=" +
                        etUsename.getText().toString() + "&password=" +
                        etPassword.getText().toString();

                if (etUsename.getText().toString().trim().length() > 0  &&
                        etPassword.getText().toString().trim().length() > 0){
                    new Masuk().execute();
                }
            }
        });

    }

    public class Masuk extends AsyncTask<String, String, String> {

        ArrayList<HashMap<String,String>> mContactList = new ArrayList<HashMap<String,String>>();

        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(getApplicationContext());
            mProgressDialog.setMessage("Please Wait...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(true);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            JSONParser mJSONParser = new JSONParser();
            JSONObject mJSONObject = mJSONParser.getJSONFromUrl(url);

            try{
                success = mJSONObject.getString("success");
                Log.e("error", "nilai sukses = " + success);

                JSONArray mJSONArray = mJSONObject.getJSONArray("login");

                if(success.equals("1")){
                    for (int i = 0; i < mJSONArray.length(); i++){
                        JSONObject mJSONObject2 = mJSONArray.getJSONObject(i);

                        String nama = mJSONObject2.getString("nama").trim();
                        String username = mJSONObject2.getString("username").trim();
                        mSessionManager.createLoginSession(nama, username);
                        Log.e("OK", "Ambil Data");
                    }
                } else {
                    Log.e("Error","Tidak bisa ambil data 0");
                }

            } catch (Exception e) {
                Log.e("Error", "Tidak bisa ambil data 1");
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            mProgressDialog.dismiss();
            if (success.equals("1")){
                Intent mIntent = new Intent(getApplicationContext(), MyAccountActivity.class);
                startActivity(mIntent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Username atau Password Salah",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
