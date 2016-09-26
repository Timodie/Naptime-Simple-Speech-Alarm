package com.example.tim.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Button;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;



/*****************************************************************************
 * Created by Tim on 1/2/2016.
 * Too many lines in Naptime
 * Check version here instead
 * I write the server's response to a SharedPreference
 * I am certain onStartCommand is called but I'll keep onCreate here for the future
 ******************************************************************************/
public class CheckVersion extends Service {
    Boolean update = false;
    String curVersion;
    Button check;
    ByteArrayBuffer byteArrayBuffer;
    public String latest = "";
    public String newVersion;

    //Constructors


    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

        if(!checkUpdate.isAlive()||!checkUpdate.isDaemon()){
            checkUpdate.start();
            Toast.makeText(this, "Checking updates", Toast.LENGTH_SHORT).show();

        }
        return START_STICKY;
    }
//Default constructor required by Manifest when this was just a class
 // And not a service
    public CheckVersion() {

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
    }

    public CheckVersion(String version) {

        super();
        this.curVersion = version;
        checkUpdate.start();
    }


    public Thread checkUpdate = new Thread() {
        public void run() {
            try {
                URL updateURL = new URL("http://timodie.github.io/version.txt");
                URLConnection conn = updateURL.openConnection();

                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                //Since bytearraybuffer is deprecated in api >22
                //Im using a local class instead
                byteArrayBuffer = new ByteArrayBuffer(50);
                //byte [] barray = new byte[(int) 50];

                int current = 0;
                while ((current = bis.read()) != -1) {
                    byteArrayBuffer.append((byte) current);

                }

                /* Convert the Bytes read to a String. */

                final String s = new String(byteArrayBuffer.toByteArray());
                // Toast.makeText(this,"Version is"+s,Toast.LENGTH_SHORT).show();
                newVersion = s;
                latest = s;
                SharedPreferences version = getSharedPreferences("versionNo", Context.MODE_PRIVATE);
                SharedPreferences.Editor writeVersion = version.edit();
                writeVersion.putString("newVersion",s);
                writeVersion.commit();

                if (!newVersion.equals(curVersion)) {
                    update = true;
                } else {
                    update = false;
                }


            } catch (Exception e) {

            }

        }
    };

    public boolean update() {
        return update;
    }

    public String getnewVersion() {
        return latest;
    }



}

