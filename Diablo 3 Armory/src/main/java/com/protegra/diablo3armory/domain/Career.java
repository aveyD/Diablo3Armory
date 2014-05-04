package com.protegra.diablo3armory.domain;

import java.util.Date;
import java.util.List;

/**
 * Created by David on 28/04/2014.
 */
public class Career {
    private List<Hero> heroes;
    private Date lastHeroPlayed;
    private Date lastUpdated;
    private Kills kills;
    private TimePlayed timePlayed;
    private List<Hero> fallenHeroes;
    private int paragonLevel;
    private int paragonLevelHardcore;
    private String battleTag;
    private CareerProgression progression;

    public static boolean hasSavedCareer(String battleTag) {
        return false;
    }

    public static Career getSavedCareer(String battleTag) {
        return null;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public Date getLastHeroPlayed() {
        return lastHeroPlayed;
    }

    public void setLastHeroPlayed(Date lastHeroPlayed) {
        this.lastHeroPlayed = lastHeroPlayed;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Kills getKills() {
        return kills;
    }

    public void setKills(Kills kills) {
        this.kills = kills;
    }

    public TimePlayed getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(TimePlayed timePlayed) {
        this.timePlayed = timePlayed;
    }

    public List<Hero> getFallenHeroes() {
        return fallenHeroes;
    }

    public void setFallenHeroes(List<Hero> fallenHeroes) {
        this.fallenHeroes = fallenHeroes;
    }

    public int getParagonLevel() {
        return paragonLevel;
    }

    public void setParagonLevel(int paragonLevel) {
        this.paragonLevel = paragonLevel;
    }

    public int getParagonLevelHardcore() {
        return paragonLevelHardcore;
    }

    public void setParagonLevelHardcore(int paragonLevelHardcore) {
        this.paragonLevelHardcore = paragonLevelHardcore;
    }

    public String getBattleTag() {
        return battleTag;
    }

    public void setBattleTag(String battleTag) {
        this.battleTag = battleTag;
    }

    public CareerProgression getProgression() {
        return progression;
    }

    public void setProgression(CareerProgression progression) {
        this.progression = progression;
    }
}
