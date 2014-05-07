package com.protegra.diablo3armory.domain;

public abstract class Hero {
    private Long id;
    private String name;
    private int level;
    private boolean hardcore;
    private ItemLoadout itemLoadout;
    private Stats stats;
    private HeroClass heroClass;
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

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getEliteKills() {
        return eliteKills;
    }

    public void setEliteKills(int eliteKills) {
        this.eliteKills = eliteKills;
    }

    public ItemLoadout getItemLoadout() {
        return itemLoadout;
    }

    public void setItemLoadout(ItemLoadout itemLoadout) {
        this.itemLoadout = itemLoadout;
    }
}
