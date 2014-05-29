package com.somethingnifty.diablo3armory.domain.enums;

import com.somethingnifty.diablo3armory.R;

import java.util.EnumSet;

public enum ColorType {
    WHITE("white", R.drawable.color_white),
    BROWN("brown", R.drawable.color_brown),
    BLUE("blue", R.drawable.color_blue),
    YELLOW("yellow", R.drawable.color_yellow),
    ORANGE("orange", R.drawable.color_orange),
    GREEN("green", R.drawable.color_green);

    public static final EnumSet<ColorType> ALL = EnumSet.allOf(ColorType.class);

    public String value;
    public int colorId;

    private ColorType(String value, int colorId){
        this.value = value;
        this.colorId = colorId;
    }

    public String getValue(){
        return value;
    }

    public int getColorId() {
        return colorId;
    }

    public static ColorType getColorType(String value){
        for (ColorType colorType : ALL){
            if (colorType.getValue().equals(value)){
                return colorType;
            }
        }

        return ColorType.WHITE;
    }
}
