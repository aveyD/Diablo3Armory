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
import com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity.AttributesScreenFragment;
import com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity.EquipmentScreenFragment;
import com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity.FollowerScreenFragment;
import com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity.HeroProgressionScreenFragment;
import com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity.SkillsScreenFragment;
import com.somethingnifty.diablo3armory.domain.ActiveHero;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HeroDetailsActivity extends FragmentActivity {

    private static final String CACHE_KEY = "activeHero";
    private static final String PREF_FILE_NAME = "myPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_details);

        ActiveHero hero = null;
        try {
            hero = getActiveHero();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.setTitle(hero.getName());

        FragmentPagerAdapter adapterViewPager = new HeroDetailsAdapter(getSupportFragmentManager(), hero);

        ViewPager vPager = (ViewPager) findViewById(R.id.hero_details_view_pager);
        vPager.setAdapter(adapterViewPager);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.hero_details_pager_tab_strip);
        tabStrip.setViewPager(vPager);
    }

    private ActiveHero getActiveHero() throws IOException, ClassNotFoundException {
        Intent intent = getIntent();
        ActiveHero hero = (ActiveHero) intent.getSerializableExtra(getResources().getString(R.string.hero_profile));

        if (hero != null) {
            cacheActiveHero(hero);
        }
        else {
            hero = readActiveHeroFromCache();
        }

        return hero;
    }

    private void cacheActiveHero(ActiveHero hero) throws IOException
    {
        FileOutputStream fos = this.openFileOutput(CACHE_KEY, MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(hero);

        oos.close();
        fos.close();
    }

    private ActiveHero readActiveHeroFromCache() throws IOException, ClassNotFoundException {
        FileInputStream fis = this.openFileInput(CACHE_KEY);
        ObjectInputStream ois = new ObjectInputStream(fis);

        ActiveHero hero = (ActiveHero) ois.readObject();

        ois.close();
        fis.close();

        return hero;
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
                    return EquipmentScreenFragment.newInstance(activeHero);
                case 1:
                    return SkillsScreenFragment.newInstance(activeHero);
                case 2:
                    return AttributesScreenFragment.newInstance(activeHero);
                case 3:
                    return FollowerScreenFragment.newInstance(activeHero);
                case 4:
                    return HeroProgressionScreenFragment.newInstance(activeHero);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_OF_HERO_DETAIL_SCREENS;
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
                case 2:
                    pageTitle = "Stats";
                    break;
                case 3:
                    pageTitle = "Followers";
                    break;
                case 4:
                    pageTitle = "Progression";
                    break;
                default:
                    break;
            }

            return pageTitle;
        }
    }
}
