package com.somethingnifty.diablo3armory.helpers;

import com.somethingnifty.diablo3armory.domain.Act;
import com.somethingnifty.diablo3armory.domain.ActProgression;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.ActiveSkill;
import com.somethingnifty.diablo3armory.domain.CraftedBy;
import com.somethingnifty.diablo3armory.domain.Follower;
import com.somethingnifty.diablo3armory.domain.FollowerMaster;
import com.somethingnifty.diablo3armory.domain.FollowerStats;
import com.somethingnifty.diablo3armory.domain.HeroProgression;
import com.somethingnifty.diablo3armory.domain.ItemLoadoutActiveHero;
import com.somethingnifty.diablo3armory.domain.ItemWearableActiveHero;
import com.somethingnifty.diablo3armory.domain.PassiveSkill;
import com.somethingnifty.diablo3armory.domain.Quest;
import com.somethingnifty.diablo3armory.domain.RandomAffix;
import com.somethingnifty.diablo3armory.domain.Skill;
import com.somethingnifty.diablo3armory.domain.Stats;
import com.somethingnifty.diablo3armory.domain.enums.ActType;
import com.somethingnifty.diablo3armory.domain.enums.FollowerType;
import com.somethingnifty.diablo3armory.domain.enums.Gender;
import com.somethingnifty.diablo3armory.domain.enums.HeroType;
import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;
import com.somethingnifty.diablo3armory.domain.enums.StatDoubleType;
import com.somethingnifty.diablo3armory.domain.enums.StatIntegerType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HeroCreator
{
    public ActiveHero createHero(JSONObject heroJson) throws JSONException
    {
        ActiveHero hero = new ActiveHero();

        Long id = heroJson.getLong("id");
        hero.setId(id);
        hero.setName(heroJson.getString("name"));
        hero.setHeroType(HeroType.getHeroClass(heroJson.getString("class")));
        hero.setGender(Gender.getGender(heroJson.getInt("gender")));
        hero.setLevel(heroJson.getInt("level"));
        hero.setParagonLevel(heroJson.getInt("paragonLevel"));
        hero.setHardcore(heroJson.getBoolean("hardcore"));

        List<ActiveSkill> activeSkills = getActiveSkills(heroJson.getJSONObject("skills"));
        hero.setActiveSkills(activeSkills);

        List<PassiveSkill> passiveSkills = getPassiveSkills(heroJson.getJSONObject("skills"));
        hero.setPassiveSkills(passiveSkills);

        ItemLoadoutActiveHero items = getItems(heroJson.getJSONObject("items"));
        hero.setItemLoadoutActiveHero(items);

        FollowerMaster followerMaster = getFollowerMaster(heroJson.getJSONObject("followers"));
        hero.setFollowerMaster(followerMaster);

        hero.setStats(getHeroStats(heroJson.getJSONObject("stats")));

        hero.setEliteKills(heroJson.getJSONObject("kills").getInt("elites"));

        hero.setProgression(getHeroProgression(heroJson.getJSONObject("progression")));

        hero.setDead(heroJson.getBoolean("dead"));
        hero.setLastUpdated(DateUtil.getDate(heroJson.getLong("last-updated")));

        return hero;
    }

    private List<ActiveSkill> getActiveSkills(JSONObject skillsJson) throws JSONException {
        List<ActiveSkill> skills = new ArrayList<ActiveSkill>();
        JSONArray activeJson = skillsJson.getJSONArray("active");

        for (int i = 0; i < activeJson.length(); i++) {
            JSONObject skillJson = activeJson.getJSONObject(i);

            Skill skill = new Skill();
            skill.setSlug(skillJson.getString("slug"));
        }

        return skills;
    }

    private List<PassiveSkill> getPassiveSkills(JSONObject skillsJson) throws JSONException {
        List<PassiveSkill> passiveSkills = new ArrayList<PassiveSkill>();
        JSONArray passiveJson = skillsJson.getJSONArray("passive");

        for (int i = 0; i < passiveJson.length(); i++) {
            JSONObject json = passiveJson.getJSONObject(i);

            PassiveSkill skill = new PassiveSkill();
            getCommonSkill(skill, json);
            skill.setFlavor(json.getString("flavor"));

            passiveSkills.add(skill);
        }

        return passiveSkills;
    }

    private void getCommonSkill(Skill skill, JSONObject json) throws JSONException {
        skill.setSlug(json.getString("slug"));
        skill.setName(json.getString("name"));
        skill.setIcon(json.getString("icon"));
        skill.setLevel(json.getInt("level"));
        skill.setTooltipUrl(json.getString("tooltipUrl"));
        skill.setDescription(json.getString("description"));
        skill.setSkillCalcId(json.getString("skillCalcId"));
    }

    private ItemLoadoutActiveHero getItems(JSONObject items) throws JSONException {
        ItemLoadoutActiveHero itemLoadout = new ItemLoadoutActiveHero();

        for (ItemWearableType itemWearableType : ItemWearableType.ALL) {
            JSONObject itemJson = items.getJSONObject(itemWearableType.getValue());

            ItemWearableActiveHero item = new ItemWearableActiveHero();
            item.setId(itemJson.getString("id"));
            item.setName(itemJson.getString("name"));
            item.setIcon(itemJson.getString("icon"));
            item.setDisplayColor(itemJson.getString("displayColor"));
            item.setTooltipParams(itemJson.getString("tooltipParams"));
            item.setRandomAffix(getRandomAffix(itemJson.getJSONArray("randomAffixes")));

            if (!itemJson.isNull("recipe")) {
                // TODO: finish recipe parsing
            }

            item.setCraftedByList(getCraftedBy(itemJson.getJSONArray("craftedBy")));

            itemLoadout.setItemActiveHero(itemWearableType, item);
        }

        return itemLoadout;
    }

    private RandomAffix getRandomAffix(JSONArray randomAffixes) {
        // TODO: finish random affixes parsing
        return null;
    }

    private List<CraftedBy> getCraftedBy(JSONArray craftedBy) {
        // TODO: finish crafted by parsing
        return null;
    }

    private FollowerMaster getFollowerMaster(JSONObject followerJson) throws JSONException {
        FollowerMaster followerMaster = new FollowerMaster();

        for (FollowerType followerType : FollowerType.ALL) {
            Follower follower = new Follower();
            follower.setSlug(followerJson.getString("slug"));
            follower.setLevel(followerJson.getInt("level"));

            // TODO: finish items parsing


            follower.setStats(getFollowerStats(followerJson.getJSONObject("stats")));
            follower.setSkills(getFollowerSkills(followerJson.getJSONArray("skills")));

            followerMaster.setFollower(followerType, follower);
        }
        return followerMaster;
    }

    private FollowerStats getFollowerStats(JSONObject stats) throws JSONException {
        FollowerStats followerStats = new FollowerStats();

        followerStats.setGoldFind(stats.getInt("goldFind"));
        followerStats.setMagicFind(stats.getInt("magicFind"));
        followerStats.setExperienceBonus(stats.getInt("experienceBonus"));

        return followerStats;
    }

    private List<Skill> getFollowerSkills(JSONArray skillsJson) throws JSONException {
        List<Skill> followerSkills = new ArrayList<Skill>();

        for (int i = 0; i < skillsJson.length(); i++)
        {
            JSONObject json = skillsJson.getJSONObject(i);

            Skill skill = new Skill();
            getCommonSkill(skill, json);

            followerSkills.add(skill);
        }
        return followerSkills;
    }

    private Stats getHeroStats(JSONObject statsJson) throws JSONException {
        Stats stats = new Stats();

        stats.setStat(StatIntegerType.LIFE, statsJson.getInt("life"));
        stats.setStat(StatDoubleType.DAMAGE, statsJson.getDouble("damage"));
        stats.setStat(StatDoubleType.ATTACK_SPEED, statsJson.getDouble("attackSpeed"));
        stats.setStat(StatIntegerType.ARMOR, statsJson.getInt("armor"));
        stats.setStat(StatIntegerType.STRENGTH, statsJson.getInt("strength"));
        stats.setStat(StatIntegerType.DEXTERITY, statsJson.getInt("dexterity"));
        stats.setStat(StatIntegerType.VITALITY, statsJson.getInt("vitality"));
        stats.setStat(StatIntegerType.INTELLIGENCE, statsJson.getInt("intelligence"));
        stats.setStat(StatIntegerType.PHYSICAL_RESIST, statsJson.getInt("physicalResist"));
        stats.setStat(StatIntegerType.FIRE_RESIST, statsJson.getInt("fireResist"));
        stats.setStat(StatIntegerType.COLD_RESIST, statsJson.getInt("coldResist"));
        stats.setStat(StatIntegerType.LIGHTNING_RESIST, statsJson.getInt("lightningResist"));
        stats.setStat(StatIntegerType.POISON_RESIST, statsJson.getInt("poisonResist"));
        stats.setStat(StatIntegerType.ARCANE_RESIST, statsJson.getInt("arcaneResist"));
        stats.setStat(StatDoubleType.CRIT_DAMAGE, statsJson.getDouble("critDamage"));
        stats.setStat(StatDoubleType.BLOCK_CHANCE, statsJson.getDouble("blockChance"));
        stats.setStat(StatIntegerType.BLOCK_AMOUNT_MIN, statsJson.getInt("blockAmountMin"));
        stats.setStat(StatIntegerType.BLOCK_AMOUNT_MAX, statsJson.getInt("blockAmountMax"));
        stats.setStat(StatDoubleType.DAMAGE_INCREASE, statsJson.getDouble("damageIncrease"));
        stats.setStat(StatDoubleType.CRIT_CHANCE, statsJson.getDouble("critChance"));
        stats.setStat(StatDoubleType.DAMAGE_REDUCTION, statsJson.getDouble("damageReduction"));
        stats.setStat(StatDoubleType.THORNS, statsJson.getDouble("thorns"));
        stats.setStat(StatDoubleType.LIFE_STEAL, statsJson.getDouble("lifeSteal"));
        stats.setStat(StatDoubleType.LIFE_PER_KILL, statsJson.getDouble("lifePerKill"));
        stats.setStat(StatDoubleType.GOLD_FIND, statsJson.getDouble("goldFind"));
        stats.setStat(StatDoubleType.MAGIC_FIND, statsJson.getDouble("magicFind"));
        stats.setStat(StatDoubleType.LIFE_ON_HIT, statsJson.getDouble("lifeOnHit"));
        stats.setStat(StatIntegerType.PRIMARY_RESOURCE, statsJson.getInt("primaryResource"));
        stats.setStat(StatIntegerType.SECONDARY_RESOURCE, statsJson.getInt("secondaryResource"));

        return stats;
    }

    private HeroProgression getHeroProgression(JSONObject progressionJson) throws JSONException {
        HeroProgression actProgression = new HeroProgression();

        for (ActType actType : ActType.ALL) {
            JSONObject json = progressionJson.getJSONObject(actType.getValue());

            Act act = new Act();

            act.setCompleted(json.getBoolean("completed"));
            act.setCompletedQuests(getCompletedQuests(json.getJSONArray("completedQuests")));

            actProgression.addAct(act);
        }

        return actProgression;
    }

    private List<Quest> getCompletedQuests(JSONArray completedQuestsJson) throws JSONException {
        List<Quest> completedQuests = new ArrayList<Quest>();

        for (int i = 0; i < completedQuestsJson.length(); i++) {
            JSONObject json = completedQuestsJson.getJSONObject(i);

            Quest quest = new Quest();
            quest.setSlug(json.getString("slug"));
            quest.setName(json.getString("name"));

            completedQuests.add(quest);
        }

        return completedQuests;
    }
}
