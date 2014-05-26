package com.somethingnifty.diablo3armory.activity.handlers.followerDetailsActivity;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity.SkillsArrayAdapter;
import com.somethingnifty.diablo3armory.domain.Follower;
import com.somethingnifty.diablo3armory.domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class FollowerSkillsScreenFragment extends ListFragment
{
    private static final String FOLLOWER_BUNDLE_ENTRY = "follower";

    protected Follower follower;

    public static FollowerSkillsScreenFragment newInstance(Follower follower) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(FOLLOWER_BUNDLE_ENTRY, follower);

        FollowerSkillsScreenFragment fragment = new FollowerSkillsScreenFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        follower = (Follower) getArguments().getSerializable(FOLLOWER_BUNDLE_ENTRY);

        SkillsArrayAdapter adapter = new SkillsArrayAdapter(this.getActivity(), getSkills(follower));
        setListAdapter(adapter);
    }

    private List<Skill> getSkills(Follower follower) {
        List<Skill> skills = new ArrayList<Skill>();
        skills.addAll(follower.getSkills());
        return skills;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skills_screen, container, false);

        //do stuff here

        return view;
    }
}
