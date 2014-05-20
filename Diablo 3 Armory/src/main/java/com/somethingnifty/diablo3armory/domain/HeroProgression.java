package com.somethingnifty.diablo3armory.domain;

import com.somethingnifty.diablo3armory.domain.enums.ActType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
