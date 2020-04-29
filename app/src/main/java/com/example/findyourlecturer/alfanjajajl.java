package com.example.findyourlecturer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSuggestion;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class alfanjajajl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alfanjajajl);
        final WifiNetworkSuggestion suggestion1;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            suggestion1 = new WifiNetworkSuggestion.Builder()
                    .setSsid("Gedung AH Polinema")
                    .setIsAppInteractionRequired(true) // Optional (Needs location permission)
                    .build();

            final WifiNetworkSuggestion suggestion2 =
                    new WifiNetworkSuggestion.Builder()
                            .setSsid("test111111")
                            .setIsAppInteractionRequired(true) // Optional (Needs location permission)
                            .build();


            WifiNetworkSuggestion[] items = {suggestion1,suggestion2};
            final List<WifiNetworkSuggestion> suggestionList = new ArrayList<WifiNetworkSuggestion>(Arrays.asList(items));
            final WifiManager wifiManager =
                    (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            final int status = wifiManager.addNetworkSuggestions(suggestionList);
            if (status != WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
                Toast.makeText(getApplicationContext(), "error dong",
                        Toast.LENGTH_LONG).show();
// do error handling here…
            }
        }



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
        getApplicationContext().registerReceiver(broadcastReceiver,intentFilter);
    }
}
