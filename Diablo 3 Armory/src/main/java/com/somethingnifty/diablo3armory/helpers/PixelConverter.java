package com.somethingnifty.diablo3armory.helpers;

import android.content.Context;
import android.util.DisplayMetrics;

public class PixelConverter {

    private float density;

    public PixelConverter(DisplayMetrics displayMetrics){
        density = displayMetrics.density;
    }

    public int convertDiptoPix(int dip){
        return (int)(dip * density + 0.5f);
    }
}
