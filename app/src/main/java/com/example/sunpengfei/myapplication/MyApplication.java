package com.example.sunpengfei.myapplication;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("aaaa", "aaaaa");
        Fresco.initialize(getApplicationContext());
        MyDB.getInstance(getApplicationContext());
    }

}