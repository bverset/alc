package com.android.agendacontactos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.android.agendacontactos.preferences.CacheManager;

public class SplashActivity extends AppCompatActivity {

    private CacheManager cacheManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        cacheManager = new CacheManager(this);
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                /*if(cacheManager.isLoggin()){
                    i = new Intent(SplashActivity.this, MainActivity.class);
                }else{
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                }
                */
                i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}
