package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.ActiveHero;

public class HeroProgressionScreenFragment extends Fragment
{
    private static final String ACTIVE_HERO_BUNDLE_ENTRY = "activeHero";

    protected ActiveHero activeHero;

    public static HeroProgressionScreenFragment newInstance(ActiveHero activeHero) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ACTIVE_HERO_BUNDLE_ENTRY, activeHero);

        HeroProgressionScreenFragment fragment = new HeroProgressionScreenFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activeHero = (ActiveHero) getArguments().getSerializable(ACTIVE_HERO_BUNDLE_ENTRY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progression_screen, container, false);

        //do stuff here

        return view;
    }
}
