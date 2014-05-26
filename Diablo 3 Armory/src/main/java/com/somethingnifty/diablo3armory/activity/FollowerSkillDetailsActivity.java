package com.somethingnifty.diablo3armory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.Skill;

public class FollowerSkillDetailsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_details);

        Intent intent = getIntent();
        Skill skill = (Skill) intent.getSerializableExtra(getResources().getString(R.string.skill));

        this.setTitle(skill.getName());
    }
}
