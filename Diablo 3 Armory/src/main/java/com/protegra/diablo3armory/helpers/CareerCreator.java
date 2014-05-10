package com.protegra.diablo3armory.helpers;

import com.protegra.diablo3armory.domain.ActiveHero;
import com.protegra.diablo3armory.domain.Career;
import com.protegra.diablo3armory.domain.CareerProgression;
import com.protegra.diablo3armory.domain.CraftedBy;
import com.protegra.diablo3armory.domain.Death;
import com.protegra.diablo3armory.domain.FallenHero;
import com.protegra.diablo3armory.domain.Gender;
import com.protegra.diablo3armory.domain.HeroClass;
import com.protegra.diablo3armory.domain.Item;
import com.protegra.diablo3armory.domain.ItemLoadoutFallenHero;
import com.protegra.diablo3armory.domain.ItemWearable;
import com.protegra.diablo3armory.domain.ItemWearableType;
import com.protegra.diablo3armory.domain.Kills;
import com.protegra.diablo3armory.domain.RandomAffix;
import com.protegra.diablo3armory.domain.Reagent;
import com.protegra.diablo3armory.domain.TimePlayed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
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
            hero.setHardcore(json.getBoolean("hardcore"));

            heroesMap.put(id, hero);
        }

        return heroesMap;
    }

    private Date getDate(Long longDate) throws JSONException {
        Date date = new Date();
        date.setTime(longDate * TIME_MULTIPLER);

        return date;
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
            setItemLoadoutFallenHero(hero, json.getJSONObject("items"));
            hero.setHardcore(json.getBoolean("hardcore"));
            setDeath(hero, json.getJSONObject("death"));
            setEliteKills(hero, json.getJSONObject("kills"));

            heroesMap.put(id, hero);
        }

        return heroesMap;
    }

    private void setItemLoadoutFallenHero(FallenHero fallenHero, JSONObject itemLoadoutJson) throws JSONException {

        ItemLoadoutFallenHero itemLoadoutFallenHero = new ItemLoadoutFallenHero();

        EnumSet<ItemWearableType> enumSet = EnumSet.allOf(ItemWearableType.class);

        for (ItemWearableType itemWearableType : enumSet){
            JSONObject itemJson = itemLoadoutJson.getJSONObject(itemWearableType.getValue());
            ItemWearable itemWearable = getItemWearableFallenHero(itemJson);

            itemLoadoutFallenHero.setItemFallenHero(itemWearableType, itemWearable);
        }

        fallenHero.setItemLoadoutFallenHero(itemLoadoutFallenHero);
    }

    private ItemWearable getItemWearableFallenHero(JSONObject itemJson) throws JSONException {
        ItemWearable item = new ItemWearable();

        setItemFields(itemJson, item);
        setCraftedBy(item, itemJson.getJSONArray("craftedBy"));
        setRandomAffixes(item, itemJson.getJSONArray("randomAffixes"));

        return item;
    }

    private void setItemFields(JSONObject itemJson, Item item) throws JSONException {
        item.setId(itemJson.getString("id"));
        item.setIcon(itemJson.getString("icon"));
        item.setTooltipParams(itemJson.getString("tooltipParams"));
        item.setDisplayColor(itemJson.getString("displayColor"));
        item.setName(itemJson.getString("name"));
    }

    private void setCraftedBy(ItemWearable item, JSONArray craftedByJson) throws JSONException {
        List<CraftedBy> craftedByList = new ArrayList<CraftedBy>();

        for (int i = 0; i < craftedByJson.length(); i++){
            JSONObject currObject = craftedByJson.getJSONObject(i);

            CraftedBy craftedBy = new CraftedBy();

            craftedBy.setId(currObject.getString("id"));
            craftedBy.setSlug("slug");
            setReagents(craftedBy, currObject.getJSONArray("reagents"));
            craftedBy.setCost(currObject.getLong("cost"));
            setItemProduced(craftedBy, currObject.getJSONObject("itemProduced"));
            craftedBy.setName(currObject.getString("name"));

            craftedByList.add(craftedBy);
        }

        item.setCraftedByList(craftedByList);
    }

    private void setReagents(CraftedBy craftedBy, JSONArray reagentsJson) throws JSONException {
        List<Reagent> reagents = new ArrayList<Reagent>();

        for (int i = 0; i < reagentsJson.length(); i++) {
            JSONObject currObject = reagentsJson.getJSONObject(i);

            Reagent reagent = new Reagent();

            reagent.setQuantity(currObject.getInt("quantity"));
            setReagentItem(reagent, currObject.getJSONObject("item"));

            reagents.add(reagent);
        }

        craftedBy.setReagents(reagents);
    }

    private void setReagentItem(Reagent reagent, JSONObject itemJson) throws JSONException {
        Item item = new Item();

        setItemFields(itemJson, item);

        reagent.setReagentItem(item);
    }

    private void setItemProduced(CraftedBy craftedBy, JSONObject itemJson) throws JSONException {
        Item item = new Item();

        setItemFields(itemJson, item);

        craftedBy.setItemProduced(item);
    }

    private void setRandomAffixes(Item item, JSONArray randomAffixesJson) throws JSONException {
        List<RandomAffix> randomAffixes = new ArrayList<RandomAffix>();

        //TODO: Need to figure out what to parse for random affixes
        for (int i = 0; i < randomAffixesJson.length(); i++) {
            JSONObject currObject = randomAffixesJson.getJSONObject(i);
        }
    }

    private void setDeath(FallenHero hero, JSONObject deathJson) throws JSONException {
        Death death = new Death();

        death.setKiller(deathJson.getInt("killer"));
        death.setLocation(deathJson.getInt("location"));

        Date date = getDate(deathJson.getLong("time"));
        death.setTime(date);

        hero.setDeath(death);
    }

    private void setEliteKills(FallenHero hero, JSONObject killsJson) throws JSONException {
        hero.setEliteKills(killsJson.getInt("elites"));
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
