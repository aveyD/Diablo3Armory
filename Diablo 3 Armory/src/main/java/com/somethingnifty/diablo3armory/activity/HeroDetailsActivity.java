package com.somethingnifty.diablo3armory.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity.StatsScreenFragment;
import com.somethingnifty.diablo3armory.activity.handlers.mainActivityHandlers.GetProfileWebServiceTask;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.CareerProfile;
import com.somethingnifty.diablo3armory.helpers.CareerCreator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class HeroDetailsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_details);

        Intent intent = getIntent();
        ActiveHero hero = (ActiveHero) intent.getSerializableExtra(getResources().getString(R.string.hero_profile));

        this.setTitle(hero.getName());

        FragmentPagerAdapter adapterViewPager = new HeroDetailsAdapter(getSupportFragmentManager(), new ActiveHero());

        ViewPager vPager = (ViewPager) findViewById(R.id.hero_details_view_pager);
        vPager.setAdapter(adapterViewPager);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.hero_details_pager_tab_strip);
        tabStrip.setViewPager(vPager);
    }

    public static class HeroDetailsAdapter extends FragmentPagerAdapter{
        private static final int NUM_OF_HERO_DETAIL_SCREENS = 5;

        private ActiveHero activeHero;

        public HeroDetailsAdapter(FragmentManager fragmentManager, ActiveHero activeHero){
            super(fragmentManager);

            this.activeHero = activeHero;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return StatsScreenFragment.newInstance(activeHero);
                case 1:
                    return StatsScreenFragment.newInstance(activeHero);
                case 2:
                    return StatsScreenFragment.newInstance(activeHero);
                case 3:
                    return StatsScreenFragment.newInstance(activeHero);
                case 4:
                    return StatsScreenFragment.newInstance(activeHero);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_OF_HERO_DETAIL_SCREENS;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return "screen #" + position;
        }
    }
}
