package com.protegra.diablo3armory.domain;

public class FollowerMaster {
    private Follower templar;
    private Follower scoundrel;
    private Follower enchantress;

    public Follower getTemplar() {
        return templar;
    }

    public void setTemplar(Follower templar) {
        this.templar = templar;
    }

    public Follower getScoundrel() {
        return scoundrel;
    }

    public void setScoundrel(Follower scoundrel) {
        this.scoundrel = scoundrel;
    }

    public Follower getEnchantress() {
        return enchantress;
    }

    public void setEnchantress(Follower enchantress) {
        this.enchantress = enchantress;
    }
}
