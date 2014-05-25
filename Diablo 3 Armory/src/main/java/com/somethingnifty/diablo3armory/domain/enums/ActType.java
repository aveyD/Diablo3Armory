package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum ActType {
    ACT1("act1", "Act 1"),
    ACT2("act2", "Act 2"),
    ACT3("act3", "Act 3"),
    ACT4("act4", "Act 4"),
    ACT5("act5", "Act 5");

    public static final EnumSet<ActType> ALL = EnumSet.allOf(ActType.class);

    private String value;
    private String toString;

    private ActType(String value, String toString){
        this.value = value;
        this.toString = toString;
    }

    public String getValue(){
        return value;
    }

    public String getToString() {
        return toString;
    }
}
