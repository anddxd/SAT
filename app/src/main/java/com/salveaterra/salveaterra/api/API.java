package com.salveaterra.salveaterra.api;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;



/**
 * Created by Andrey Medeiros on 30/03/2016.
 */
public class API {
    /**
     * Created by Andrey
     * @since 30/03/2016
     * @param telaOrigem - contexto da tela de origem
     * @param telaDestino - nome da tela de destino
     */
    public static void mudarTela(Context telaOrigem, Class telaDestino) {
        Intent i = new Intent(telaOrigem, telaDestino);
        telaOrigem.startActivity(i);
    }

    public static void mudarTela(Context telaOrigem, Class telaDestino, String parametro) {
        Intent i = new Intent(telaOrigem, telaDestino);
        i.putExtra("nomeVideo", parametro);
        telaOrigem.startActivity(i);
    }

    /**
     * Created by Andrey
     * @since 30/03/2016
     * @param telaOrigem - contexto da tela de origem
     * @param mensagem - mensagem que será exibida no toast
     * @param duracao - duração do toast, onde 0 é curta e 1 é longa
     */
    public static void mostrarToast(Context telaOrigem, String mensagem, int duracao){
        if(duracao == 0){
            Toast toast = Toast.makeText(telaOrigem, mensagem, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(telaOrigem, mensagem, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * Created by Andrey Medeiros
     * @param telaOrigem
     * @param som
     */
    public static void reproduzirSom(Context telaOrigem, int som){
        MediaPlayer mp;
        try {
            mp = MediaPlayer.create(telaOrigem, som);
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
