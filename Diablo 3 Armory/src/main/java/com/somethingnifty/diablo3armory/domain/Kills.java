package com.somethingnifty.diablo3armory.domain;

import com.somethingnifty.diablo3armory.domain.enums.KillType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Kills implements Serializable {
    private Map<KillType, Integer> killsByKillType = new HashMap<KillType, Integer>();

    public Kills(){
        for (KillType killType : KillType.ALL){
            killsByKillType.put(killType, Integer.valueOf(0));
        }
    }

    public Integer getKills(KillType killType){
        return killsByKillType.get(killType);
    }

    public void setKills(KillType killType, Integer kills){
        killsByKillType.put(killType, kills);
    }
}
