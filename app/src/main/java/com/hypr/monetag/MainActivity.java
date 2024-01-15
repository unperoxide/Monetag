package com.hypr.monetag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;

import co.notix.appopen.AppOpenLoader;
import co.notix.appopen.NotixAppOpen;
import co.notix.interstitial.InterstitialLoader;
import co.notix.interstitial.NotixInterstitial;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {

    static AppOpenLoader appOpenLoader;
    static InterstitialLoader interstitialLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ad Zone
        int APP_OPEN_ZONE_ID = 6911072;
        int INTERSTITIAL_ZONE_ID = 6910728;

        AppCompatButton interstitialAdButton = findViewById(R.id.interstitialAdButton);

        appOpenLoader = NotixAppOpen.Companion.createLoader(APP_OPEN_ZONE_ID);
        appOpenLoader.startLoading();
        NotixAppOpen.Companion.startAutoShow(appOpenLoader);

        interstitialLoader = NotixInterstitial.Companion.createLoader(INTERSTITIAL_ZONE_ID);
        interstitialLoader.startLoading();

        interstitialAdButton.setOnClickListener(view -> {
            showInterstitialAd();
        });
    }

    private void showInterstitialAd() {
        interstitialLoader.doOnNextAvailable(result -> {
            if (result != null) {
                NotixInterstitial.Companion.show(result);
            }
            return Unit.INSTANCE;
        });
    }
}