package com.protegra.diablo3armory.helpers;

import com.protegra.diablo3armory.domain.ActiveHero;
import com.protegra.diablo3armory.domain.Career;
import com.protegra.diablo3armory.domain.CareerProgression;
import com.protegra.diablo3armory.domain.FallenHero;
import com.protegra.diablo3armory.domain.Gender;
import com.protegra.diablo3armory.domain.HeroClass;
import com.protegra.diablo3armory.domain.Item;
import com.protegra.diablo3armory.domain.ItemLoadout;
import com.protegra.diablo3armory.domain.Kills;
import com.protegra.diablo3armory.domain.TimePlayed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CareerCreator {

    private static final long TIME_MULTIPLER = 1000;

    public Career createCareer(JSONObject object) throws JSONException {

        Career career = new Career();

        Map<Long, ActiveHero> activeHeroes = getHeroes(object.getJSONArray("heroes"));
        career.setActiveHeroes(activeHeroes);

        ActiveHero lastHeroPlayed = getLastHeroPlayed(object.getLong("lastHeroPlayed"), activeHeroes);
        career.setLastHeroPlayed(lastHeroPlayed);

        Date lastUpdated = getDate(object.getLong("lastUpdated"));
        career.setLastUpdated(lastUpdated);

        Kills kills = getKills(object.getJSONObject("kills"));
        career.setKills(kills);

        TimePlayed timePlayed = getTimePlayed(object.getJSONObject("timePlayed"));
        career.setTimePlayed(timePlayed);

        Map<Long, FallenHero> fallenHeroes = getFallenHeroes(object.getJSONArray("fallenHeroes"));
        career.setFallenHeroes(fallenHeroes);

        Integer paragonLevel = object.getInt("paragonLevel");
        career.setParagonLevel(paragonLevel);

        Integer paragonLevelHardcore = object.getInt("paragonLevelHardcore");
        career.setParagonLevelHardcore(paragonLevelHardcore);

        String battleTag = object.getString("battleTag");
        career.setBattleTag(battleTag);

        CareerProgression progression = getCareerProgression(object.getJSONObject("progression"));
        career.setProgression(progression);

        return career;
    }

    private Date getDate(Long longDate) throws JSONException {
        Date date = new Date();
        date.setTime(longDate * TIME_MULTIPLER);

        return date;
    }

    private Map<Long, ActiveHero> getHeroes(JSONArray heroesJson) throws JSONException {
        Map<Long, ActiveHero> heroesMap = new HashMap<Long, ActiveHero>();

        for (int i = 0; i < heroesJson.length(); i++) {
            JSONObject json = heroesJson.getJSONObject(i);

            ActiveHero hero = new ActiveHero();
            Long id = json.getLong("id");

            hero.setId(id);
            hero.setLevel(json.getInt("level"));
            hero.setName(json.getString("name"));
            hero.setLastUpdated(getDate(json.getLong("last-updated")));
            hero.setHeroClass(HeroClass.getHeroClass(json.getString("class")));
            hero.setGender(Gender.getGender(json.getInt("gender")));
            hero.setParagonLevel(json.getInt("paragonLevel"));
            hero.setDead(json.getBoolean("dead"));

            heroesMap.put(id, hero);
        }

        return heroesMap;
    }

    private ActiveHero getLastHeroPlayed(Long lastHeroPlayedId, Map<Long, ActiveHero> heroes) {
        return heroes.get(lastHeroPlayedId);
    }

    private Kills getKills(JSONObject kills) throws JSONException {
        Kills kill = new Kills();

        kill.setMonsterKills(kills.getInt("monsters"));
        kill.setEliteKills(kills.getInt("elites"));
        kill.setHardcoreMonsterKills(kills.getInt("hardcoreMonsters"));

        return kill;
    }

    private TimePlayed getTimePlayed(JSONObject timePlayedJson) throws JSONException {
        TimePlayed time = new TimePlayed();

        time.setBarbarian(timePlayedJson.getDouble(HeroClass.BARBARIAN.getValue()));
        time.setCrusader(timePlayedJson.getDouble(HeroClass.CRUSADER.getValue()));
        time.setDemonHunter(timePlayedJson.getDouble(HeroClass.DEMON_HUNTER.getValue()));
        time.setMonk(timePlayedJson.getDouble(HeroClass.MONK.getValue()));
        time.setWitchDoctor(timePlayedJson.getDouble(HeroClass.WITCH_DOCTOR.getValue()));
        time.setWizard(timePlayedJson.getDouble(HeroClass.WIZARD.getValue()));

        return time;
    }

    private Map<Long, FallenHero> getFallenHeroes(JSONArray fallenHeroesJson) throws JSONException {
        Map<Long, FallenHero> heroesMap = new HashMap<Long, FallenHero>();

        for (int i = 0; i < fallenHeroesJson.length(); i++) {
            JSONObject json = fallenHeroesJson.getJSONObject(i);

            FallenHero hero = new FallenHero();
            Long id = json.getLong("heroId");

            hero.setId(id);
            hero.setLevel(json.getInt("level"));
            hero.setName(json.getString("name"));
            hero.setHeroClass(HeroClass.getHeroClass(json.getString("class")));
            hero.setGender(Gender.getGender(json.getInt("gender")));
            hero.setItemLoadout(getItemLoadout(json.getJSONObject(("items"))));

            heroesMap.put(id, hero);
        }

        return heroesMap;
    }

    private ItemLoadout getItemLoadout(JSONObject itemLoadoutJson) throws JSONException {

        ItemLoadout itemLoadout = new ItemLoadout();

        Item icon = getItem(itemLoadoutJson.getJSONObject("torso"));

        return itemLoadout;
    }

    private Item getItem(JSONObject itemJson) throws JSONException {
        Item item = new Item();

        item.setId(itemJson.getString("id"));
        item.setIcon(itemJson.getString("icon"));
        setCraftedBy(item, itemJson.getJSONArray("craftedBy"));

        return null;
    }

    private void setCraftedBy(Item item, JSONArray craftedByJson) {
    }

    private CareerProgression getCareerProgression(JSONObject progressionJson) throws JSONException {
        CareerProgression progression = new CareerProgression();

        progression.setAct1(progressionJson.getBoolean("act1"));
        progression.setAct2(progressionJson.getBoolean("act2"));
        progression.setAct3(progressionJson.getBoolean("act3"));
        progression.setAct4(progressionJson.getBoolean("act4"));
        progression.setAct5(progressionJson.getBoolean("act5"));

        return progression;
    }
}
