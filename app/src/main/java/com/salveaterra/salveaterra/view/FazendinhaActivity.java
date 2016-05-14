package com.salveaterra.salveaterra.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.salveaterra.salveaterra.R;
import com.salveaterra.salveaterra.api.API;

public class FazendinhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_fazendinha);

        FrameLayout areaPorco = (FrameLayout) findViewById(R.id.areaPorco);
        areaPorco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.mudarTela(FazendinhaActivity.this,PorcoActivity.class);
            }
        });



    }
}
