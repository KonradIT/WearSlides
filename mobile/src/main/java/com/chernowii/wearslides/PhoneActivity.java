package com.chernowii.wearslides;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneActivity extends AppCompatActivity {
    String IpAddress = "";
    String Port = "";
    String toastStatus = "";
    public static final String PREFS_NAME = "Preferences";
    public static final String ipsetting = "ip_address";
    public static final String portsetting = "ip_port";
    public static final String toastMessage = "toast_phone";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button config = (Button) findViewById(R.id.config);
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configure();
            }
        });
        WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo;

        wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getSupplicantState()== SupplicantState.COMPLETED) {
            TextView wifi = (TextView) findViewById(R.id.wifi);
            wifi.setText(wifiInfo.getSSID());
        }

    }
    public void configure(){
        EditText IPADDRESS = (EditText) findViewById(R.id.ipbox);
        IpAddress = IPADDRESS.getText().toString();
        EditText PORT = (EditText) findViewById(R.id.port);
        Port = PORT.getText().toString();
        boolean isChecked = ((CheckBox) findViewById(R.id.toast)).isChecked();
        if (isChecked){
            toastStatus = "true";
        }
        else{
            toastStatus = "false";
        }
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(ipsetting, IpAddress);
        editor.putString(portsetting, Port);
        editor.putString(toastMessage, toastStatus);
        editor.commit();
        Toast.makeText(getApplicationContext(),"All set! " + IpAddress, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phone, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_more_apps:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Konrad Iturbe - Chernowii")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/search?q=pub:Konrad Iturbe - Chernowii")));
                }

                return true;
            case R.id.action_donate:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.chernowii.donate")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.chernowii.donate")));
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
