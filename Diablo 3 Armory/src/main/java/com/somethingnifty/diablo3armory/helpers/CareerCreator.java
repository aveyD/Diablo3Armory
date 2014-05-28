package com.somethingnifty.diablo3armory.helpers;

import com.somethingnifty.diablo3armory.domain.ActProgression;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.CareerProfile;
import com.somethingnifty.diablo3armory.domain.CraftedBy;
import com.somethingnifty.diablo3armory.domain.Death;
import com.somethingnifty.diablo3armory.domain.FallenHero;
import com.somethingnifty.diablo3armory.domain.Item;
import com.somethingnifty.diablo3armory.domain.ItemLoadoutFallenHero;
import com.somethingnifty.diablo3armory.domain.ItemWearable;
import com.somethingnifty.diablo3armory.domain.Kills;
import com.somethingnifty.diablo3armory.domain.RandomAffix;
import com.somethingnifty.diablo3armory.domain.Reagent;
import com.somethingnifty.diablo3armory.domain.TimePlayed;
import com.somethingnifty.diablo3armory.domain.enums.ActType;
import com.somethingnifty.diablo3armory.domain.enums.ColorType;
import com.somethingnifty.diablo3armory.domain.enums.Gender;
import com.somethingnifty.diablo3armory.domain.enums.HeroType;
import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;
import com.somethingnifty.diablo3armory.domain.enums.KillType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CareerCreator {

    public CareerProfile createCareer(JSONObject object) throws JSONException {

        CareerProfile careerProfile = new CareerProfile();

        Map<Long, ActiveHero> activeHeroes = getHeroes(object.getJSONArray("heroes"));
        careerProfile.setActiveHeroes(activeHeroes);

        ActiveHero lastHeroPlayed = getLastHeroPlayed(object.getLong("lastHeroPlayed"), activeHeroes);
        careerProfile.setLastHeroPlayed(lastHeroPlayed);

        Date lastUpdated = DateUtil.getDate(object.getLong("lastUpdated"));
        careerProfile.setLastUpdated(lastUpdated);

        Kills kills = getKills(object.getJSONObject("kills"));
        careerProfile.setKills(kills);

        TimePlayed timePlayed = getTimePlayed(object.getJSONObject("timePlayed"));
        careerProfile.setTimePlayed(timePlayed);

        Map<Long, FallenHero> fallenHeroes = getFallenHeroes(object.getJSONArray("fallenHeroes"));
        careerProfile.setFallenHeroes(fallenHeroes);

        Integer paragonLevel = object.getInt("paragonLevel");
        careerProfile.setParagonLevel(paragonLevel);

        Integer paragonLevelHardcore = object.getInt("paragonLevelHardcore");
        careerProfile.setParagonLevelHardcore(paragonLevelHardcore);

        String battleTag = object.getString("battleTag");
        careerProfile.setBattleTag(battleTag);

        ActProgression progression = getCareerProgression(object.getJSONObject("progression"));
        careerProfile.setProgression(progression);

        return careerProfile;
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
            hero.setLastUpdated(DateUtil.getDate(json.getLong("last-updated")));
            hero.setHeroType(HeroType.getHeroClass(json.getString("class")));
            hero.setGender(Gender.getGender(json.getInt("gender")));
            hero.setParagonLevel(json.getInt("paragonLevel"));
            hero.setDead(json.getBoolean("dead"));
            hero.setHardcore(json.getBoolean("hardcore"));

            heroesMap.put(id, hero);
        }

        return heroesMap;
    }

    private ActiveHero getLastHeroPlayed(Long lastHeroPlayedId, Map<Long, ActiveHero> heroes) {
        return heroes.get(lastHeroPlayedId);
    }

    private Kills getKills(JSONObject killsJson) throws JSONException {
        Kills kills = new Kills();

        for (KillType killType : KillType.ALL){
            int killCount = killsJson.getInt(killType.getValue());
            kills.setKills(killType, Integer.valueOf(killCount));
        }

        return kills;
    }

    private TimePlayed getTimePlayed(JSONObject timePlayedJson) throws JSONException {
        TimePlayed time = new TimePlayed();

        for (HeroType heroType : HeroType.ALL){
            double timePlayed = timePlayedJson.getDouble(heroType.getValue());
            time.setTimePlayed(heroType, Double.valueOf(timePlayed));
        }

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
            hero.setHeroType(HeroType.getHeroClass(json.getString("class")));
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

        for (ItemWearableType itemWearableType : ItemWearableType.ALL_HERO_ITEMS){
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
        item.setDisplayColor(ColorType.getColorType(itemJson.getString("displayColor")));
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

        Date date = DateUtil.getDate(deathJson.getLong("time"));
        death.setTime(date);

        hero.setDeath(death);
    }

    private void setEliteKills(FallenHero hero, JSONObject killsJson) throws JSONException {
        hero.setEliteKills(killsJson.getInt("elites"));
    }

    private ActProgression getCareerProgression(JSONObject progressionJson) throws JSONException {
        ActProgression progression = new ActProgression();

        for (ActType actType : ActType.ALL){
            boolean isCompleted = progressionJson.getBoolean(actType.getValue());
            progression.setCareerProgression(actType, Boolean.valueOf(isCompleted));
        }

        return progression;
    }
}
