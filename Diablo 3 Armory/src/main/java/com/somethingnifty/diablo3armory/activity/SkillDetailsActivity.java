package com.somethingnifty.diablo3armory.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.webkit.WebView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.Rune;
import com.somethingnifty.diablo3armory.domain.Skill;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SkillDetailsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_details);

        Intent intent = getIntent();
        Skill skill = (Skill) intent.getSerializableExtra(getResources().getString(R.string.skill));
        Rune rune = (Rune) intent.getSerializableExtra(getResources().getString(R.string.rune));

        String title = rune != null ? skill.getName() + ": " + rune.getName() : skill.getName();

        this.setTitle(title);

        SkillAndRuneDownloader skillTask = new SkillAndRuneDownloader();
        skillTask.execute("http://us.battle.net/d3/en/tooltip/" + skill.getTooltipUrl());

        if (rune != null) {
            SkillAndRuneDownloader runeTask = new SkillAndRuneDownloader();
            runeTask.execute("http://us.battle.net/d3/en/tooltip/" + rune.getTooltipParams(), "rune");
        }
    }

    private class SkillAndRuneDownloader extends AsyncTask<String, Void, String> {
        boolean hasRune = false;

        @Override
        protected String doInBackground(String... urls) {
            HttpResponse response = null;
            HttpGet httpGet = null;
            HttpClient mHttpClient = null;
            String s = "";

            if (urls.length > 1) {
                hasRune = true;
            }

            try {
                if(mHttpClient == null){
                    mHttpClient = new DefaultHttpClient();
                }

                httpGet = new HttpGet(urls[0]);

                response = mHttpClient.execute(httpGet);
                s = EntityUtils.toString(response.getEntity(), "UTF-8");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }

        @Override
        protected void onPostExecute(String result){

            if (result.equals("")) {
                Log.d("Skill Details", "Body result text is empty.");
            }

            WebView myWebView = null;

            if (hasRune) {
                myWebView = (WebView) findViewById(R.id.rune_web_view);
            }
            else {
                myWebView = (WebView) findViewById(R.id.skill_web_view);
            }

            myWebView.getSettings().setJavaScriptEnabled(true);

            String head = "<html><head><link href=\"http://us.battle.net/d3/static/css/tooltips.css\" rel=\"stylesheet\">" +
                    "</head><body><div style=\"width: 100%;background-color:#000000;" +
                    "border: 2px solid #272727;\">";

            String foot = "</div></body></html>";

            String customHtml = head + result + foot;

            myWebView.loadData(customHtml, "text/html", "UTF-8");
        }
    }
}
