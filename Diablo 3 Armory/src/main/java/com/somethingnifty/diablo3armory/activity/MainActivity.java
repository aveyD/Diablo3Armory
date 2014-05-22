package com.somethingnifty.diablo3armory.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.handlers.mainActivityHandlers.SearchHeroButtonHandler;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {

    private static final String PREF_FILE_NAME = "myPrefs";
    private static final String CACHE_BATTLE_TAG = "battleTag";
    private static final String CACHE_DOMAIN = "domain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readBattletagCache();
    }

    private void readBattletagCache() {
        SharedPreferences settings = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        String battleTag = settings.getString(CACHE_BATTLE_TAG, "");

        EditText text = (EditText) findViewById(R.id.hero_text);
        text.setText(battleTag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public void searchHero(View view) throws IOException, ExecutionException, InterruptedException, JSONException {
        cacheBattleTagAndRegion();

        SearchHeroButtonHandler handler = getSearchHeroHandler();
        handler.searchHero();
    }

    private void cacheBattleTagAndRegion() throws IOException {
        EditText text = (EditText) findViewById(R.id.hero_text);
        String battleTag = text.getText() != null ? text.getText().toString() : "";
        String selectedRegionUrl = getRegionUrl((RadioGroup) findViewById(R.id.regions_radio_group));

        SharedPreferences settings = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(CACHE_BATTLE_TAG, battleTag);
        editor.putString(CACHE_DOMAIN, selectedRegionUrl);
        editor.commit();
    }

    //For mocking purposes
    SearchHeroButtonHandler getSearchHeroHandler() {
        return new SearchHeroButtonHandler(this);
    }

    // TODO: duplicate method.
    private String getRegionUrl(RadioGroup regionsRadioGroup) {
        int id = regionsRadioGroup.getCheckedRadioButtonId();
        String regionUrl = "";

        switch(id)
        {
            case R.id.na_region_radio_button:
                regionUrl = getResources().getString(R.string.na_region_url);
                break;
            case R.id.eu_region_radio_button:
                regionUrl = getResources().getString(R.string.eu_region_url);
                break;
            case R.id.tw_region_radio_button:
                regionUrl = getResources().getString(R.string.tw_region_url);
                break;
            case R.id.kr_region_radio_button:
                regionUrl = getResources().getString(R.string.kr_region_url);
                break;
        }

        return regionUrl;
    }
}
