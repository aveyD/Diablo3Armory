package com.protegra.diablo3armory.domain;

import com.protegra.diablo3armory.domain.enums.ActType;

import java.util.HashMap;
import java.util.Map;

public class ActProgression {
    private Map<ActType, Boolean> careerProgression = new HashMap<ActType, Boolean>();

    public ActProgression(){
        for (ActType type : ActType.ALL){
            careerProgression.put(type, Boolean.FALSE);
        }
    }

    public Boolean getCareerProgression(ActType actType){
        return careerProgression.get(actType);
    }

    public void setCareerProgression(ActType actType, Boolean isCompleted){
        careerProgression.put(actType, isCompleted);
    }
}
