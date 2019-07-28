package com.candy.lovewallpaper.activity;
/*
 *  描述：    引导页
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.candy.lovewallpaper.MainActivity;
import com.candy.lovewallpaper.R;
import com.candy.lovewallpaper.entity.Constants;
import com.candy.lovewallpaper.utils.MyToast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class SplashActivity extends AppCompatActivity{
    private InterstitialAd mInterstitialAd;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constants.HANDLER_FIRST_START:
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MobileAds.initialize(this, "ca-app-pub-9744248562981900~9180748692");
        initView();
    }

    private void initView() {
        mHandler.sendEmptyMessageDelayed(Constants.HANDLER_FIRST_START,2000);



        AdRequest request = new AdRequest.Builder()
                .addTestDevice("281B9259A011928F416E2C701125F546")  // An example device ID
                .build();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9744248562981900/3806855280");

        mInterstitialAd.loadAd(request);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
                MyToast.showMessage(getApplicationContext(),"onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                MyToast.showMessage(getApplicationContext(),"onAdFailedToLoad"+errorCode);
            }

            @Override
            public void onAdOpened() {
                MyToast.showMessage(getApplicationContext(),"onAdOpened");
            }

            @Override
            public void onAdClicked() {
                MyToast.showMessage(getApplicationContext(),"onAdClicked");
            }

            @Override
            public void onAdLeftApplication() {
                MyToast.showMessage(getApplicationContext(),"onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                MyToast.showMessage(getApplicationContext(),"onAdClosed");
            }
        });


    }
}
