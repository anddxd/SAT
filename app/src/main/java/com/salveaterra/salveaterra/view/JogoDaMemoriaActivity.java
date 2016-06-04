package com.salveaterra.salveaterra.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.salveaterra.salveaterra.R;
import com.salveaterra.salveaterra.adapter.ImageAdapter;
import com.salveaterra.salveaterra.api.API;

import java.util.ArrayList;
import java.util.Collections;

public class JogoDaMemoriaActivity extends AppCompatActivity {

    TextView txtTitulos;
    ImageButton ibVoltar;

    GridView gridView;

    ArrayList<Integer> cartas = new ArrayList<Integer>();
    ArrayList<Integer> cartasReveladas = new ArrayList<Integer>();
    Handler handler = new Handler();
    ImageAdapter adapter;
    AlertDialog alert;
    Dialog dialogVenceu;
    AlertDialog.Builder builder;


    int primeiraPeca = -1, segundaPeca = -1, quantidadeAcerto = 0, quantidadeDePecas = 0;
    View pecaAnterior;
    ArrayList ordem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_jogo_da_memoria);

        // Botao para voltar
        ibVoltar = (ImageButton) findViewById(R.id.btnVoltar);
        ibVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtTitulos = (TextView) findViewById(R.id.titulos);
        Typeface font = Typeface.createFromAsset(getAssets(), "titulo.ttf");
        txtTitulos.setTypeface(font);


        // gerarAlerta();


        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        gridView = (GridView) findViewById(R.id.gridview);

        gerarJogoDaMemoria(4);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaPlayer mp;
                final ImageView img = (ImageView) view;
                img.setClickable(true);
                if (Integer.parseInt(ordem.get(position).toString()) == 0) {
                    img.setImageResource(R.drawable.cachorro);
                    API.reproduzirSom(JogoDaMemoriaActivity.this, R.raw.som_cachorro);
                }
                if (Integer.parseInt(ordem.get(position).toString()) == 1) {
                    img.setImageResource(R.drawable.cobra);
                    API.reproduzirSom(JogoDaMemoriaActivity.this, R.raw.som_cobra);

                }
                if (Integer.parseInt(ordem.get(position).toString()) == 2) {
                    img.setImageResource(R.drawable.macaco);
                    API.reproduzirSom(JogoDaMemoriaActivity.this, R.raw.som_macaco);
                }
                if (Integer.parseInt(ordem.get(position).toString()) == 3) {
                    img.setImageResource(R.drawable.ovelha);
                    API.reproduzirSom(JogoDaMemoriaActivity.this, R.raw.som_ovelha);
                }
                if (Integer.parseInt(ordem.get(position).toString()) == 4) {
                    img.setImageResource(R.drawable.porco);
                    API.reproduzirSom(JogoDaMemoriaActivity.this, R.raw.som_porco);
                }
                if (Integer.parseInt(ordem.get(position).toString()) == 5) {
                    img.setImageResource(R.drawable.tucano);
                    // API.reproduzirSom(JogoDaMemoriaActivity.this, R.raw.som_porco);
                }
                if (Integer.parseInt(ordem.get(position).toString()) == 6) {
                    img.setImageResource(R.drawable.pato);
                    // API.reproduzirSom(JogoDaMemoriaActivity.this, R.raw.som_porco);
                }
                if (Integer.parseInt(ordem.get(position).toString()) == 7) {
                    img.setImageResource(R.drawable.rino);
                    // API.reproduzirSom(JogoDaMemoriaActivity.this, R.raw.som_porco);
                }

                if (primeiraPeca != -1) {
                    gridView.setEnabled(false);
                    segundaPeca = Integer.parseInt(ordem.get(position).toString());
                    if (segundaPeca == primeiraPeca) {
                        API.reproduzirSom(JogoDaMemoriaActivity.this, R.raw.som_acerto);
                        quantidadeAcerto++;
                        if (quantidadeAcerto == (quantidadeDePecas / 2)) {
                            API.reproduzirSom(JogoDaMemoriaActivity.this, R.raw.som_fase_concluida);
                            //alert.show();
                            gerarDialog();
                        }
                        gridView.setEnabled(true);
                    } else {
                        API.reproduzirSom(JogoDaMemoriaActivity.this, R.raw.som_erro);
                        if (Integer.parseInt(ordem.get(position).toString()) == 0)
                            img.setImageResource(R.drawable.cachorro);
                        if (Integer.parseInt(ordem.get(position).toString()) == 1)
                            img.setImageResource(R.drawable.cobra);
                        if (Integer.parseInt(ordem.get(position).toString()) == 2)
                            img.setImageResource(R.drawable.macaco);
                        if (Integer.parseInt(ordem.get(position).toString()) == 3)
                            img.setImageResource(R.drawable.ovelha);
                        if (Integer.parseInt(ordem.get(position).toString()) == 4)
                            img.setImageResource(R.drawable.porco);
                        if (Integer.parseInt(ordem.get(position).toString()) == 5)
                            img.setImageResource(R.drawable.tucano);
                        if (Integer.parseInt(ordem.get(position).toString()) == 6)
                            img.setImageResource(R.drawable.pato);
                        if (Integer.parseInt(ordem.get(position).toString()) == 7)
                            img.setImageResource(R.drawable.rino);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ImageView imgAnterior = (ImageView) pecaAnterior;
                                imgAnterior.setImageResource(R.drawable.carta);
                                img.setImageResource(R.drawable.carta);
                                img.setClickable(false);
                                imgAnterior.setClickable(false);
                                gridView.setEnabled(true);
                            }
                        }, 1000);
                    }
                    primeiraPeca = -1;
                } else {
                    primeiraPeca = Integer.parseInt(ordem.get(position).toString());
                    pecaAnterior = view;
                }
            }
        });


    }

    public void gerarAlerta() {
        builder = new AlertDialog.Builder(JogoDaMemoriaActivity.this);
        builder.setTitle("Você venceu!");
        builder.setMessage("O que você deseja fazer?");
        builder.setPositiveButton("Próximo Nível", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                quantidadeAcerto = 0;
                gerarJogoDaMemoria(quantidadeDePecas + 2);
            }
        });
        builder.setNegativeButton("Jogar Nível Novamente", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                quantidadeAcerto = 0;
                gerarJogoDaMemoria(quantidadeDePecas);
            }
        });
        alert = builder.create();

    }

    public void gerarJogoDaMemoria(int dificuldade) {
        if (dificuldade <= 16) {
            cartas.clear();
            cartasReveladas.clear();
            quantidadeDePecas = dificuldade;
            ordem = gerarPecas(quantidadeDePecas);
            for (int i = 0; i < quantidadeDePecas; i++) {
                cartas.add(R.drawable.carta);
            }
            for (int i = 0; i < quantidadeDePecas; i++) {
                if (Integer.parseInt(ordem.get(i).toString()) == 0)
                    cartasReveladas.add(R.drawable.cachorro);
                if (Integer.parseInt(ordem.get(i).toString()) == 1)
                    cartasReveladas.add(R.drawable.cobra);
                if (Integer.parseInt(ordem.get(i).toString()) == 2)
                    cartasReveladas.add(R.drawable.macaco);
                if (Integer.parseInt(ordem.get(i).toString()) == 3)
                    cartasReveladas.add(R.drawable.ovelha);
                if (Integer.parseInt(ordem.get(i).toString()) == 4)
                    cartasReveladas.add(R.drawable.porco);
                if (Integer.parseInt(ordem.get(i).toString()) == 5)
                    cartasReveladas.add(R.drawable.tucano);
                if (Integer.parseInt(ordem.get(i).toString()) == 6)
                    cartasReveladas.add(R.drawable.pato);
                if (Integer.parseInt(ordem.get(i).toString()) == 7)
                    cartasReveladas.add(R.drawable.rino);
            }
            adapter = new ImageAdapter(this, cartasReveladas);
            gridView.setAdapter(adapter);
            gridView.setNumColumns(quantidadeDePecas / 2);
            gridView.setEnabled(false);
            adapter = new ImageAdapter(this, cartas);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gridView.setAdapter(adapter);
                    gridView.setEnabled(true);
                }
            }, 1000);
        }
    }

    public ArrayList gerarPecas(int quantidadePecas) {
        ArrayList<Integer> arrayList = new ArrayList();

        for (int i = 0; i < quantidadePecas / 2; i++) {
            arrayList.add(i);
            arrayList.add(i);
        }

        Collections.shuffle(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        return arrayList;

    }

    public void gerarDialog() {

        dialogVenceu = new Dialog(JogoDaMemoriaActivity.this);
        dialogVenceu.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogVenceu.setContentView(R.layout.dialog_venceu);

        ImageButton btnNovamente = (ImageButton) dialogVenceu.findViewById(R.id.btnJogarNovamente);
        btnNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantidadeAcerto = 0;
                gerarJogoDaMemoria(quantidadeDePecas);
                dialogVenceu.dismiss();
            }
        });

        ImageButton btnProximo = (ImageButton) dialogVenceu.findViewById(R.id.btnProximoNivel);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantidadeAcerto = 0;
                gerarJogoDaMemoria(quantidadeDePecas + 2);
                dialogVenceu.dismiss();
            }
        });

        dialogVenceu.show();

    }
}
