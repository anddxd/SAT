package com.salveaterra.salveaterra.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salveaterra.salveaterra.R;


/**
 * Created by Gustavo on 25/04/2016.
 */
public class ImgViewPagerAdapter extends PagerAdapter {

    private Context ctx;
    private final int[] imagens;
    private String[] nomeHistoria ;


    public ImgViewPagerAdapter(Context ctx, int[] imagens, String[] nomeHistoria) {
        this.ctx = ctx;
        this.imagens = imagens;
        this.nomeHistoria = nomeHistoria;
    }

    @Override
    public int getCount() {
        return imagens != null ? imagens.length:0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(this.ctx).inflate(R.layout.adapter_imagem_historinha,container,false);
        ImageView img = (ImageView) view.findViewById(R.id.imgHistorinha);
        img.setImageResource(imagens[position]);
        TextView txt = (TextView) view.findViewById(R.id.txtNomeHist);
        txt.setText(nomeHistoria[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
