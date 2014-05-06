package com.protegra.diablo3armory.helpers;

import com.protegra.diablo3armory.domain.Career;
import com.protegra.diablo3armory.domain.CareerProgression;
import com.protegra.diablo3armory.domain.Gender;
import com.protegra.diablo3armory.domain.Hero;
import com.protegra.diablo3armory.domain.HeroClass;
import com.protegra.diablo3armory.domain.Kills;
import com.protegra.diablo3armory.domain.TimePlayed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CareerCreator {

    private static final long TIME_MULTIPLER = 1000;

    public Career createCareer(JSONObject object) throws JSONException {

        Career career = new Career();

        Map<Long, Hero> heroes = getHeroes(object.getJSONArray("heroes"));
        career.setHeroes(heroes);

        Hero lastHeroPlayed = getLastHeroPlayed(object.getLong("lastHeroPlayed"), heroes);
        career.setLastHeroPlayed(lastHeroPlayed);

        Date lastUpdated = getDate(object.getLong("lastUpdated"));
        career.setLastUpdated(lastUpdated);

        Kills kills = getKills(object.getJSONObject("kills"));
        career.setKills(kills);

        TimePlayed timePlayed = getTimePlayed(object.getJSONObject("timePlayed"));
        career.setTimePlayed(timePlayed);

        List<Hero> fallenHeroes = getFallenHeroes(object.getJSONArray("fallenHeroes"));
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

    private Map<Long, Hero> getHeroes(JSONArray heroesJson) throws JSONException {
        Map<Long, Hero> heroesMap = new HashMap<Long, Hero>();

        for (int i = 0; i < heroesJson.length(); i++) {
            JSONObject json = heroesJson.getJSONObject(i);

            Hero hero = new Hero();
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

    private Hero getLastHeroPlayed(Long lastHeroPlayedId, Map<Long, Hero> heroes) {
        return heroes.get(lastHeroPlayedId);
    }

    private Kills getKills(JSONObject kills) throws JSONException {
        Kills kill = new Kills();

        kill.setMonsterKills(kills.getInt("monsters"));
        kill.setEliteKills(kills.getInt("elites"));
        kill.setHardcoreMonsterKills(kills.getInt("hardcoreMonsters"));

        return kill;
    }

    private TimePlayed getTimePlayed(JSONObject timePlayedJson) {
        TimePlayed time = new TimePlayed();

        //time.set

        return null;
    }

    private List<Hero> getFallenHeroes(JSONArray fallenHeroes) {
        return null;
    }

    private CareerProgression getCareerProgression(JSONObject progression) {
        return null;
    }
}
