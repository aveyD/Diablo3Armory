package com.somethingnifty.diablo3armory.domain.enums;

import java.util.EnumSet;

public enum QuestType {
    ACT_ONE_QUEST_ONE(ActType.ACT1, 1, "the-fallen-star", "The Fallen Star"),
    ACT_ONE_QUEST_TWO(ActType.ACT1, 2, "the-legacy-of-cain", "The Legacy of Cain"),
    ACT_ONE_QUEST_THREE(ActType.ACT1, 3, "a-shattered-crown", "A Shattered Crown"),
    ACT_ONE_QUEST_FOUR(ActType.ACT1, 4, "reign-of-the-black-king", "Reign of the Black King"),
    ACT_ONE_QUEST_FIVE(ActType.ACT1, 5, "sword-of-the-stranger", "Sword of the Stranger"),
    ACT_ONE_QUEST_SIX(ActType.ACT1, 6, "the-broken-blade", "The Broken Blade"),
    ACT_ONE_QUEST_SEVEN(ActType.ACT1, 7, "the-doom-in-wortham", "The Doom in Wortham"),
    ACT_ONE_QUEST_EIGHT(ActType.ACT1, 8, "trailing-the-coven", "Trailing the Coven"),
    ACT_ONE_QUEST_NINE(ActType.ACT1, 9, "the-imprisoned-angel", "The Imprisoned Angel"),
    ACT_ONE_QUEST_TEN(ActType.ACT1, 10, "return-to-new-tristram", "Return to New Tristam"),

    ACT_TWO_QUEST_ONE(ActType.ACT2, 1, "shadows-in-the-desert", "Shadows in the Desert"),
    ACT_TWO_QUEST_TWO(ActType.ACT2, 2, "the-road-to-alcarnus", "The Road to Alcarnus"),
    ACT_TWO_QUEST_THREE(ActType.ACT2, 3, "city-of-blood", "City of Blood"),
    ACT_TWO_QUEST_FOUR(ActType.ACT2, 4, "a-royal-audience", "A Royal Audience"),
    ACT_TWO_QUEST_FIVE(ActType.ACT2, 5, "unexpected-allies", "Unexpected Allies"),
    ACT_TWO_QUEST_SIX(ActType.ACT2, 6, "betrayer-of-the-horadrim", "Betrayer of the Horadrim"),
    ACT_TWO_QUEST_SEVEN(ActType.ACT2, 7, "blood-and-sand", "Blood and Sand"),
    ACT_TWO_QUEST_EIGHT(ActType.ACT2, 8, "the-black-soulstone", "The Black Soulstone"),
    ACT_TWO_QUEST_NINE(ActType.ACT2, 9, "the-scouring-of-caldeum", "The Scouring of Caldeum"),
    ACT_TWO_QUEST_TEN(ActType.ACT2, 10, "lord-of-lies", "Lord of Lies"),

    ACT_THREE_QUEST_ONE(ActType.ACT3, 1, "the-siege-of-bastions-keep", "The Siege of Bastion's Keep"),
    ACT_THREE_QUEST_TWO(ActType.ACT3, 2, "turning-the-tide", "Turning the Tide"),
    ACT_THREE_QUEST_THREE(ActType.ACT3, 3, "the-breached-keep", "The Breached Keep"),
    ACT_THREE_QUEST_FOUR(ActType.ACT3, 4, "tremors-in-the-stone", "Tremors in the Stone"),
    ACT_THREE_QUEST_FIVE(ActType.ACT3, 5, "machines-of-war", "Machines of War"),
    ACT_THREE_QUEST_SIX(ActType.ACT3, 6, "siegebreaker", "Siegebreaker"),
    ACT_THREE_QUEST_SEVEN(ActType.ACT3, 7, "heart-of-sin", "Heart of Sin"),

    ACT_FOUR_QUEST_ONE(ActType.ACT4, 1, "fall-of-the-high-heavens", "Fall of the High Heavens"),
    ACT_FOUR_QUEST_TWO(ActType.ACT4, 2, "the-light-of-hope", "The Light of Hope"),
    ACT_FOUR_QUEST_THREE(ActType.ACT4, 3, "beneath-the-spire", "Beneath the Spire"),
    ACT_FOUR_QUEST_FOUR(ActType.ACT4, 4, "prime-evil", "Prime Evil"),

    ACT_FIVE_QUEST_ONE(ActType.ACT4, 1, "the-fall-of-westmarch", "The Fall of Westmarch"),
    ACT_FIVE_QUEST_TWO(ActType.ACT4, 2, "souls-of-the-dead", "Souls of the Dead"),
    ACT_FIVE_QUEST_THREE(ActType.ACT4, 3, "the-harbinger", "The Harbinger"),
    ACT_FIVE_QUEST_FOUR(ActType.ACT4, 4, "the-witch", "The Witch"),
    ACT_FIVE_QUEST_FIVE(ActType.ACT4, 5, "the-pandemonium-gate", "The Pandemonium Gate"),
    ACT_FIVE_QUEST_SIX(ActType.ACT4, 6, "the-battlefields-of-eternity", "The Battlefields of Eternity"),
    ACT_FIVE_QUEST_SEVEN(ActType.ACT4, 7, "breaching-the-fortress", "Breaching the Fortress"),
    ACT_FIVE_QUEST_EIGHT(ActType.ACT4, 8, "angel-of-death", "Angel of Death");

    private ActType actType;
    private Integer questNumber;
    private String slug;
    private String questName;

    public static EnumSet<QuestType> ALL = EnumSet.allOf(QuestType.class);
    public static EnumSet<QuestType> ALL_ACT_ONE_QUESTS = EnumSet.range(ACT_ONE_QUEST_ONE, ACT_ONE_QUEST_TEN);
    public static EnumSet<QuestType> ALL_ACT_TWO_QUESTS = EnumSet.range(ACT_TWO_QUEST_ONE, ACT_TWO_QUEST_TEN);
    public static EnumSet<QuestType> ALL_ACT_THREE_QUESTS = EnumSet.range(ACT_THREE_QUEST_ONE, ACT_THREE_QUEST_SEVEN);
    public static EnumSet<QuestType> ALL_ACT_FOUR_QUESTS = EnumSet.range(ACT_FOUR_QUEST_ONE, ACT_FOUR_QUEST_FOUR);
    public static EnumSet<QuestType> ALL_ACT_FIVE_QUESTS = EnumSet.range(ACT_FIVE_QUEST_ONE, ACT_FIVE_QUEST_EIGHT);

    private QuestType(ActType actType, Integer questNumber, String slug, String questName){
        this.actType = actType;
        this.questNumber = questNumber;
        this.slug = slug;
        this.questName = questName;
    }

    public QuestType getQuest(Integer questNumber){
        for (QuestType quest : ALL){
            if (quest.questNumber.equals(questNumber)){
                return quest;
            }
        }

        return null;
    }

    public QuestType getQuest(String slug){
        for (QuestType quest : ALL){
            if (quest.slug.equals(slug)){
                return quest;
            }
        }

        return null;
    }

    public String getSlug(){
        return slug;
    }
}
