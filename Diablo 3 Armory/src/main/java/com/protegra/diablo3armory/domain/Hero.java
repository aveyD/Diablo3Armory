package com.protegra.diablo3armory.domain;

import com.protegra.diablo3armory.domain.enums.Gender;
import com.protegra.diablo3armory.domain.enums.HeroType;

import java.io.Serializable;

public abstract class Hero implements Serializable{
    private Long id;
    private String name;
    private int level;
    private boolean hardcore;
    private HeroType heroType;
    private Gender gender;
    private int eliteKills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isHardcore() {
        return hardcore;
    }

    public void setHardcore(boolean hardcore) {
        this.hardcore = hardcore;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public HeroType getHeroType() {
        return heroType;
    }

    public void setHeroType(HeroType heroType) {
        this.heroType = heroType;
    }

    public int getEliteKills() {
        return eliteKills;
    }

    public void setEliteKills(int eliteKills) {
        this.eliteKills = eliteKills;
    }
}
