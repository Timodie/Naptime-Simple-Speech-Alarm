package com.example.tim.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.SuperActivityToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.OutputStream;

public class Info extends AppCompatActivity {

    MaterialEditText et;
    MaterialEditText wkupmsg;
    MaterialEditText place;
    public String UserName;
    SharedPreferences sp;
    OutputStream outputStream;

    private void setinfo(View view){
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", et.getText().toString());
        editor.apply();
        SuperActivityToast.create(Info.this, "Saved!", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();

    }

    private void setinfo2(View view){
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("wkupmsg", wkupmsg.getText().toString());
        editor.apply();


        SuperActivityToast.create(Info.this, "Saved!", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
    }

    private void setinfo3(View view){
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("place", place.getText().toString());
        editor.apply();
        SuperActivityToast.create(Info.this, "Saved!", SuperToast.Duration.SHORT, Style.getStyle(Style.BLUE, SuperToast.Animations.FADE)).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        UserName = " ";

        //get username
        et = (MaterialEditText)findViewById(R.id.editText);
        et.setText(sp.getString("username",""));
        wkupmsg = (MaterialEditText)findViewById(R.id.editwkupmsg);
        wkupmsg.setText(sp.getString("wkupmsg", ""));
        wkupmsg.setSelected(false);
        et.setSelected(false);
        place = (MaterialEditText)findViewById(R.id.editplace);
        place.setText(sp.getString("place",""));
        place.setSelected(false);


        //change font here
        Typeface t1= Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        TextView toolbartitle = (TextView)findViewById(R.id.toolbar_title_Info);
        toolbartitle.setTypeface(t1);
        et.setTypeface(t1);
        wkupmsg.setTypeface(t1);
        place.setTypeface(t1);

        //action bar
        Toolbar bar = (Toolbar)findViewById(R.id.tool_bar_info);
        bar.getBackground().setAlpha(90);
        setSupportActionBar(bar);
        //no need title
        getSupportActionBar().setTitle("");
        //set NavigationIcon action
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                Intent intent = new Intent(Info.this, Naptime.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
        bar.setNavigationIcon(R.drawable.ic_action_previous_item);
        for (int i = 0; i < bar.getChildCount(); i++) {
            // make toggle drawable center-vertical, you can make each view alignment whatever you want
            if (bar.getChildAt(i) instanceof ImageButton) {
                Toolbar.LayoutParams lp = (Toolbar.LayoutParams) bar.getChildAt(i).getLayoutParams();
                lp.gravity = Gravity.CENTER_VERTICAL;
            }
        }


        ImageButton set= (ImageButton)findViewById(R.id.setButton);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setinfo(v);
                //hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
            }
        });

        ImageButton set2 = (ImageButton)findViewById(R.id.setButton2);
        set2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setinfo2(v);
                //hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
            }
        });


        ImageButton set3 = (ImageButton)findViewById(R.id.setButton3);
        set3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setinfo3(v);
                //hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
            }
        });



    }
}
