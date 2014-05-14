package com.somethingnifty.diablo3armory.helpers;

import android.app.Application;
import android.content.Context;

public final class App extends Application {
    private static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        context = this;
    }

    public static Context context(){
        return context;
    }
}
