package com.salveaterra.salveaterra.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.salveaterra.salveaterra.R;
import com.salveaterra.salveaterra.api.API;

public class JoguinhosActivity extends AppCompatActivity {
    ImageButton jogoDaMemoria, game2, game3, ibVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joguinhos);

        jogoDaMemoria = (ImageButton) findViewById(R.id.game1);
        jogoDaMemoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.mudarTela(JoguinhosActivity.this, JogoDaMemoriaActivity.class);
            }
        });

        /*game2 = (ImageButton) findViewById(R.id.game2);
        game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.mudarTela(JoguinhosActivity.this, FazendinhaActivity.class);
            }
        });*/

        game3 = (ImageButton) findViewById(R.id.game3);
        game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.mudarTela(JoguinhosActivity.this, JogoDaMemoriaMusicalActivity.class);
            }
        });

        ibVoltar = (ImageButton) findViewById(R.id.btnVoltar);
        ibVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
