package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum FollowerType {

    ENCHANTRESS("enchantress"),
    TEMPLAR("templar"),
    SCOUNDREL("scoundrel");

    public static final EnumSet<FollowerType> ALL = EnumSet.allOf(FollowerType.class);

    private String value;

    private FollowerType(String value){
        this.value = value;
    }

    public String getvalue(){
        return value;
    }
}
