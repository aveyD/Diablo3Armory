package com.somethingnifty.diablo3armory.helpers;

import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.ActiveSkill;
import com.somethingnifty.diablo3armory.domain.Skill;
import com.somethingnifty.diablo3armory.domain.enums.Gender;
import com.somethingnifty.diablo3armory.domain.enums.HeroType;

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
        List<ActiveSkill> activeSkills = getActiveSkills(heroJson.getJSONArray("skills"));
        hero.setActiveSkills(activeSkills);

        // TODO: fix up this. it shouldn't be "skills"
        List<Skill> passiveSkills = getSkills(heroJson.getJSONArray("skills"));
        hero.setPassiveSkills(passiveSkills);

        return hero;
    }

    private List<ActiveSkill> getActiveSkills(JSONArray skillJson) throws JSONException
    {
        List<ActiveSkill> skills = new ArrayList<ActiveSkill>();

        for (int i = 0; i < skillJson.length(); i++) {
            JSONObject json = skillJson.getJSONObject(i);

            Skill skill = new Skill();
            skill.setSlug(json.getString("slug"));
        }

        return skills;
    }

    private List<Skill> getSkills(JSONArray skillJson) throws JSONException
    {
        List<Skill> skills = new ArrayList<Skill>();

        for (int i = 0; i < skillJson.length(); i++) {
            JSONObject json = skillJson.getJSONObject(i);

            Skill skill = new Skill();
            skill.setSlug(json.getString("slug"));
        }

        return skills;
    }
}
