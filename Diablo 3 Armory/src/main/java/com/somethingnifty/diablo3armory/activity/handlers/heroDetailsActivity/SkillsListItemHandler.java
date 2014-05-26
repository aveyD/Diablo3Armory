package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.app.Activity;
import android.content.Intent;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.SkillDetailsActivity;
import com.somethingnifty.diablo3armory.activity.handlers.EventHandler;
import com.somethingnifty.diablo3armory.domain.Skill;

public class SkillsListItemHandler extends EventHandler {

    public SkillsListItemHandler(Activity activity) { super(activity); }

    public void getSkill(Skill skill) {
        if (skill != null) {
            startSkillDetailsActivity(skill);
        }
    }

    private void startSkillDetailsActivity(Skill skill) {
        Intent intent = new Intent(activity, SkillDetailsActivity.class);
        intent.putExtra(activity.getResources().getString(R.string.skill), skill);
        activity.startActivity(intent);
    }
}
