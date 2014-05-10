package com.protegra.diablo3armory.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CareerProfile implements Serializable{
    private Map<Long, ActiveHero> activeHeroes = new HashMap<Long, ActiveHero>();
    private Map<Long, FallenHero> fallenHeroes = new HashMap<Long, FallenHero>();

    private ActiveHero lastHeroPlayed;
    private Date lastUpdated;
    private Kills kills;
    private TimePlayed timePlayed;
    private Integer paragonLevel;
    private Integer paragonLevelHardcore;
    private String battleTag;
    private ActProgression progression;

    public Map<Long, ActiveHero> getActiveHeroes() {
        return activeHeroes;
    }

    public void setActiveHeroes(Map<Long, ActiveHero> activeHeroes) {
        this.activeHeroes = activeHeroes;
    }

    public ActiveHero getLastHeroPlayed() {
        return lastHeroPlayed;
    }

    public void setLastHeroPlayed(ActiveHero lastHeroPlayed) {
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

    public Map<Long, FallenHero> getFallenHeroes() {
        return fallenHeroes;
    }

    public void setFallenHeroes(Map<Long, FallenHero> fallenHeroes) {
        this.fallenHeroes = fallenHeroes;
    }

    public Integer getParagonLevel() {
        return paragonLevel;
    }

    public void setParagonLevel(Integer paragonLevel) {
        this.paragonLevel = paragonLevel;
    }

    public Integer getParagonLevelHardcore() {
        return paragonLevelHardcore;
    }

    public void setParagonLevelHardcore(Integer paragonLevelHardcore) {
        this.paragonLevelHardcore = paragonLevelHardcore;
    }

    public String getBattleTag() {
        return battleTag;
    }

    public void setBattleTag(String battleTag) {
        this.battleTag = battleTag;
    }

    public ActProgression getProgression() {
        return progression;
    }

    public void setProgression(ActProgression progression) {
        this.progression = progression;
    }
}
