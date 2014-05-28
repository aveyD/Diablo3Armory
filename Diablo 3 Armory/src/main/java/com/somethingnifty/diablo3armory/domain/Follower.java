package com.somethingnifty.diablo3armory.domain;

import java.io.Serializable;
import java.util.List;

public class Follower implements Serializable {
    private String slug;
    private int level;
    private ItemLoadout itemLoadoutFollower;
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

    public ItemLoadout getItemLoadout() {
        return itemLoadoutFollower;
    }

    public void setItemLoadout(ItemLoadout itemLoadoutFollower) {
        this.itemLoadoutFollower = itemLoadoutFollower;
    }
}
