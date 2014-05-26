package com.somethingnifty.diablo3armory.activity.handlers.heroListActivityHandlers;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.HeroDetailsActivity;
import com.somethingnifty.diablo3armory.activity.handlers.EventHandler;
import com.somethingnifty.diablo3armory.activity.handlers.mainActivityHandlers.GetProfileWebServiceTask;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.helpers.BattletagUtil;
import com.somethingnifty.diablo3armory.helpers.HeroCreator;
import com.somethingnifty.diablo3armory.helpers.NetworkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class HeroListItemHandler extends EventHandler {

    public HeroListItemHandler(Activity activity)
    {
        super(activity);
    }

    public void getHero(String domain, String battleTag, String heroId) throws ExecutionException, InterruptedException, JSONException
    {
        if (NetworkUtil.isNetworkAvailable(activity)) {
            battleTag = BattletagUtil.formatBattletagForWebService(battleTag);
            String url = domain + "/api/d3/profile/" +  battleTag + "/hero/" + heroId;

            JSONObject result = getHeroProfile(url);

            if (result != null) {
                HeroCreator creator = new HeroCreator();
                ActiveHero hero = creator.createHero(result);

                startHeroDetailsActivity(hero);
            }
            else {
                Toast.makeText(activity, battleTag + " hero [" + heroId + "] not found!", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(activity, getResources().getString(R.string.no_network_available), Toast.LENGTH_LONG).show();
        }
    }

    private JSONObject getHeroProfile(String url) throws InterruptedException, ExecutionException {
        GetProfileWebServiceTask profile = new GetProfileWebServiceTask(activity);
        AsyncTask<String, Integer, JSONObject> task = profile.execute(url);

        return task.get();
    }

    private void startHeroDetailsActivity(ActiveHero hero) {
        Intent intent = new Intent(activity, HeroDetailsActivity.class);
        intent.putExtra(activity.getResources().getString(R.string.hero_profile), hero);
        activity.startActivity(intent);
    }
}
