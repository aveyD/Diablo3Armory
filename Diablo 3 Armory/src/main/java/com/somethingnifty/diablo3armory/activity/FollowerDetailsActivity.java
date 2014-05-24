package com.somethingnifty.diablo3armory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.handlers.followerDetailsActivity.FollowerEquipmentScreenFragment;
import com.somethingnifty.diablo3armory.activity.handlers.followerDetailsActivity.FollowerSkillsScreenFragment;
import com.somethingnifty.diablo3armory.domain.Follower;
import com.somethingnifty.diablo3armory.domain.enums.FollowerType;

public class FollowerDetailsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower_details);

        Intent intent = getIntent();
        FollowerType type = (FollowerType) intent.getSerializableExtra(getResources().getString(R.string.follower_type));
        Follower follower = (Follower) intent.getSerializableExtra(getResources().getString(R.string.follower));

        this.setTitle(type.toString());

        FragmentPagerAdapter adapterViewPager = new FollowerDetailsAdapter(getSupportFragmentManager(), follower);

        ViewPager vPager = (ViewPager) findViewById(R.id.follower_details_view_pager);
        vPager.setAdapter(adapterViewPager);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.follower_details_pager_tab_strip);
        tabStrip.setViewPager(vPager);
    }

    public static class FollowerDetailsAdapter extends FragmentPagerAdapter{
        private static final int NUM_OF_FOLLOWER_DETAIL_SCREENS = 2;

        private Follower follower;

        public FollowerDetailsAdapter(FragmentManager fragmentManager, Follower follower){
            super(fragmentManager);

            this.follower = follower;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return FollowerEquipmentScreenFragment.newInstance(follower);
                case 1:
                    return FollowerSkillsScreenFragment.newInstance(follower);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_OF_FOLLOWER_DETAIL_SCREENS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String pageTitle = "";
            switch(position) {
                case 0:
                    pageTitle = "Equipment";
                    break;
                case 1:
                    pageTitle = "Skills";
                    break;
                default:
                    break;
            }

            return pageTitle;
        }
    }
}
