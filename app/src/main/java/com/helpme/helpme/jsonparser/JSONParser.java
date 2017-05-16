package com.helpme.helpme.jsonparser;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BufferedHeader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by root on 16/05/17.
 */

public class JSONParser {
    static InputStream mInputStream = null;
    static JSONObject mJSONObject = null;
    static String json = "";

    public JSONParser() {

    }


    public JSONObject getJSONFromUrl(String url) {
        try {
            DefaultHttpClient mHttpClient = new DefaultHttpClient();
            HttpPost mHttpPost = new HttpPost(url);

            HttpResponse mHttpResonse = mHttpClient.execute(mHttpPost);
            HttpEntity mHttpEntity = mHttpResonse.getEntity();
            mInputStream = mHttpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(
                    mInputStream, "iso-8859-1"),8);
            StringBuilder mStringBuilder = new StringBuilder();
            String line = null;
            while ((line = mBufferedReader.readLine()) != null){
                mStringBuilder.append(line + "\n");
            }
            mInputStream.close();
            json = mStringBuilder.toString();

        } catch (IOException e) {
            Log.e("Buffer Error", "Error Converting Result " + e.toString());
        }


        return mJSONObject;
    }

    public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params){

        try{

            if (method == "POST"){

            }
        }

    }



}
