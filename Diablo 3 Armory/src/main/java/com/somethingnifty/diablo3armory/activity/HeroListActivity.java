package com.somethingnifty.diablo3armory.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.handlers.heroListActivityHandlers.HeroArrayAdapter;
import com.somethingnifty.diablo3armory.activity.handlers.heroListActivityHandlers.HeroListItemHandler;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.CareerProfile;

import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HeroListActivity extends ListActivity
{
    private static final String CACHE_KEY = "careerProfile";
    private static final String PREF_FILE_NAME = "myPrefs";
    private static final String CACHE_DOMAIN = "domain";
    private static final String CACHE_BATTLE_TAG = "battleTag";
    private static final String CACHE_HERO_ID = "heroId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);

        loadHeroList();
    }

    private void loadHeroList() {
        try {
            List<ActiveHero> activeHeroes = getActiveHeroes();

            HeroArrayAdapter adapter = new HeroArrayAdapter(this, activeHeroes);
            setListAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<ActiveHero> getActiveHeroes() throws IOException, ClassNotFoundException {
        Intent intent = getIntent();
        CareerProfile profile = (CareerProfile) intent.getSerializableExtra(getResources().getString(R.string.career_profile_search));

        List<ActiveHero> activeHeroes;
        if (profile != null) {
            activeHeroes = new ArrayList<ActiveHero>(profile.getActiveHeroes().values());

            cacheCareerProfile(profile);
        }
        else {
            profile = readCareerProfileFromCache();

            activeHeroes = new ArrayList<ActiveHero>(profile.getActiveHeroes().values());
        }

        return activeHeroes;
    }

    private void cacheCareerProfile(CareerProfile profile) throws IOException {
        FileOutputStream fos = this.openFileOutput(CACHE_KEY, MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(profile);

        oos.close();
        fos.close();
    }

    private CareerProfile readCareerProfileFromCache() throws IOException, ClassNotFoundException {
        FileInputStream fis = this.openFileInput(CACHE_KEY);
        ObjectInputStream ois = new ObjectInputStream(fis);

        CareerProfile profile =  (CareerProfile) ois.readObject();

        ois.close();
        fis.close();

        return profile;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hero_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        HeroListItemHandler handler = getHeroListItemHandler();

        SharedPreferences settings = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        String domain = settings.getString(CACHE_DOMAIN, "");
        String battleTag = settings.getString(CACHE_BATTLE_TAG, "");
        String heroId = ((ActiveHero)listView.getItemAtPosition(position)).getId().toString();

        // TODO: don't throw stack trace, but method can't throw exceptions
        try {
            handler.getHero(domain, battleTag, heroId);
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //For mocking purposes
    HeroListItemHandler getHeroListItemHandler() {
        return new HeroListItemHandler(this);
    }
}
