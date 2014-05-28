package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.app.Activity;
import android.content.Intent;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.FollowerSkillDetailsActivity;
import com.somethingnifty.diablo3armory.activity.SkillDetailsActivity;
import com.somethingnifty.diablo3armory.activity.handlers.EventHandler;
import com.somethingnifty.diablo3armory.domain.ActiveSkill;
import com.somethingnifty.diablo3armory.domain.PassiveSkill;
import com.somethingnifty.diablo3armory.domain.Skill;

public class SkillsListItemHandler extends EventHandler {

    public SkillsListItemHandler(Activity activity) { super(activity); }

    public void getSkill(Skill skill) {
        if (skill != null) {
            if (skill instanceof ActiveSkill || skill instanceof PassiveSkill) {
                startSkillDetailsActivity(skill);
            }
            else {
                startFollowerSkillDetailsActivity(skill);
            }
        }
    }

    private void startSkillDetailsActivity(Skill skill) {
        Intent intent = new Intent(activity, SkillDetailsActivity.class);
        intent.putExtra(activity.getResources().getString(R.string.skill), skill);

        if (skill instanceof ActiveSkill) {
            intent.putExtra(activity.getResources().getString(R.string.rune), ((ActiveSkill) skill).getRune());
        }

        activity.startActivity(intent);
    }

    private void startFollowerSkillDetailsActivity(Skill skill) {
        Intent intent = new Intent(activity, FollowerSkillDetailsActivity.class);
        intent.putExtra(activity.getResources().getString(R.string.skill), skill);
        activity.startActivity(intent);
    }
}
