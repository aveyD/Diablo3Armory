package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.Stats;

public class AttributeScreenFragment extends Fragment {
    private static final String ACTIVE_HERO_BUNDLE_ENTRY = "activeHero";

    protected ActiveHero activeHero;

    public static AttributeScreenFragment newInstance(ActiveHero activeHero) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ACTIVE_HERO_BUNDLE_ENTRY, activeHero);

        AttributeScreenFragment fragment = new AttributeScreenFragment();
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
        View view = inflater.inflate(R.layout.fragment_attribute_screen, container, false);

        Stats stats = activeHero.getStats();

        return view;
    }
}
