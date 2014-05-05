package com.protegra.diablo3armory.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.protegra.diablo3armory.R;
import com.protegra.diablo3armory.activity.handlers.mainActivityHandlers.SearchHeroButtonHandler;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        SearchHeroButtonHandler handler = getSearchHeroHandler();
        handler.searchHero();
    }

    //For mocking purposes
    SearchHeroButtonHandler getSearchHeroHandler()
    {
        return new SearchHeroButtonHandler(this);
    }
}
