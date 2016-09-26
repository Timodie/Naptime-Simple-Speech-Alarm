package com.example.tim.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.Test;

import java.net.URL;
import java.net.URLConnection;

public class About extends AppCompatActivity {
    Bitmap bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        TextView licenses = (TextView) findViewById(R.id.licenses);
        licenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent license = new Intent(About.this, licenses.class);
                license.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(license);
            }
        });


        TextView textview = (TextView) findViewById(R.id.textView3);
        TextView textview3 = (TextView) findViewById(R.id.textView);
        TextView textview4 = (TextView) findViewById(R.id.textView2);
        textview.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textview2 = (TextView) findViewById(R.id.textView4);
        textview2.setMovementMethod(LinkMovementMethod.getInstance());

        Toolbar bar = (Toolbar) findViewById(R.id.tool_bar_info);
//        bar.getBackground().setAlpha(90);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("");
        bar.setNavigationIcon(R.drawable.ic_action_previous_item);
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(About.this, Naptime.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                finish();
            }
        });

        for (int i = 0; i < bar.getChildCount(); i++) {
            // make toggle drawable center-vertical, you can make each view alignment whatever you want
            if (bar.getChildAt(i) instanceof ImageButton) {
                Toolbar.LayoutParams lp = (Toolbar.LayoutParams) bar.getChildAt(i).getLayoutParams();
                lp.gravity = Gravity.CENTER_VERTICAL;
            }
        }

        Typeface t = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        TextView toolbar = (TextView) findViewById(R.id.toolbar_title_Info);
        toolbar.setTypeface(t);
        textview.setTypeface(t);
        textview2.setTypeface(t);
        textview3.setTypeface(t);
        textview4.setTypeface(t);

        URL url = null;
        try {
            url = new URL("http://timodie.github.io/weatherimages/mostly_cloudy_night.png");
            URLConnection conn = url.openConnection();
            bm = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap fm = BitmapFactory.decodeResource(getResources(), R.drawable.screenshotweather);

        ImageView v = (ImageView) findViewById(R.id.urlimageView2);
        v.setImageBitmap(bm);
    }
}
