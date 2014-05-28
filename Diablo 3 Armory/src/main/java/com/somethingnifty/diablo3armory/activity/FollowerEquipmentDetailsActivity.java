package com.somethingnifty.diablo3armory.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.Item;
import com.somethingnifty.diablo3armory.helpers.TooltipDetailsWebViewPopulator;

public class FollowerEquipmentDetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower_equipment_details);

        Intent intent = getIntent();
        Item item = (Item) intent.getSerializableExtra(getResources().getString(R.string.follower_equipment_details_load));

        this.setTitle(item.getName());

        WebView myWebView = (WebView) findViewById(R.id.follower_equipment_web_view);

        TooltipDetailsWebViewPopulator populator = new TooltipDetailsWebViewPopulator();
        populator.populateTooltipWebview(myWebView, item.getTooltipParams());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.follower_equipment_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings | super.onOptionsItemSelected(item);
    }
}
