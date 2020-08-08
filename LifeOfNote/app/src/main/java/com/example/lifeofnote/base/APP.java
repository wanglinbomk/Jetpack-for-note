package com.example.lifeofnote.base;

import android.app.Application;
import android.content.Context;

public class APP extends Application {

    public static Context App = null;

    @Override
    public void onCreate() {
        super.onCreate();
        App = getApplicationContext();
    }

    public static Context get() {
        return App;
    }
}
