package com.salveaterra.salveaterra.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.salveaterra.salveaterra.R;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Para abrir em fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //TODO: modificar acesso ao video através de botões
        setContentView(R.layout.activity_video);

        VideoView vd = (VideoView) findViewById(R.id.video);

        Intent i = getIntent();
        String nomeVideo = i.getExtras().getString("nomeVideo");
        Uri uri = null;
        switch (nomeVideo) {
            case "PoluicaoUrbana":
                uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.poluicao_urbana);
                break;
            case "Agua":
                uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_agua);
                break;
            case "CacaIlegal":
                uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_caca_ilegal);
                break;
            case "Desmatamento":
                uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_desmatamento);
                break;
        }

        MediaController mc = new MediaController(this);
        vd.setMediaController(mc);

        vd.setVideoURI(uri);
        vd.start();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
