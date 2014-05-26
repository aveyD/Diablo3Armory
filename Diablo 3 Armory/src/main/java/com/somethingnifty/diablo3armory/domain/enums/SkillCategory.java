package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum SkillCategory
{
    PRIMARY("primary", "Primary"),
    SECONDARY("secondary", "Secondary"),
    TACTICS("tactics", "Tactics"),
    RAGE("rage", "Rage"),
    DEFENSIVE("defensive", "Defensive"),
    MIGHT("might", "Might");

    public static final EnumSet<SkillCategory> ALL = EnumSet.allOf(SkillCategory.class);

    private String value;
    private String toString;

    private SkillCategory(String value, String toString){
        this.value = value;
        this.toString = toString;
    }

    public String getValue(){
        return value;
    }

    public String toString() { return toString; }
}
