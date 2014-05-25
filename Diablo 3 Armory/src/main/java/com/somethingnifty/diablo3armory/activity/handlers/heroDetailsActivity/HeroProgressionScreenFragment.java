package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.Act;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.HeroProgression;
import com.somethingnifty.diablo3armory.domain.enums.QuestType;
import com.somethingnifty.diablo3armory.helpers.PixelConverter;

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

        PixelConverter converter = new PixelConverter(getResources().getDisplayMetrics());

        for (Act act : progression.getHeroProgression()){

            LinearLayout actHeaderLayout = new LinearLayout(getActivity());
            LinearLayout.LayoutParams actHeaderLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int marginDip = converter.convertDiptoPix(10);
            actHeaderLayoutParams.setMargins(marginDip, marginDip, marginDip, marginDip);
            actHeaderLayout.setLayoutParams(actHeaderLayoutParams);
            actHeaderLayout.setOrientation(LinearLayout.VERTICAL);
            progressionLayout.addView(actHeaderLayout);

            RelativeLayout actHeaderTextLayout = new RelativeLayout(getActivity());

            TextView headerText = new TextView(getActivity());
            RelativeLayout.LayoutParams headerTextParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            headerTextParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            headerText.setLayoutParams(headerTextParams);
            headerText.setText(act.getActType().getToString());
            headerText.setTypeface(null, Typeface.BOLD);
            headerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            actHeaderTextLayout.addView(headerText);

            TextView headerTextCompleted = new TextView(getActivity());
            RelativeLayout.LayoutParams headerTextCompletedParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            headerTextCompletedParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            headerTextCompleted.setLayoutParams(headerTextCompletedParams);
            String actCompletedString = act.isCompleted() ? " Completed" : " Not completed";
            headerTextCompleted.setText(actCompletedString);
            headerTextCompleted.setTypeface(null, Typeface.BOLD);
            headerTextCompleted.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            actHeaderTextLayout.addView(headerTextCompleted);

            actHeaderLayout.addView(actHeaderTextLayout);

            int horizontalBarHeight = converter.convertDiptoPix(2);
            LinearLayout.LayoutParams horizontalBarLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, horizontalBarHeight);
            horizontalBarLayout.setMargins(0, 5, 0, 5);
            View headerHorizontalBar = new View(getActivity());
            headerHorizontalBar.setBackgroundColor(getResources().getColor(R.color.horizontal_rule_grey));
            headerHorizontalBar.setLayoutParams(horizontalBarLayout);
            actHeaderLayout.addView(headerHorizontalBar);

            Map<QuestType, Boolean> questCompletionByQuestType = act.getQuestCompletionByQuestType();

            for(Map.Entry<QuestType, Boolean> entry : questCompletionByQuestType.entrySet()){
                RelativeLayout questTextLayout = new RelativeLayout(getActivity());

                RelativeLayout.LayoutParams questTextParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                questTextParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                TextView questText = new TextView(getActivity());
                questText.setLayoutParams(questTextParams);
                String questString = entry.getKey().getQuestName();
                questText.setText(questString);
                questTextLayout.addView(questText);

                RelativeLayout.LayoutParams questCompletedParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                questCompletedParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                TextView completedText = new TextView(getActivity());
                completedText.setLayoutParams(questCompletedParams);
                String questCompletedString = entry.getValue().booleanValue() ? "\u2713" : "X";
                int questCompletedColor = entry.getValue().booleanValue() ? getResources().getColor(R.color.green) : getResources().getColor(R.color.red);
                completedText.setTextColor(questCompletedColor);
                completedText.setText(questCompletedString);
                questTextLayout.addView(completedText);

                actHeaderLayout.addView(questTextLayout);
            }

        }

        //do stuff here
        return view;

    }
}
