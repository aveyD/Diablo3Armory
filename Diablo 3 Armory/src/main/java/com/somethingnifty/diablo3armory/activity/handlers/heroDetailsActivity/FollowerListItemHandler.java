package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.app.Activity;
import android.content.Intent;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.FollowerDetailsActivity;
import com.somethingnifty.diablo3armory.activity.handlers.EventHandler;
import com.somethingnifty.diablo3armory.domain.Follower;
import com.somethingnifty.diablo3armory.domain.enums.FollowerType;

public class FollowerListItemHandler extends EventHandler {

    public FollowerListItemHandler(Activity activity)
    {
        super(activity);
    }

    public void getFollower(FollowerType type, Follower follower) {
        if (type != null && follower != null) {
            startFollowerDetailsActivity(type, follower);
        }
    }

    private void startFollowerDetailsActivity(FollowerType type, Follower follower) {
        Intent intent = new Intent(activity, FollowerDetailsActivity.class);
        intent.putExtra(activity.getResources().getString(R.string.follower_type), type);
        intent.putExtra(activity.getResources().getString(R.string.follower), follower);
        activity.startActivity(intent);
    }
}
