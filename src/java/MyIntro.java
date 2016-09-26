package com.example.tim.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Tim on 12/26/2015.
 * Inherit AppIntro
 */
public class MyIntro extends AppIntro {


    @Override
    public void init(Bundle savedInstanceState) {


        /**
         * Bar color black with white indicators, and others
         */
        setBarColor(Color.parseColor("#000000"));
        setProgressBarVisibility(true);
        setVisible(false);
        setDoneText("Get Started");

        /**
         * addSlide(AppIntro...) implements sample fragments from library, addSlide(new Fragment) implements my own fragment
         */

        addSlide(AppIntroFragment.newInstance("Welcome ", "What's New?", R.drawable.logo4, Color.parseColor("#000000") ,Color.parseColor("#cdc9c9"), Color.parseColor("#cdc9c9")));
        //slide background gif
        addSlide(new Fragment2()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               );
        addSlide(AppIntroFragment.newInstance("Weather", "You now have quick and easy weather info", R.drawable.screenshot2crop, Color.parseColor("#2196F3")));
        addSlide(AppIntroFragment.newInstance("Weather Details", "Touch weather info to view weather details", R.drawable.weatherdetailscrop, Color.parseColor("#2196F3")));
         //weather details gif
        addSlide(new Fragment1());
        //all set fragment
        addSlide(new Fragment3());

        showSkipButton(false);

        setFadeAnimation();
        //setZoomAnimation();
        //setFlowAnimation();
       // setSlideOverAnimation();
       // setDepthAnimation();

    }



    private void loadMainActivity() {
        Intent intent = new Intent(MyIntro.this, Naptime.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
        //Toast.makeText(getApplicationContext(),getString(R.string.skip),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onSlideChanged() {

    }

    public void getStarted(View v) {
        loadMainActivity();
    }


    /**
     * A page transformer that's a bit dramatic lol
     */


    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);

            }
        }
    }

    /**
     * Doesnt really work i'll figure out wby later
     */









}













