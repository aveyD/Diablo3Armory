package com.protegra.diablo3armory.domain;

import java.util.Date;

/**
 * Created by David on 28/04/2014.
 */
public class Hero {
    private int paragonLevel;
    private String name;
    private int id;
    private int level;
    private boolean hardcore;
    private int gender;
    private boolean dead;
    private String heroClass;
    private Date lastUpdated;

    private Item head;
    private Item torso;
    private Item feet;
    private Item hands;
    private Item shoulders;
    private Item legs;
    private Item bracers;
    private Item mainHand;
    private Item offHand;
    private Item waist;
    private Item rightFinger;
    private Item leftFinger;
    private Item neck;

    private Follower templar;
    private Follower scoundrel;
    private Follower enchantress;

    private Stats stats;
    private int eliteKills;
    private HeroProgression progression;

    public int getParagonLevel() {
        return paragonLevel;
    }

    public void setParagonLevel(int paragonLevel) {
        this.paragonLevel = paragonLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Item getHead() {
        return head;
    }

    public void setHead(Item head) {
        this.head = head;
    }

    public Item getTorso() {
        return torso;
    }

    public void setTorso(Item torso) {
        this.torso = torso;
    }

    public Item getFeet() {
        return feet;
    }

    public void setFeet(Item feet) {
        this.feet = feet;
    }

    public Item getHands() {
        return hands;
    }

    public void setHands(Item hands) {
        this.hands = hands;
    }

    public Item getShoulders() {
        return shoulders;
    }

    public void setShoulders(Item shoulders) {
        this.shoulders = shoulders;
    }

    public Item getLegs() {
        return legs;
    }

    public void setLegs(Item legs) {
        this.legs = legs;
    }

    public Item getBracers() {
        return bracers;
    }

    public void setBracers(Item bracers) {
        this.bracers = bracers;
    }

    public Item getMainHand() {
        return mainHand;
    }

    public void setMainHand(Item mainHand) {
        this.mainHand = mainHand;
    }

    public Item getOffHand() {
        return offHand;
    }

    public void setOffHand(Item offHand) {
        this.offHand = offHand;
    }

    public Item getWaist() {
        return waist;
    }

    public void setWaist(Item waist) {
        this.waist = waist;
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

    public HeroProgression getProgression() {
        return progression;
    }

    public void setProgression(HeroProgression progression) {
        this.progression = progression;
    }
}
