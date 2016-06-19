package com.salveaterra.salveaterra.view;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.salveaterra.salveaterra.R;
import com.salveaterra.salveaterra.adapter.ImageAdapter;
import com.salveaterra.salveaterra.api.API;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class JogoDaMemoriaMusicalActivity extends AppCompatActivity {

    GridView gridView;
    ImageAdapter adapter;
    ArrayList<Integer> sapos = new ArrayList<Integer>();
    ArrayList<Integer> sequenciaGerada = new ArrayList<Integer>();
    //ArrayList<View> sequenciaExecutada = new ArrayList<View>();
    MediaPlayer mp;
    ArrayList<Uri> listaDeSons = new ArrayList<>();
    Uri som;
    String path;
    int atual = 0;
    int rodada = 1;
    int quantidadeApertada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mp = new MediaPlayer();
        setContentView(R.layout.activity_jogo_da_memoria_musical);
        gridView = (GridView) findViewById(R.id.gvMemoriaMusical);
        gerarAnimais();

        adapter = new ImageAdapter(this, sapos);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(2);
        gerarSequencia();

        API.mostrarToast(JogoDaMemoriaMusicalActivity.this, sequenciaGerada.get(0).toString(), 0);
        // tocar som animal
        if (sequenciaGerada.get(0) == 0) {
            API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "ovelha", 0);
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_ovelha);
            path = "android.resource://" + getPackageName() + "/raw/som_ovelha";
            som = Uri.parse(path);
            listaDeSons.add(som);
        } else if(sequenciaGerada.get(0) == 1){
            API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "cachorro", 0);
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_cachorro);
            path = "android.resource://" + getPackageName() + "/raw/som_cachorro";
            som = Uri.parse(path);
            listaDeSons.add(som);
        } else if(sequenciaGerada.get(0) == 2){
            API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "porco", 0);
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_porco);
            path = "android.resource://" + getPackageName() + "/raw/som_porco";
            som = Uri.parse(path);
            listaDeSons.add(som);
        } else if(sequenciaGerada.get(0) == 3){
            API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "macaco", 0);
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_macaco);
            path = "android.resource://" + getPackageName() + "/raw/som_macaco";
            som = Uri.parse(path);
            listaDeSons.add(som);
        }
        //criarListaDeSons(0);
        // esperar click
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (sequenciaGerada.get(quantidadeApertada) == position) {
                    tocarSons(position);
                    API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "Acertou", 0);
                    quantidadeApertada++;
                    if (quantidadeApertada == rodada) {
                        rodada++;
                        quantidadeApertada = 0;
                        //for (int i = 0; i < rodada; i++) {
                            criarListaDeSons(position);
                        //}
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
                            API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "Erro", 0);
                            e.printStackTrace();
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

    public void criarListaDeSons(int position) {
        // ovelha
        if (sequenciaGerada.get(position) == 0) {
            API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "ovelha", 0);
            //API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_ovelha);
            path = "android.resource://" + getPackageName() + "/raw/som_ovelha";
            som = Uri.parse(path);
            listaDeSons.add(som);
        }
        // cachorro
        else if (sequenciaGerada.get(position) == 1) {
            API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "cachorro", 0);
            //API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_cachorro);
            path = "android.resource://" + getPackageName() + "/raw/som_cachorro";
            som = Uri.parse(path);
            listaDeSons.add(som);
        }
        // porco
        else if (sequenciaGerada.get(position) == 2) {
            API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "porco", 0);
            //API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_porco);
            path = "android.resource://" + getPackageName() + "/raw/som_porco";
            som = Uri.parse(path);
            listaDeSons.add(som);
        }
        // macaco
        else if (sequenciaGerada.get(position) == 3) {
            API.mostrarToast(JogoDaMemoriaMusicalActivity.this, "macaco", 0);
            //API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_macaco);
            path = "android.resource://" + getPackageName() + "/raw/som_macaco";
            som = Uri.parse(path);
            listaDeSons.add(som);
        }
    }

    public void tocarSons(int position) {
        // ovelha
        if (sequenciaGerada.get(position) == 0) {
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_ovelha);
        }
        // cachorro
        else if (sequenciaGerada.get(position) == 1) {
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_cachorro);
        }
        // porco
        else if (sequenciaGerada.get(position) == 2) {
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_porco);
        }
        // macaco
        else if (sequenciaGerada.get(position) == 3) {
            API.reproduzirSom(JogoDaMemoriaMusicalActivity.this, R.raw.som_macaco);
        }
    }
}
