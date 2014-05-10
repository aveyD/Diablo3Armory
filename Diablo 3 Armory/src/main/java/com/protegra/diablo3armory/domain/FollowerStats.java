package com.protegra.diablo3armory.domain;

import java.io.Serializable;

public class FollowerStats implements Serializable {
    private int goldFind;
    private int magicFind;
    private int experienceBonus;

    public int getGoldFind() {
        return goldFind;
    }

    public void setGoldFind(int goldFind) {
        this.goldFind = goldFind;
    }

    public int getMagicFind() {
        return magicFind;
    }

    public void setMagicFind(int magicFind) {
        this.magicFind = magicFind;
    }

    public int getExperienceBonus() {
        return experienceBonus;
    }

    public void setExperienceBonus(int experienceBonus) {
        this.experienceBonus = experienceBonus;
    }
}
