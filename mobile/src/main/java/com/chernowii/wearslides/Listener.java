package com.chernowii.wearslides;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Konrad Iturbe on 02/19/16.
 */
public class Listener extends WearableListenerService {
    private static final String prev = "/prev";
    private static final String next = "/next";
    public static final String PREFS_NAME = "Preferences";
    public static final String ipsetting = "ip_address";
    public static final String portsetting = "ip_port";
    public static final String toastMessage = "toast_phone";
    public String IPADDRESS = "";
    String Port = "";
    String toastStatus = "";
    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        SharedPreferences settings;
        settings = getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        IPADDRESS = settings.getString(ipsetting, null);
        Port = settings.getString(portsetting, null);//2
        if (messageEvent.getPath().equals(prev)) {
            new HttpAsyncTask().execute("http://" + IPADDRESS + ":" + Port + "/prev");

        }

        if (messageEvent.getPath().equals(next)) {
            new HttpAsyncTask().execute("http://" + IPADDRESS + ":" + Port + "/next");

        }

    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            SharedPreferences settings;
            settings = getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
            toastStatus = settings.getString(toastMessage, null);
            if (toastStatus != null) {
                if (toastStatus.equals("true")){
                    Toast.makeText(getBaseContext(), "Command sent!", Toast.LENGTH_SHORT).show();
                }

            }
            ;
        }
    }
}


