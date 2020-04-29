package com.example.findyourlecturer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSuggestion;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thanosfisherman.wifiutils.WifiUtils;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static com.example.findyourlecturer.R.layout.activity_menu_utama_user;

public class MenuUtamaUserActivity extends AppCompatActivity {


/*
    Button btn;
    String ote = "";
    String otePass = "";
    Integer logic = 0;
*/
    private Bundle savedInstanceState;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(activity_menu_utama_user);

/*
        WifiUtils.enableLog(true);
        if (logic ==0) {
            connectWithWpa();
        }
*/
/*
        else if (logic ==1){
            connectWithWpadua();
        }
*/
/*
        else if (logic ==2){
            connectWithWpatiga();
        }
*/

/*
        btn=(Button) findViewById(R.id.btinfo1);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                showDialog();
            }
        });
*/
    }



/*
    private void connectWithWpa() {
        ote = "Novian Arma";
        otePass = "novianar";
        WifiUtils.withContext(getApplicationContext())
                .connectWith(ote, otePass)
                .setTimeout(40000)
                .onConnectionResult(this::checkResult)
                .start();
    }
*/

/*
    private void connectWithWpadua() {
        ote = "Gedung AL Polinema";
        otePass = "";
        WifiUtils.withContext(getApplicationContext())
                .connectWith(ote, otePass)
                .setTimeout(40000)
                .onConnectionResult(this::checkResultdua)
                .start();
    }
*/

/*
    private void connectWithWpatiga() {
        ote = "Novian Arma";
        otePass = "novianar";
        WifiUtils.withContext(getApplicationContext())
                .connectWith(ote, otePass)
                .setTimeout(40000)
                .onConnectionResult(this::checkResulttiga)
                .start();
    }
*/



/*
    private void checkResult(boolean isSuccess) {
        if (isSuccess){
            fAuth = FirebaseAuth.getInstance();
            String user = fAuth.getCurrentUser().getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("user").child("Dosen").child(user+"/ssid");
            myRef.setValue(ote);
        }
        else{
            Toast.makeText(MenuUtamaUserActivity.this, "Menyambungkan ke Wifi Novian", Toast.LENGTH_SHORT).show();
            logic =0;

        }
    }
*/

/*
    private void checkResultdua(boolean isSuccess) {
        if (isSuccess){
            fAuth = FirebaseAuth.getInstance();
            String user = fAuth.getCurrentUser().getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("user").child("Dosen").child(user+"/ssid");
            myRef.setValue(ote);
        }
        else{
            Toast.makeText(MenuUtamaUserActivity.this, "Menyambungkan ke Wifi Gedung AL", Toast.LENGTH_SHORT).show();
            logic =1;

        }
    }
*/

/*
    private void checkResulttiga(boolean isSuccess) {
        if (isSuccess){
            fAuth = FirebaseAuth.getInstance();
            String user = fAuth.getCurrentUser().getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("user").child("Dosen").child(user+"/ssid");
            myRef.setValue(ote);
        }
        else{
            Toast.makeText(MenuUtamaUserActivity.this, "Berada di area kampus", Toast.LENGTH_SHORT).show();
            logic = 2;
        }
    }
*/


    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected void onStart(){
        super.onStart();
        Toast.makeText(this, "sedang menyambungkan wifi ...", Toast.LENGTH_SHORT).show();


/*
        final WifiNetworkSuggestion suggestion2 =
                new WifiNetworkSuggestion.Builder()
                        .setSsid("Gedung AH Polinema")
                        .setWpa2Passphrase("novianarma")
                        .build();

        final WifiNetworkSuggestion suggestion3 =
                new WifiNetworkSuggestion.Builder()
                        .setSsid("Novian Arma")
                        .setWpa3Passphrase("novianar")
                        .build();

        final List<WifiNetworkSuggestion> suggestionsList =
                new ArrayList<WifiNetworkSuggestion>(){{
                    add(suggestion2);
                    add(suggestion3);
        }};

        final WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        final int status = wifiManager.addNetworkSuggestions(suggestionsList);
        if (status != WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
        // do error handling here…
        }

        // Optional (Wait for post connection broadcast to one of your suggestions)
        final IntentFilter intentFilter =
                new IntentFilter(WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION);

        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!intent.getAction().equals(
                        WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION)) {
                    return;
                }
                // do post connect processing here...
            }
        };
        getApplicationContext().registerReceiver(broadcastReceiver, intentFilter);
*/
    }




    public void showDialog(){
        Dialog dialog=new Dialog(MenuUtamaUserActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialoguser);
        dialog.show();
    }



    public void btcaridosen(View view) {
        Intent intent = new Intent(MenuUtamaUserActivity.this, CariDosenActivity.class);
        startActivity(intent);
    }
    public void btberitahusaya(View view) {
        Intent intent = new Intent(MenuUtamaUserActivity.this, BeritahuSayaActivity.class);
        startActivity(intent);
    }

    public void btlogout1(View view) {
        FirebaseAuth.getInstance().signOut();
/*
        Toast.makeText(MenuUtamaAdminActivity.this, "Hasilnyaaa" , Toast.LENGTH_SHORT).show(); //mengecek
*/
        Intent intent = new Intent(MenuUtamaUserActivity.this, LoginUserActivity.class);
        startActivity(intent);
        session.setLoggedInStatusAdmin(getBaseContext(),false);
        finish();
    }

}
