package com.example.tim.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.johnpersano.supertoasts.SuperActivityToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;

public class First extends Activity implements OnClickListener {

    SharedPreferences help;
     SharedPreferences.Editor helpEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        help = getSharedPreferences("help", Context.MODE_PRIVATE);
        helpEd = help.edit();

        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.setDuration(1000);
        container.startShimmerAnimation();

        Snackbar sb = Snackbar.make(findViewById(R.id.first), "Kind Courtesy of Tim and Vincent", Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View v = sb.getView();
        v.setBackgroundColor(1);
        TextView textView = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        Typeface tf = Typeface.createFromAsset(getAssets(), "GreatVibes-Regular.otf");
        textView.setTypeface(tf);
        sb.show();

        //delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!help.contains("tim")) {
                    SuperActivityToast.create(First.this, "coach tested", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
                    helpEd.putString("tim", "tim");
                    helpEd.commit();

                    final Intent coachIntent = new Intent(First.this, MyIntro.class);
                    First.this.startActivity(coachIntent);
                    overridePendingTransition(R.layout.fade_in, R.layout.fade_out);
                    SuperActivityToast.create(First.this, "testing coach", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();


                    First.this.finish();

                } else {
                    final Intent mainIntent = new Intent(First.this, Naptime.class);
                    First.this.startActivity(mainIntent);
                    overridePendingTransition(R.layout.fade_in, R.layout.fade_out);
                    First.this.finish();
                }
            }
        }, 2000);

    }

    @Override
    public void onClick(View v) {

    }
}
