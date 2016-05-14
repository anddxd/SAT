package com.salveaterra.salveaterra.view;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.salveaterra.salveaterra.R;
import com.salveaterra.salveaterra.api.API;

public class MainActivity extends AppCompatActivity {

    ImageView imgSol, imgLua;
    ImageView animacaoOlho;
    ImageView animacaoSono;
    ImageView olho_fechado;
    ImageView ibHistorinhas, ibJoguinhos;
    ImageButton planeta_claro, planeta_escuro;
    ImageView fundo_background;
    String status;
    public static final String PREFS_NAME = "Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        status = settings.getString("status", "");
        if (status.equals("lua")) {
            alterarAstro("sol");
            animarSono();
        } else {
            alterarAstro("lua");
            animarSol();
            animarOlho();
        }

    }

    /*@Override
    protected void onStop() {
        super.onStop();
        finish();
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void salvarConfiguracoes() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("status", status);
        editor.commit();
    }

    public void inicializarComponentes() {

        imgSol = (ImageView) findViewById(R.id.imgSol);
        imgSol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgSol.setClickable(false);
                alterarAstro("sol");
                status = "lua";
                salvarConfiguracoes();
            }
        });
        imgLua = (ImageView) findViewById(R.id.imgLua);
        imgLua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgLua.setClickable(false);
                alterarAstro("lua");
                animarSol();
                animarOlho();
                status = "sol";
                salvarConfiguracoes();

            }
        });

        fundo_background = (ImageView) findViewById(R.id.fundo_background);
        planeta_claro = (ImageButton) findViewById(R.id.planeta_claro);
        planeta_escuro = (ImageButton) findViewById(R.id.planeta_escuro);
        olho_fechado = (ImageView) findViewById(R.id.olho_fechado);
        animacaoSono = (ImageView) findViewById(R.id.animacao_sono);
        animacaoOlho = (ImageView) findViewById(R.id.animacao_olho);
        animacaoSono = (ImageView) findViewById(R.id.animacao_sono);

        ibHistorinhas = (ImageView) findViewById(R.id.ibHistorinhas);
        ibHistorinhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.reproduzirSom(MainActivity.this, R.raw.som_menu_click);
                API.mudarTela(MainActivity.this, HistorinhasActivity.class);
            }
        });

        ibJoguinhos = (ImageView) findViewById(R.id.ibJoguinhos);
        ibJoguinhos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API.reproduzirSom(MainActivity.this, R.raw.som_menu_click);
                API.mudarTela(MainActivity.this, JoguinhosActivity.class);
            }
        });


    }

    public void animarSol() {
        Animation girar = new RotateAnimation(0.0f, -360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        girar.setInterpolator(new LinearInterpolator());
        girar.setDuration(250);
        girar.setFillAfter(true);
        girar.setRepeatCount(Animation.INFINITE);
        girar.setDuration(10000);
        imgSol.startAnimation(girar);
    }

    public void animarOlho() {
        animacaoOlho.setBackgroundResource(R.drawable.animacao_olhos_piscando);

        AnimationDrawable piscar = (AnimationDrawable) animacaoOlho.getBackground();
        piscar.start();
    }

    public void animarSono() {
        animacaoSono.setBackgroundResource(R.drawable.animacao_sono);

        AnimationDrawable sono = (AnimationDrawable) animacaoSono.getBackground();
        sono.start();
    }

    public void alterarAstro(String astro) {
        final Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(0);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(0);

        if (astro.equals("sol")) {
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    imgSol.setVisibility(View.GONE);
                    planeta_claro.setVisibility(View.GONE);
                    imgLua.startAnimation(fadeIn);
                    imgLua.setVisibility(View.VISIBLE);
                    imgLua.setClickable(true);
                    planeta_escuro.setVisibility(View.VISIBLE);
                    planeta_escuro.startAnimation(fadeIn);
                    fundo_background.setVisibility(View.INVISIBLE);
                    olho_fechado.setVisibility(View.VISIBLE);
                    animacaoSono.setVisibility(View.VISIBLE);


                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });

            imgSol.startAnimation(fadeOut);

        } else if (astro.equals("lua")) {
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    imgLua.setVisibility(View.GONE);
                    planeta_escuro.setVisibility(View.GONE);
                    imgSol.startAnimation(fadeIn);
                    imgSol.setVisibility(View.VISIBLE);
                    imgSol.setClickable(true);
                    planeta_claro.setVisibility(View.VISIBLE);
                    planeta_claro.startAnimation(fadeIn);
                    fundo_background.setVisibility(View.VISIBLE);
                    fundo_background.startAnimation(fadeIn);
                    olho_fechado.setVisibility(View.INVISIBLE);
                    animacaoSono.setVisibility(View.INVISIBLE);

                    animarSol();
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });

            imgLua.startAnimation(fadeOut);
        }
    }
}
