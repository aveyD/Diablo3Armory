package com.somethingnifty.diablo3armory.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HeroProgression implements Serializable{
    private List<Act> heroProgression;

    public HeroProgression(){
        heroProgression = new ArrayList<Act>();
    }

    public List<Act> getHeroProgression() { return heroProgression; }

    public void setHeroProgression(List<Act> heroProgression) {
        this.heroProgression = heroProgression;
    }

    public void addAct(Act act) {
        heroProgression.add(act);
    }
}
