package com.protegra.diablo3armory.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.protegra.diablo3armory.R;
import com.protegra.diablo3armory.domain.ActiveHero;
import com.protegra.diablo3armory.domain.CareerProfile;

import java.util.Map;

public class HeroListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);

        testIntentPassing();
    }

    //TODO: Stub for testing intent passing of serializable object
    private void testIntentPassing() {
        Intent intent = getIntent();
        CareerProfile profile = (CareerProfile) intent.getSerializableExtra(getResources().getString(R.string.career_profile_search));

        TextView view = (TextView) findViewById(R.id.hero_list_activity_text);

        String heroString = "Active Heroes:\n";
        for (Map.Entry<Long, ActiveHero> entry: profile.getActiveHeroes().entrySet()){
            heroString += entry.getValue().getName() + " - " + entry.getKey() + "\n";
        }

        view.setText(heroString);
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
}
