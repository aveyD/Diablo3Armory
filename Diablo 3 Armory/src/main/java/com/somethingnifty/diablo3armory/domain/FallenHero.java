package com.somethingnifty.diablo3armory.domain;

public class FallenHero extends Hero{
    private ItemLoadoutFallenHero itemLoadoutFallenHero;
    private Death death;

    public Death getDeath() {
        return death;
    }

    public void setDeath(Death death) {
        this.death = death;
    }

    public ItemLoadoutFallenHero getItemLoadoutFallenHero() {
        return itemLoadoutFallenHero;
    }

    public void setItemLoadoutFallenHero(ItemLoadoutFallenHero itemLoadoutFallenHero) {
        this.itemLoadoutFallenHero = itemLoadoutFallenHero;
    }
}
