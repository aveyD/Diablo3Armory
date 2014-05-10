package com.protegra.diablo3armory.domain;

import com.protegra.diablo3armory.domain.enums.HeroType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TimePlayed implements Serializable{
    private Map<HeroType, Double> timePlayedByHero = new HashMap<HeroType, Double>();

    public TimePlayed(){
        for (HeroType heroType : HeroType.ALL){
            timePlayedByHero.put(heroType, Double.valueOf(0));
        }
    }

    public Double getTimePlayed(HeroType heroType){
        return timePlayedByHero.get(heroType);
    }

    public void setTimePlayed(HeroType heroType, Double timePlayed){
        timePlayedByHero.put(heroType, timePlayed);
    }
}
