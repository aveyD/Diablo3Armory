package com.protegra.diablo3armory.domain.enums;

import java.util.EnumSet;


import static com.protegra.diablo3armory.domain.enums.HeroType.*;
import static com.protegra.diablo3armory.domain.enums.Gender.*;

public enum HeroGenderType {
    BARBARIAN_MALE(BARBARIAN, MALE, "barb_male"),
    BARBARIAN_FEMALE(BARBARIAN, FEMALE, "barb_female"),
    CRUSADER_MALE(CRUSADER, MALE, "crusader_male"),
    CRUSADER_FEMALE(CRUSADER, FEMALE, "crusader_female"),
    DEMON_HUNTER_MALE(DEMON_HUNTER, MALE, "demon_hunter_male"),
    DEMON_HUNTER_FEMALE(DEMON_HUNTER, FEMALE, "demon_hunter_female"),
    MONK_MALE(MONK, MALE, "monk_male"),
    MONK_FEMALE(MONK, FEMALE, "monk_female"),
    WITCH_DOCTOR_MALE(WITCH_DOCTOR, MALE, "witch_doctor_male"),
    WITCH_DOCTOR_FEMALE(WITCH_DOCTOR, FEMALE, "witch_doctor_female"),
    WIZARD_MALE(WIZARD, MALE, "wizard_male"),
    WIZARD_FEMALE(WIZARD, FEMALE, "wizard_female");

    public static final EnumSet<HeroGenderType> ALL = EnumSet.allOf(HeroGenderType.class);

    private HeroType heroType;
    private Gender gender;
    private String value;

    private HeroGenderType(HeroType heroType, Gender gender, String value){
        this.heroType = heroType;
        this.gender = gender;
        this.value = value;
    }

    public HeroType getHeroType(){
        return heroType;
    }

    public Gender getGender(){
        return gender;
    }

    public String getValue(){
        return value;
    }

    public static HeroGenderType getHeroGenderType(HeroType heroType, Gender gender){

        for (HeroGenderType heroGenderType : ALL){
            if (heroGenderType.getHeroType().equals(heroType) && heroGenderType.getGender().equals(gender)){
                return heroGenderType;
            }
        }

        return null;
    }

    public static String getValue(HeroType heroType, Gender gender){

        for (HeroGenderType heroGenderType : ALL){
            if (heroGenderType.getHeroType().equals(heroType) && heroGenderType.getGender().equals(gender)){
                return heroGenderType.getValue();
            }
        }

        return null;
    }
}
