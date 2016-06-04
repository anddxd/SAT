package com.salveaterra.salveaterra.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.salveaterra.salveaterra.R;
import com.salveaterra.salveaterra.api.API;

public class HistorinhasActivity extends AppCompatActivity {
    ImageButton ibPoluicao, ibAgua, ibDesmatamento, ibUrbanismo, ibCacaIlegal, ibVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historinhas);

        ibPoluicao = (ImageButton) findViewById(R.id.ibPoluicao);
        ibPoluicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.mudarTela(HistorinhasActivity.this, VideoActivity.class, "PoluicaoUrbana");
            }
        });

        ibAgua = (ImageButton) findViewById(R.id.ibAgua);
        ibAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.mudarTela(HistorinhasActivity.this, VideoActivity.class, "Agua");
            }
        });

        ibDesmatamento = (ImageButton) findViewById(R.id.ibDesmatamento);
        ibDesmatamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.mudarTela(HistorinhasActivity.this, VideoActivity.class, "Desmatamento");
            }
        });

        ibCacaIlegal = (ImageButton) findViewById(R.id.ibCacaIlegal);
        ibCacaIlegal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.mudarTela(HistorinhasActivity.this, VideoActivity.class, "CacaIlegal");
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
