package com.protegra.diablo3armory.activity.handlers.mainActivityHandlers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.protegra.diablo3armory.R;
import com.protegra.diablo3armory.activity.handlers.EventHandler;
import com.protegra.diablo3armory.util.BattletagUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SearchHeroButtonHandler extends EventHandler {

    public SearchHeroButtonHandler(Activity activity)
    {
        super(activity);
    }

    public void searchHero() throws IOException, ExecutionException, InterruptedException {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.search_hero_progressbar);
        progressBar.setVisibility(View.VISIBLE);

        EditText editText = (EditText) findViewById(R.id.hero_text);

        String battleTag = editText.getText() != null ? editText.getText().toString() : "";
        battleTag = BattletagUtil.formatBattletagForWebService(battleTag);

        if (BattletagUtil.isValidWebserviceBattletagFormat(battleTag)) {
            if (isNetworkAvailable()) {
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

        progressBar.setVisibility(View.GONE);
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
                regionUrl = getResources().getString(R.string.kr_region_string);
                break;
        }

        return regionUrl;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        return networkInfo != null && networkInfo.isConnected();
    }

    private void getCareer(String domain, String hero) throws IOException, ExecutionException, InterruptedException {
        String url = domain + "/api/d3/profile/" +  hero + "/";

        JSONObject result = getProfile(url);

        //TODO: Remove the toasts and make an intent call to a character screen activity
        showResultToast(hero, result);
    }

    private JSONObject getProfile(String url) throws InterruptedException, ExecutionException {
        GetProfileWebServiceTask profile = new GetProfileWebServiceTask();
        AsyncTask<String, Integer, JSONObject> task = profile.execute(url);

        return task.get();
    }

    private void showResultToast(String hero, JSONObject result) {
        String resultMessage = hero + " ";
        resultMessage += (result != null) ? "was found!" : "not found";

        Toast.makeText(activity, resultMessage, Toast.LENGTH_LONG).show();
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
