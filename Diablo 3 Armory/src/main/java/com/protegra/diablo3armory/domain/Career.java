package com.protegra.diablo3armory.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Career {
    private Map<Long, Hero> heroes;
    private Hero lastHeroPlayed;
    private Date lastUpdated;
    private Kills kills;
    private TimePlayed timePlayed;
    private List<Hero> fallenHeroes;
    private Integer paragonLevel;
    private Integer paragonLevelHardcore;
    private String battleTag;
    private CareerProgression progression;

    public static boolean hasSavedCareer(String battleTag) {
        return false;
    }

    public static Career getSavedCareer(String battleTag) {
        return null;
    }

    public Map<Long, Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Map<Long, Hero> heroes) {
        this.heroes = heroes;
    }

    public Hero getLastHeroPlayed() {
        return lastHeroPlayed;
    }

    public void setLastHeroPlayed(Hero lastHeroPlayed) {
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

    public CareerProgression getProgression() {
        return progression;
    }

    public void setProgression(CareerProgression progression) {
        this.progression = progression;
    }
}
