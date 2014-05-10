package com.protegra.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum KillType {

    MONSTER("monsters"),
    ELITE("elites"),
    HARDCORE("hardcoreMonsters");

    public static final EnumSet<KillType> ALL = EnumSet.allOf(KillType.class);

    private String value;

    private KillType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
