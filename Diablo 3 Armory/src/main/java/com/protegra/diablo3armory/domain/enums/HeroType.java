package com.protegra.diablo3armory.domain.enums;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public enum HeroType {

    BARBARIAN("barbarian"),
    CRUSADER("crusader"),
    DEMON_HUNTER("demon-hunter"),
    MONK("monk"),
    WITCH_DOCTOR("witch-doctor"),
    WIZARD("wizard");

    public static final EnumSet<HeroType> ALL = EnumSet.allOf(HeroType.class);

    private String value;

    private HeroType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static HeroType getHeroClass(String stringHeroClass){
        List<HeroType> heroTypes = Arrays.asList(HeroType.values());

        for (HeroType heroType : heroTypes){
            if (heroType.getValue().equals(stringHeroClass)) {
                return heroType;
            }
        }

        return null;
    }
}
