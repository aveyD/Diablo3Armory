package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillsScreenFragment extends ListFragment
{
    private static final String ACTIVE_HERO_BUNDLE_ENTRY = "activeHero";

    protected ActiveHero activeHero;

    public static SkillsScreenFragment newInstance(ActiveHero activeHero) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ACTIVE_HERO_BUNDLE_ENTRY, activeHero);

        SkillsScreenFragment fragment = new SkillsScreenFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activeHero = (ActiveHero) getArguments().getSerializable(ACTIVE_HERO_BUNDLE_ENTRY);

        SkillsArrayAdapter adapter = new SkillsArrayAdapter(this.getActivity(), getSkills(activeHero));
        setListAdapter(adapter);
    }

    private List<Skill> getSkills(ActiveHero hero) {
        List<Skill> skills = new ArrayList<Skill>();

        skills.addAll(hero.getActiveSkills());
        skills.addAll(hero.getPassiveSkills());

        return skills;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skills_screen, container, false);

        //do stuff here

        return view;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        SkillsListItemHandler handler = getSkillsListItemHandler();

        handler.getSkill((Skill) listView.getItemAtPosition(position));
    }

    //For mocking purposes
    SkillsListItemHandler getSkillsListItemHandler() {
        return new SkillsListItemHandler(this.getActivity());
    }
}
