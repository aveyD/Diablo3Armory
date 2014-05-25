package com.somethingnifty.diablo3armory.domain;

import com.somethingnifty.diablo3armory.domain.enums.ActType;
import com.somethingnifty.diablo3armory.domain.enums.QuestType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Act implements Serializable {
    private ActType actType;
    private boolean completed;
    private Map<QuestType, Boolean> questCompletionByQuestType = new HashMap<QuestType, Boolean>();

    public ActType getActType() {
        return actType;
    }

    public void setActType(ActType actType) {
        this.actType = actType;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Map<QuestType, Boolean> getQuestCompletionByQuestType() {
        return questCompletionByQuestType;
    }

    public void setQuestCompletionByQuestType(Map<QuestType, Boolean> questCompletionByQuestType) {
        this.questCompletionByQuestType = questCompletionByQuestType;
    }
}
