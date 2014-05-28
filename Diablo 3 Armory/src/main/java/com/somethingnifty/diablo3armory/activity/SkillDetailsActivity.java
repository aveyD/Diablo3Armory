package com.somethingnifty.diablo3armory.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.Rune;
import com.somethingnifty.diablo3armory.domain.Skill;
import com.somethingnifty.diablo3armory.helpers.TooltipDetailsWebViewPopulator;

public class SkillDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_details);

        Intent intent = getIntent();
        Skill skill = (Skill) intent.getSerializableExtra(getResources().getString(R.string.skill));
        Rune rune = (Rune) intent.getSerializableExtra(getResources().getString(R.string.rune));

        String title = rune != null ? skill.getName() + ": " + rune.getName() : skill.getName();

        this.setTitle(title);

        TooltipDetailsWebViewPopulator populator = new TooltipDetailsWebViewPopulator();

        WebView webView = (WebView) findViewById(R.id.skill_web_view);
        populator.populateTooltipWebview(webView, skill.getTooltipUrl());

        if (rune != null) {
            webView = (WebView) findViewById(R.id.rune_web_view);
            populator.populateTooltipWebview(webView, rune.getTooltipParams());
        }
    }
}
