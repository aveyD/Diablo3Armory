package com.protegra.diablo3armory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {
    //public final static String EXTRA_MESSAGE = "com.protegra.diablo3armory.MESSAGE";

    private static final String VALID_BATTLETAG_PATTERN_POUND = "(\\w+)#(\\d{4})";
    private static final String VALID_BATTLETAG_PATTERN_DASH = "(\\w+)-(\\d{4})";

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

    public void searchHero(View view) throws IOException, ExecutionException, InterruptedException {
        EditText editText = (EditText) findViewById(R.id.hero_text);

        String battleTag = editText.getText() != null ? editText.getText().toString() : "";
        battleTag = formatBattleTag(battleTag);

        if (battleTag.matches(VALID_BATTLETAG_PATTERN_DASH)) {
            String selectedRegionUrl = getRegionUrl((RadioGroup) findViewById(R.id.regions_radio_group));

            // instead of hard coding the host we should use a radio button to choose host
            if (isNetworkAvailable()) {
                getCareer(selectedRegionUrl, battleTag);
            }
        }
        else
        {
            displayInvalidBatletagFormatAlert();
        }
    }

    private void displayInvalidBatletagFormatAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.valid_battletag_format_blurb))
                .setTitle(getResources().getString(R.string.invalid_battletag_format))
                .setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String formatBattleTag(String battleTag) {
        if(battleTag.matches(VALID_BATTLETAG_PATTERN_POUND))
        {
            battleTag = battleTag.replaceFirst("#", "-");
        }

        return battleTag;
    }

    private String getRegionUrl(RadioGroup regionsRadioGroup) {
        int id = regionsRadioGroup.getCheckedRadioButtonId();
        String regionUrl = "";

        switch(id)
        {
            case R.id.NA_region_radio_button:
                regionUrl = getResources().getString(R.string.NA_region_url);
                break;
            case R.id.EU_region_radio_button:
                regionUrl = getResources().getString(R.string.EU_region_url);
                break;
            case R.id.TW_region_radio_button:
                regionUrl = getResources().getString(R.string.TW_region_url);
                break;
        }

        return regionUrl;
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        return networkInfo != null && networkInfo.isConnected();
    }

    private void getCareer(String domain, String hero) throws IOException, ExecutionException, InterruptedException {
        String url = domain + "/api/d3/profile/" +  hero + "/";

        JSONObject result = getProfile(url);
        showResultToast(hero, result);
    }

    private JSONObject getProfile(String url) throws InterruptedException, ExecutionException {
        GetProfileTask profile = new GetProfileTask(this);

        AsyncTask<String, Integer, JSONObject> task = profile.execute(url);
        return task.get();
    }

    private void showResultToast(String hero, JSONObject result) {
        String resultMessage = hero + " ";
        resultMessage += (result != null) ? "was found!" : "not found";

        Toast.makeText(getApplication(), resultMessage, Toast.LENGTH_LONG).show();
    }
}
