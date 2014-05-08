package com.protegra.diablo3armory.domain;

import java.util.Date;

public class ActiveHero extends Hero {
    private int paragonLevel;
    private boolean dead;
    private Date lastUpdated;
    private FollowerMaster followerMaster;
    private HeroProgression progression;
    private ItemLoadoutActiveHero itemLoadoutActiveHero;
    private Stats stats;

    public int getParagonLevel() {
        return paragonLevel;
    }

    public void setParagonLevel(int paragonLevel) {
        this.paragonLevel = paragonLevel;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public FollowerMaster getFollowerMaster() {
        return followerMaster;
    }

    public void setFollowerMaster(FollowerMaster followerMaster) {
        this.followerMaster = followerMaster;
    }

    public HeroProgression getProgression() {
        return progression;
    }

    public void setProgression(HeroProgression progression) {
        this.progression = progression;
    }

    public ItemLoadoutActiveHero getItemLoadoutActiveHero() {
        return itemLoadoutActiveHero;
    }

    public void setItemLoadoutActiveHero(ItemLoadoutActiveHero itemLoadoutActiveHero) {
        this.itemLoadoutActiveHero = itemLoadoutActiveHero;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
