package com.protegra.diablo3armory.domain;

import java.util.List;

public class CraftedBy {
    private String id;
    private String slug;
    private List<Reagent> reagents;
    private long cost = 0;
    private Item itemProduced;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<Reagent> getReagents() {
        return reagents;
    }

    public void setReagents(List<Reagent> reagents) {
        this.reagents = reagents;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Item getItemProduced() {
        return itemProduced;
    }

    public void setItemProduced(Item itemProduced) {
        this.itemProduced = itemProduced;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
