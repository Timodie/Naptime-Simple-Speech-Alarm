package com.example.tim.myapplication;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
//import com.github.paolorotolo.appintroexample.R;

/**
 * Created by Tim on 12/28/2015.
 * A class to display how to view weather details in MyIntro
 * Very important to have @Nullable in the parameters of onCreate
 */
public class Fragment1 extends Fragment {
    @Nullable

    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /**
         * Layout contains a webView which enables gif to be displayed
         */

        v = inflater.inflate(R.layout.activity_fragment1, container, false);
            // View v = v.
           WebView wv = (WebView) v.findViewById(R.id.webView);
            wv.loadUrl("file:///android_asset/weatherd.gif");
            wv.setClickable(false);

            return v;

    }

}
