package com.example.tim.myapplication;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
//import com.daimajia.androidanimations.library.attention.StandUpAnimator;
//import com.daimajia.slider.library.Animations.BaseAnimationInterface;

/**
 * Created by Tim on 12/29/2015.
 * A class to display background changes
 */
public class Fragment2 extends Fragment {

    @Nullable

    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
            * Layout contains a webView which enables gif to be displayed
         */

            v = inflater.inflate(R.layout.activity_fragment2, container, false);
        WebView wv = (WebView)v.findViewById(R.id.webView);
        wv.loadUrl("file:///android_asset/ezgifresized.gif");
//        wv.pageDown(false);
//        wv.setClickable(false);
//        wv.setEnabled(false);
//
//        wv.pageDown(false);
//        wv.pageUp(false);

        return v;

        }
    }

