package com.somethingnifty.diablo3armory.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.handlers.heroListActivityHandlers.HeroArrayAdapter;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.CareerProfile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class HeroListActivity extends ListActivity
{
    private static final String CACHE_KEY = "careerProfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);

        List<ActiveHero> activeHeroes = null;
        try {
            activeHeroes = getActiveHeroes();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HeroArrayAdapter adapter = new HeroArrayAdapter(this, activeHeroes);
        setListAdapter(adapter);
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

        ois.close();
        fis.close();

        return (CareerProfile) ois.readObject();
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
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

//        Toast.makeText(this.getBaseContext(), "click", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, HeroDetailsActivity.class);
        //intent.putExtra(activity.getResources().getString(R.string.career_profile_search), profile);
        this.startActivity(intent);
    }
}
