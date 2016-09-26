package com.example.tim.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.SuperActivityToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
//import com.squareup.mimecraft.Multipart;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class RingTone extends AppCompatActivity {
    ListView music;
    String []items;
    OutputStream outputStream;
    InputStream fileInputStream;
    String filename = "song";
    private MediaPlayer mp;
    int selected =0;//to prevent more than one song from playing at a time


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_tone);

        Typeface t = Typeface.createFromAsset(getAssets(),"Raleway-Regular.ttf");
        TextView toolbar = (TextView)findViewById(R.id.toolbar_title_Info);
        toolbar.setTypeface(t);

        Toolbar bar = (Toolbar)findViewById(R.id.tool_bar_info);
//        bar.getBackground().setAlpha(90);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("");
        bar.setNavigationIcon(R.drawable.ic_action_previous_item);
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RingTone.this,Naptime.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                if(mp!=null || mp.isPlaying()){
                    SuperActivityToast.create(RingTone.this, "Supposed to stop", SuperToast.Duration.EXTRA_LONG, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
                    mp.stop();
                }
                RingTone.this.finish();
            }
        });

        for (int i = 0; i < bar.getChildCount(); i++) {
            // make toggle drawable center-vertical, you can make each view alignment whatever you want
            if (bar.getChildAt(i) instanceof ImageButton) {
                Toolbar.LayoutParams lp = (Toolbar.LayoutParams) bar.getChildAt(i).getLayoutParams();
                lp.gravity = Gravity.CENTER_VERTICAL;
            }
        }


        music = (ListView)findViewById(R.id.musiclist);

        final ArrayList<File> mysongs = findSongs(Environment.getExternalStorageDirectory());
        items = new String[mysongs.size()];
        for(int i=0; i<mysongs.size(); i++){
            items[i] = mysongs.get(i).getName().toString();
        }

        if(items.length == 0){
            SuperActivityToast.create(RingTone.this, "No songs found", SuperToast.Duration.EXTRA_LONG, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>((getApplicationContext()), android.R.layout.simple_list_item_1, items);
        music.setAdapter(adp);
        music.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //bump up count when song is clicked
                selected++;
                //if

                if(mysongs != null){
                    File s = mysongs.get(position);
                    Uri uri;
                    uri = Uri.parse(s.toString());
                    mp = MediaPlayer.create(RingTone.this,uri);
                    if(mp!=null){
                       int session1 = mp.getAudioSessionId();
                        SuperActivityToast.create(RingTone.this, "Id is"+session1, SuperToast.Duration.VERY_SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();

                        CountDownTimer cd = new CountDownTimer(5000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                mp.start();
                                int session2 = mp.getAudioSessionId();
                                SuperActivityToast.create(RingTone.this, "Id2 is"+session2, SuperToast.Duration.VERY_SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
                            }

                            @Override
                            public void onFinish() {
                               mp.pause();
                                //mp.seekTo(0);
                                mp.stop();
                            }
                        };cd.start();
                    }


                    byte[] bFile = new byte[(int) s.length()];

                    try {
                        //convert file into array of bytes
                        fileInputStream = new FileInputStream(s);
                        fileInputStream.read(bFile);
                        fileInputStream.close();

                        //convert array of bytes into file
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        //customized toast
//                    LayoutInflater inflater = getLayoutInflater();
//                    View layout = inflater.inflate(R.layout.toast,
//                            (ViewGroup) findViewById(R.id.toast_layout_root));
//                    TextView text = (TextView) layout.findViewById(R.id.text);
//                    Typeface tf3 = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
//                    text.setTypeface(tf3);
//                    text.setText("Saved");
//                    Toast toast = new Toast(getApplicationContext());
//                    toast.setDuration(Toast.LENGTH_SHORT);
//                    toast.setView(layout);
//                    toast.show();
                        SuperActivityToast.create(RingTone.this, "Saved!", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
                        outputStream.write(bFile);
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private ArrayList<File> findSongs(File root){
        ArrayList<File> al = new ArrayList<File>();
        File[] f = root.listFiles();
        if(f==null)
            return null;
        for(File Single :f){
            if(Single.isDirectory() && !Single.isHidden()) {
                al.addAll(findSongs(Single));
            }
            else{
                if(Single.getName().endsWith(".mp3") || Single.getName().endsWith(".wav")){
                    al.add(Single);
                }
            }
        }
        return al;
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(RingTone.this,Naptime.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        if(mp!=null ){
            SuperActivityToast.create(RingTone.this, "Supposed to stop", SuperToast.Duration.EXTRA_LONG, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
            mp.stop();
        }
        startActivity(i);
        RingTone.this.finish();

    }
}
