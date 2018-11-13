package com.example.abdur.probisapplication;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class ProbisApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
