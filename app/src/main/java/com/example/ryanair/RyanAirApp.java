package com.example.ryanair;

import android.app.Application;
import android.content.Context;

public class RyanAirApp extends Application {
    private static final String TAG = RyanAirApp.class.getSimpleName();
    private AppExecutors       appExecutors;
    private static Context     context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getInstance(){
        return RyanAirApp.context;
    }

}
