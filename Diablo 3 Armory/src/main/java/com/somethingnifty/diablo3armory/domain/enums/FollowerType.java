package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum FollowerType {

    TEMPLAR("templar", "Templar"),
    SCOUNDREL("scoundrel", "Scoundrel"),
    ENCHANTRESS("enchantress", "Enchantress");

    public static final EnumSet<FollowerType> ALL = EnumSet.allOf(FollowerType.class);

    private String value;
    private String toString;

    private FollowerType(String value, String toString){
        this.value = value;
        this.toString = toString;
    }

    public String getValue(){
        return value;
    }

    public String toString() { return toString; }
}
