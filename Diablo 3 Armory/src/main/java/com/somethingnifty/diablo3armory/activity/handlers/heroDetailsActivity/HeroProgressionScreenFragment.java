package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.Act;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.HeroProgression;
import com.somethingnifty.diablo3armory.domain.enums.QuestType;

import java.util.Map;

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
        LinearLayout progressionLayout = (LinearLayout) view.findViewById(R.id.progression_layout);

        HeroProgression progression = activeHero.getProgression();

        for (Act act : progression.getHeroProgression()){

            LinearLayout actHeaderLayout = new LinearLayout(getActivity());
            actHeaderLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            progressionLayout.addView(actHeaderLayout);

            TextView headerText = new TextView(getActivity());
            headerText.setText("TEST");
            actHeaderLayout.addView(headerText);

            Map<QuestType, Boolean> questCompletionByQuestType = act.getQuestCompletionByQuestType();

            //for ()

        }

        //do stuff here
        return view;

    }
}
