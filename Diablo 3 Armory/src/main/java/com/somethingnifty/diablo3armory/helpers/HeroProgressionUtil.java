package com.somethingnifty.diablo3armory.helpers;

import com.somethingnifty.diablo3armory.domain.Act;
import com.somethingnifty.diablo3armory.domain.HeroProgression;
import com.somethingnifty.diablo3armory.domain.enums.ActType;
import com.somethingnifty.diablo3armory.domain.enums.QuestType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public final class HeroProgressionUtil {
    private HeroProgressionUtil(){

    }

    public static HeroProgression getHeroProgression(JSONObject progressionJson) throws JSONException {
        HeroProgression actProgression = new HeroProgression();

        for (ActType actType : ActType.ALL) {
            JSONObject json = progressionJson.getJSONObject(actType.getValue());

            Act act = new Act();

            act.setActType(actType);
            act.setCompleted(json.getBoolean("completed"));
            act.setQuestCompletionByQuestType(getCompletedQuests(actType, json.getJSONArray("completedQuests")));

            actProgression.addAct(act);
        }

        return actProgression;
    }

    private static Map<QuestType, Boolean> getCompletedQuests(ActType actType, JSONArray completedQuestsJson) throws JSONException {
        Map<QuestType, Boolean> questsCompletedByQuestType = new HashMap<QuestType, Boolean>();
        EnumSet<QuestType> quests = getQuestsToProcess(actType);

        for (QuestType questType : quests){
            Boolean isCompleted = isQuestCompleted(completedQuestsJson, questType);
            questsCompletedByQuestType.put(questType, isCompleted);
        }

        return questsCompletedByQuestType;
    }

    private static Boolean isQuestCompleted(JSONArray completedQuestsJson, QuestType questType) throws JSONException {
        Boolean isCompleted = Boolean.FALSE;

        for (int i = 0; i < completedQuestsJson.length(); i++) {
            JSONObject json = completedQuestsJson.getJSONObject(i);

            if (questType.getSlug().equals(json.getString("slug"))){
                isCompleted = Boolean.TRUE;
                break;
            }
        }
        return isCompleted;
    }

    private static EnumSet<QuestType> getQuestsToProcess(ActType actType) {

        if (ActType.ACT1.equals(actType)){
            return QuestType.ALL_ACT_ONE_QUESTS;
        }
        else if (ActType.ACT2.equals(actType)){
            return QuestType.ALL_ACT_TWO_QUESTS;
        }
        else if (ActType.ACT3.equals(actType)){
            return QuestType.ALL_ACT_THREE_QUESTS;
        }
        else if (ActType.ACT4.equals(actType)){
            return QuestType.ALL_ACT_FOUR_QUESTS;
        }
        else if (ActType.ACT5.equals(actType)){
            return QuestType.ALL_ACT_FIVE_QUESTS;
        }

        return null;
    }
}
