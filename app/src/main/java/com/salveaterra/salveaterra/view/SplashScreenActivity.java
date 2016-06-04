package com.salveaterra.salveaterra.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.salveaterra.salveaterra.R;
import com.salveaterra.salveaterra.api.API;

public class SplashScreenActivity extends AppCompatActivity {

    // Acredito que seja 3 segundos o tempo
    private static int TEMPO_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                API.mudarTela(SplashScreenActivity.this,MainActivity.class);
                finish();
            }
        }, TEMPO_SPLASH);
    }
}
