package com.karon.myapimainapp.views;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.karon.myapimainapp.R;

import java.util.Arrays;

//ca-app-pub-6331106558953185~7191395544
//TOP - ca-app-pub-6331106558953185/2130323329
//BOTTOM _ ca-app-pub-6331106558953185/6977441424
public class GoogleAdMobExample extends AppCompatActivity {

    private AdView adView;
    private AdView adViewTop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_google_ad_mob_example);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        MobileAds.initialize(this, initializationStatus -> {});
//        RequestConfiguration configuration = new RequestConfiguration.Builder()
//                .setTestDeviceIds(Arrays.asList("33C2D7C7F3A3B515667EE733C97C6AE3"))
//                .build();
//        MobileAds.setRequestConfiguration(configuration);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView = findViewById(R.id.adView);
       // adViewTop = findViewById(R.id.adViewTop);
        adView.loadAd(adRequest);
     //   adViewTop.loadAd(adRequest);
    }
}