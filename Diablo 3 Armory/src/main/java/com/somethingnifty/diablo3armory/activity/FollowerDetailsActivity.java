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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FollowerDetailsActivity extends FragmentActivity {

    private static final String TYPE_CACHE_KEY = "type";
    private static final String FOLLOWER_CACHE_KEY = "follower";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower_details);

        FollowerType type = null;
        Follower follower = null;
        try {
            type = getFollowerType();
            follower = getFollower();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.setTitle(type.toString());

        FragmentPagerAdapter adapterViewPager = new FollowerDetailsAdapter(getSupportFragmentManager(), follower);

        ViewPager vPager = (ViewPager) findViewById(R.id.follower_details_view_pager);
        vPager.setAdapter(adapterViewPager);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.follower_details_pager_tab_strip);
        tabStrip.setViewPager(vPager);
    }

    private Follower getFollower() throws IOException, ClassNotFoundException
    {
        Intent intent = getIntent();
        Follower follower = (Follower) intent.getSerializableExtra(getResources().getString(R.string.follower));

        if (follower != null) {
            cacheFollower(follower);
        }
        else {
            follower = readFollowerFromCache();
        }

        return follower;
    }

    private FollowerType getFollowerType() throws IOException, ClassNotFoundException
    {
        Intent intent = getIntent();
        FollowerType type = (FollowerType) intent.getSerializableExtra(getResources().getString(R.string.follower_type));

        if (type != null) {
            cacheFollowerType(type);
        }
        else {
            type = readFollowerTypeFromCache();
        }

        return type;
    }

    private void cacheFollowerType(FollowerType followerType) throws IOException
    {
        FileOutputStream fos = this.openFileOutput(TYPE_CACHE_KEY, MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(followerType);

        oos.close();
        fos.close();
    }

    private FollowerType readFollowerTypeFromCache() throws IOException, ClassNotFoundException {
        FileInputStream fis = this.openFileInput(TYPE_CACHE_KEY);
        ObjectInputStream ois = new ObjectInputStream(fis);

        FollowerType type = (FollowerType) ois.readObject();

        ois.close();
        fis.close();

        return type;
    }

    private void cacheFollower(Follower follower) throws IOException
    {
        FileOutputStream fos = this.openFileOutput(FOLLOWER_CACHE_KEY, MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(follower);

        oos.close();
        fos.close();
    }

    private Follower readFollowerFromCache() throws IOException, ClassNotFoundException {
        FileInputStream fis = this.openFileInput(FOLLOWER_CACHE_KEY);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Follower follower = (Follower) ois.readObject();

        ois.close();
        fis.close();

        return follower;
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
