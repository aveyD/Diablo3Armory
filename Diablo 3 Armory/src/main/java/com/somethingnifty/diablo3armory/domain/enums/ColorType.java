package com.somethingnifty.diablo3armory.domain.enums;

import com.somethingnifty.diablo3armory.R;

import java.util.EnumSet;

public enum ColorType {
    WHITE("white", R.color.white),
    BROWN("brown", R.color.Brown),
    BLUE("blue", R.color.blue),
    YELLOW("yellow", R.color.yellow),
    ORANGE("orange", R.color.Orange),
    GREEN("green", R.color.green);

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
