package com.protegra.diablo3armory.domain;

/**
 * Created by David on 01/05/2014.
 */
public class TimePlayed {
    private double barbarian = 0;
    private double crusader = 0;
    private double demonHunter = 0;
    private double monk;
    private double witchDoctor;
    private double wizard;

    public double getBarbarian() {
        return barbarian;
    }

    public void setBarbarian(double barbarian) {
        this.barbarian = barbarian;
    }

    public double getCrusader() {
        return crusader;
    }

    public void setCrusader(double crusader) {
        this.crusader = crusader;
    }

    public double getDemonHunter() {
        return demonHunter;
    }

    public void setDemonHunter(double demonHunter) {
        this.demonHunter = demonHunter;
    }

    public double getMonk() {
        return monk;
    }

    public void setMonk(double monk) {
        this.monk = monk;
    }

    public double getWitchDoctor() {
        return witchDoctor;
    }

    public void setWitchDoctor(double witchDoctor) {
        this.witchDoctor = witchDoctor;
    }

    public double getWizard() {
        return wizard;
    }

    public void setWizard(double wizard) {
        this.wizard = wizard;
    }
}
