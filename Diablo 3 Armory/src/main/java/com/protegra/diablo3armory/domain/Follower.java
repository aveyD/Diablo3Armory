package com.protegra.diablo3armory.domain;

import java.util.List;

public class Follower {
    private String slug;
    private int level;
    private Item special;
    private Item mainHand;
    private Item rightFinger;
    private Item leftFinger;
    private Item neck;
    private FollowerStats stats;
    private List<Skill> skills;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Item getSpecial() {
        return special;
    }

    public void setSpecial(Item special) {
        this.special = special;
    }

    public Item getMainHand() {
        return mainHand;
    }

    public void setMainHand(Item mainHand) {
        this.mainHand = mainHand;
    }

    public Item getRightFinger() {
        return rightFinger;
    }

    public void setRightFinger(Item rightFinger) {
        this.rightFinger = rightFinger;
    }

    public Item getLeftFinger() {
        return leftFinger;
    }

    public void setLeftFinger(Item leftFinger) {
        this.leftFinger = leftFinger;
    }

    public Item getNeck() {
        return neck;
    }

    public void setNeck(Item neck) {
        this.neck = neck;
    }

    public FollowerStats getStats() {
        return stats;
    }

    public void setStats(FollowerStats stats) {
        this.stats = stats;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
