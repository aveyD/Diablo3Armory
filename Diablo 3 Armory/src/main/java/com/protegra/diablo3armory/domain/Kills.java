package com.protegra.diablo3armory.domain;

/**
 * Created by David on 01/05/2014.
 */
public class Kills {
    private int monsterKills;
    private int eliteKills;
    private int hardcoreMonsterKills;

    public int getMonsterKills() {
        return monsterKills;
    }

    public void setMonsterKills(int monsterKills) {
        this.monsterKills = monsterKills;
    }

    public int getEliteKills() {
        return eliteKills;
    }

    public void setEliteKills(int eliteKills) {
        this.eliteKills = eliteKills;
    }

    public int getHardcoreMonsterKills() {
        return hardcoreMonsterKills;
    }

    public void setHardcoreMonsterKills(int hardcoreMonsterKills) {
        this.hardcoreMonsterKills = hardcoreMonsterKills;
    }
}
