package com.protegra.diablo3armory.activity.handlers;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;

public abstract class EventHandler {
    protected Activity activity;

    protected EventHandler(Activity activity){
        this.activity = activity;
    }

    protected Resources getResources() {
        return activity.getResources();
    }

    protected View findViewById(int id) {
        return activity.findViewById(id);
    }
}
