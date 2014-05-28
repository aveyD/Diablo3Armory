package com.somethingnifty.diablo3armory.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.Skill;
import com.somethingnifty.diablo3armory.helpers.TooltipDetailsWebViewPopulator;

public class FollowerSkillDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_details);

        Intent intent = getIntent();
        Skill skill = (Skill) intent.getSerializableExtra(getResources().getString(R.string.skill));

        this.setTitle(skill.getName());

        WebView myWebView = (WebView) findViewById(R.id.skill_web_view);

        TooltipDetailsWebViewPopulator populator = new TooltipDetailsWebViewPopulator();
        populator.populateTooltipWebview(myWebView, skill.getTooltipUrl());
    }
}
