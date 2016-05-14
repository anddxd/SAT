package com.salveaterra.salveaterra.view;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.salveaterra.salveaterra.R;
import com.salveaterra.salveaterra.adapter.ImageAdapter;
import com.salveaterra.salveaterra.api.API;

import java.util.ArrayList;
import java.util.Collections;

public class JogoDaMemoriaMusicalActivity extends AppCompatActivity {

    GridView gridView;
    ImageAdapter adapter;
    ArrayList<Integer> sapos = new ArrayList<Integer>();
    ArrayList<Integer> sequenciaGerada = new ArrayList<Integer>();
    ArrayList<View> sequenciaExecutada = new ArrayList<View>();
    int rodada = 1;
    int quantidadeApertada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_jogo_da_memoria_musical);
        gridView = (GridView) findViewById(R.id.gvMemoriaMusical);
        gerarAnimais();

        adapter = new ImageAdapter(this, sapos);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(2);
        gerarSequencia();

        API.mostrarToast(JogoDaMemoriaMusicalActivity.this, sequenciaGerada.get(0).toString(), 0);
        // tocar som animal
        reproduzirSom(0);
        // esperar click
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (sequenciaGerada.get(quantidadeApertada) == position) {
                    API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "Acertou", 0);
                    quantidadeApertada++;
                    if (quantidadeApertada == rodada) {
                        rodada++;
                        quantidadeApertada = 0;
                        for (int i = 0; i < rodada; i++) {
                            reproduzirSom(i);
                        }
                    }

                } else {
                    API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "Errou", 0);
                    finish();
                }
            }
        });
    }

    public void gerarAnimais() {
        sapos.add(R.drawable.ovelha);
        sapos.add(R.drawable.cachorro);
        sapos.add(R.drawable.porco);
        sapos.add(R.drawable.macaco);
    }

    public void gerarSequencia() {
        ArrayList<Integer> arrayList = new ArrayList();

        for (int i = 0; i < 100; i++) {
            arrayList.add(0);
            arrayList.add(1);
            arrayList.add(2);
            arrayList.add(3);
        }

        Collections.shuffle(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        sequenciaGerada = arrayList;
    }

    public void reproduzirSom(int position) {
        // ovelha
        if (sequenciaGerada.get(position) == 0) {
            //API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "ovelha", 0);
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_ovelha);
        }
        // cachorro
        else if (sequenciaGerada.get(position) == 1) {
            //API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "cachorro", 0);
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_cachorro);
        }
        // porco
        else if (sequenciaGerada.get(position) == 2) {
            //API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "porco", 0);
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_porco);
        }
        // macaco
        else if (sequenciaGerada.get(position) == 3) {
            //API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "macaco", 0);
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_macaco);
        }
    }
}
