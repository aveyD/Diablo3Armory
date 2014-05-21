package com.somethingnifty.diablo3armory.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.handlers.mainActivityHandlers.SearchHeroButtonHandler;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {

    private static final String PREF_FILE_NAME = "myPrefs";
    private static final String CACHE_KEY = "battleTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readBattletagCache();
    }

    private void readBattletagCache() {
        SharedPreferences settings = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        String battleTag = settings.getString(CACHE_KEY, "");

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
        cacheBattleTag();

        SearchHeroButtonHandler handler = getSearchHeroHandler();
        handler.searchHero();
    }

    private void cacheBattleTag() throws IOException {
        EditText text = (EditText) findViewById(R.id.hero_text);
        String battleTag = text.getText() != null ? text.getText().toString() : "";

        SharedPreferences settings = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(CACHE_KEY, battleTag);
        editor.commit();
    }

    //For mocking purposes
    SearchHeroButtonHandler getSearchHeroHandler() {
        return new SearchHeroButtonHandler(this);
    }
}
