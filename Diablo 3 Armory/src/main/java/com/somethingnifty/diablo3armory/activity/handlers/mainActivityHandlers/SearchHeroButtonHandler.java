package com.somethingnifty.diablo3armory.activity.handlers.mainActivityHandlers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.HeroListActivity;
import com.somethingnifty.diablo3armory.activity.handlers.EventHandler;
import com.somethingnifty.diablo3armory.domain.CareerProfile;
import com.somethingnifty.diablo3armory.helpers.BattletagUtil;
import com.somethingnifty.diablo3armory.helpers.CareerCreator;
import com.somethingnifty.diablo3armory.helpers.NetworkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SearchHeroButtonHandler extends EventHandler {

    public SearchHeroButtonHandler(Activity activity)
    {
        super(activity);
    }

    public void searchHero() throws IOException, ExecutionException, InterruptedException, JSONException {
        EditText editText = (EditText) findViewById(R.id.hero_text);

        String battleTag = editText.getText() != null ? editText.getText().toString() : "";
        battleTag = BattletagUtil.formatBattletagForWebService(battleTag);

        if (BattletagUtil.isValidWebserviceBattletagFormat(battleTag)) {
            if (NetworkUtil.isNetworkAvailable(activity)) {
                String selectedRegionUrl = getRegionUrl((RadioGroup) findViewById(R.id.regions_radio_group));
                getCareer(selectedRegionUrl, battleTag);
            }
            else{
                Toast.makeText(activity, getResources().getString(R.string.no_network_available), Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            displayInvalidBattletagFormatAlert();
        }
    }

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

    private void getCareer(String domain, String profileName) throws IOException, ExecutionException, InterruptedException, JSONException {
        String url = domain + "/api/d3/profile/" +  profileName + "/";

        JSONObject result = getProfile(url);

        if (result != null) {
            CareerCreator creator = new CareerCreator();
            CareerProfile profile = creator.createCareer(result);

            startHeroListActivity(profile);
        }
        else
        {
            Toast.makeText(activity, profileName + " not found!", Toast.LENGTH_LONG).show();
        }
    }

    private JSONObject getProfile(String url) throws InterruptedException, ExecutionException {
        GetProfileWebServiceTask profile = new GetProfileWebServiceTask(activity);
        AsyncTask<String, Integer, JSONObject> task = profile.execute(url);

        return task.get();
    }

    private void startHeroListActivity(CareerProfile profile) {
        Intent intent = new Intent(activity, HeroListActivity.class);
        intent.putExtra(activity.getResources().getString(R.string.career_profile_search), profile);
        activity.startActivity(intent);
    }

    private void displayInvalidBattletagFormatAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(getResources().getString(R.string.valid_battletag_format_blurb))
                .setTitle(getResources().getString(R.string.invalid_battletag_format))
                .setPositiveButton(getResources().getString(R.string.ok), getEmptyButtonListener());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private DialogInterface.OnClickListener getEmptyButtonListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        };
    }
}
