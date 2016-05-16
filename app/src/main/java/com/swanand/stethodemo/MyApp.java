package com.swanand.stethodemo;

import android.app.Application;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;

import com.facebook.stetho.Stetho;

/**
 * Created by swanand on 5/16/2016.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

    }
}
