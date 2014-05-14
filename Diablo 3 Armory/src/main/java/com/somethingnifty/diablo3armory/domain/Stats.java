package com.somethingnifty.diablo3armory.domain;

import com.somethingnifty.diablo3armory.domain.enums.StatDoubleType;
import com.somethingnifty.diablo3armory.domain.enums.StatIntegerType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Stats implements Serializable {

    private Map<StatIntegerType, Integer> intStatsByStatType = new HashMap<StatIntegerType, Integer>();
    private Map<StatDoubleType, Double> doubleStatsByStatType = new HashMap<StatDoubleType, Double>();

    public Stats(){
        for (StatIntegerType statIntegerType : StatIntegerType.ALL){
            intStatsByStatType.put(statIntegerType, Integer.valueOf(0));
        }

        for (StatDoubleType statDoubleType : StatDoubleType.ALL){
            doubleStatsByStatType.put(statDoubleType, Double.valueOf(0));
        }
    }

    public Double getStat(StatDoubleType statDoubleType){
        return doubleStatsByStatType.get(statDoubleType);
    }

    public Integer getStat(StatIntegerType statIntegerType){
        return intStatsByStatType.get(statIntegerType);
    }

    public void setStat(StatIntegerType statIntegerType, Integer value){
        intStatsByStatType.put(statIntegerType, value);
    }

    public void setStat(StatDoubleType statDoubleType, Double value){
        doubleStatsByStatType.put(statDoubleType, value);
    }
}
