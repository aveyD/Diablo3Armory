package com.somethingnifty.diablo3armory.helpers;

import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.ActiveSkill;
import com.somethingnifty.diablo3armory.domain.CraftedBy;
import com.somethingnifty.diablo3armory.domain.Follower;
import com.somethingnifty.diablo3armory.domain.FollowerMaster;
import com.somethingnifty.diablo3armory.domain.ItemLoadoutActiveHero;
import com.somethingnifty.diablo3armory.domain.ItemWearableActiveHero;
import com.somethingnifty.diablo3armory.domain.PassiveSkill;
import com.somethingnifty.diablo3armory.domain.RandomAffix;
import com.somethingnifty.diablo3armory.domain.Skill;
import com.somethingnifty.diablo3armory.domain.enums.FollowerType;
import com.somethingnifty.diablo3armory.domain.enums.Gender;
import com.somethingnifty.diablo3armory.domain.enums.HeroType;
import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;

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

        // TODO: fix up this. it shouldn't be "skills"
        List<ActiveSkill> activeSkills = getActiveSkills(heroJson.getJSONObject("skills"));
        hero.setActiveSkills(activeSkills);

        List<PassiveSkill> passiveSkills = getPassiveSkills(heroJson.getJSONObject("skills"));
        hero.setPassiveSkills(passiveSkills);

        ItemLoadoutActiveHero items = getItems(heroJson.getJSONObject("items"));
        hero.setItemLoadoutActiveHero(items);

        FollowerMaster followerMaster = getFollowerMaster(heroJson.getJSONObject("followers"));
        hero.setFollowerMaster(followerMaster);

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

            followerMaster.setFollower(followerType, follower);
        }
        return followerMaster;
    }
}
