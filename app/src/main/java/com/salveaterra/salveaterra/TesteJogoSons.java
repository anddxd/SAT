package com.salveaterra.salveaterra;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.salveaterra.salveaterra.api.API;

import java.io.IOException;
import java.util.ArrayList;

public class TesteJogoSons extends AppCompatActivity {
    Button btnAddOvelha, btnAddPorco, btnAddMacaco, btnTestePlay;
    MediaPlayer mp;
    ArrayList<Uri> listaDeSons = new ArrayList<>();
    Uri som;
    String path;
    int atual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_jogo_sons);
        mp = new MediaPlayer();
        btnAddOvelha = (Button) findViewById(R.id.btnAddOvelha);
        btnAddPorco = (Button) findViewById(R.id.btnAddPorco);
        btnAddMacaco = (Button) findViewById(R.id.btnAddMacaco);
        btnTestePlay = (Button) findViewById(R.id.btnTestePlay);

        path = "android.resource://" + getPackageName() + "/raw/som_ovelha";
        som = Uri.parse(path);
        listaDeSons.add(som);
        path = "android.resource://" + getPackageName() + "/raw/som_macaco";
        som = Uri.parse(path);
        listaDeSons.add(som);
        path = "android.resource://" + getPackageName() + "/raw/som_porco";
        som = Uri.parse(path);
        listaDeSons.add(som);

        btnAddOvelha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = "android.resource://" + getPackageName() + "/raw/som_ovelha";
                som = Uri.parse(path);
                listaDeSons.add(som);
            }
        });

        btnAddPorco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = "android.resource://" + getPackageName() + "/raw/som_porco";
                som = Uri.parse(path);
                listaDeSons.add(som);
            }
        });

        btnAddMacaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = "android.resource://" + getPackageName() + "/raw/som_macaco";
                som = Uri.parse(path);
                listaDeSons.add(som);
            }
        });

        btnTestePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mp.setDataSource(getApplicationContext(), listaDeSons.get(atual));
                    mp.prepare();
                    mp.start();
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            atual++;
                            mp.reset();
                            try {
                                if (atual < listaDeSons.size()) {
                                    mp.setDataSource(getApplicationContext(), listaDeSons.get(atual));
                                    mp.prepare();
                                    mp.start();
                                } else {
                                    atual = 0;
                                    mp.reset();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } catch (IOException e) {
                    API.mostrarToast(TesteJogoSons.this, "Erro", 0);
                    e.printStackTrace();
                }

            }
        });
    }
}
