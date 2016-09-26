package com.example.tim.myapplication;

/**
 * Created by Tim on 12/30/2015.
 * A class to display a little ticjkplus animation in myIntro
 */

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class Fragment3 extends  Fragment {
    @Nullable

    View v;
    /**
     * Layout has view component that displays animated object
     *Choreographer says too much work is being done on main thread
     * So I use another another thread to initialize the animation
     * */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v =inflater.inflate(R.layout.activity_fragment3,container,false);
        animation.run();


        return v;
    }
    public Thread animation = new Thread(){
        public void run(){
            final TickPlusDrawable tickPlusDrawable = new TickPlusDrawable(getResources().getDimensionPixelSize(R.dimen.stroke_width), getResources().getColor(android.R.color.holo_blue_dark), Color.WHITE);
            v.setBackground(tickPlusDrawable);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    tickPlusDrawable.toggle();
                }
            });
        }

    };
}
