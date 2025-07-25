package com.karon.myapimainapp.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.karon.myapimainapp.R;

import java.util.Arrays;

//ca-app-pub-6331106558953185~7191395544
//TOP - ca-app-pub-6331106558953185/2130323329
//BOTTOM _ ca-app-pub-6331106558953185/6977441424


//REWARD = ca-app-pub-6331106558953185/9488673244
//INT = ca-app-pub-6331106558953185/3788241119
public class GoogleAdMobExample extends AppCompatActivity {

    private AdView adView;
    Button btnInterstitial;
    Button btnRewarded;
    private AdView adViewTop;
    InterstitialAd interstitialAd;
    RewardedAd rewardedAd;
    private TextView txtCoins;
    private int coins = 0;
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

        btnInterstitial = (Button) findViewById(R.id.btnInterstitial);
        btnRewarded = findViewById(R.id.btnRewarded);
        txtCoins = findViewById(R.id.txtCoins);
        // Load Interstitial
        loadInterstitialAd();

        // Load Rewarded
        loadRewardedAd();

        btnInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd != null) {
                    interstitialAd.show(GoogleAdMobExample.this);
                }
            }
        });
        btnRewarded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedAd != null) {
                    rewardedAd.show(GoogleAdMobExample.this, rewardItem -> {
                        coins += rewardItem.getAmount();
                        txtCoins.setText("Coins: " + coins);
                    });
                } else {
                    Toast.makeText(GoogleAdMobExample.this, "Rewarded Ad not loaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void loadInterstitialAd()
    {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(GoogleAdMobExample.this, "ca-app-pub-3940256099942544/1033173712",
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd ad) {
                        interstitialAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                        interstitialAd = null;
                    }
                });
    }
    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                        rewardedAd = null;
                    }
                });
    }


}