package com.protegra.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum ActType {
    ACT1("act1"),
    ACT2("act2"),
    ACT3("act3"),
    ACT4("act4"),
    ACT5("act5");

    public static final EnumSet<ActType> ALL = EnumSet.allOf(ActType.class);

    private String value;

    private ActType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
