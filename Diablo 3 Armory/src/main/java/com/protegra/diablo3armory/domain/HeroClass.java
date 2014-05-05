package com.protegra.diablo3armory.domain;

import java.util.Arrays;
import java.util.List;

public enum HeroClass {

    BARBARIAN("barbarian"),
    CRUSADER("crusader"),
    DEMON_HUNTER("demon-hunter"),
    MONK("monk"),
    WITCH_DOCTOR("witch-doctor"),
    WIZARD("wizard");

    private String value;

    private HeroClass(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static HeroClass getHeroClass(String stringHeroClass){
        List<HeroClass> heroClasses = Arrays.asList(HeroClass.values());

        for (HeroClass heroClass : heroClasses){
            if (heroClass.getValue().equals(stringHeroClass)) {
                return heroClass;
            }
        }

        return null;
    }
}
