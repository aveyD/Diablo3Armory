package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.Follower;
import com.somethingnifty.diablo3armory.domain.FollowerAndType;
import com.somethingnifty.diablo3armory.domain.FollowerMaster;
import com.somethingnifty.diablo3armory.domain.enums.FollowerType;

import java.util.ArrayList;
import java.util.List;

public class FollowerScreenFragment extends ListFragment
{
    private static final String ACTIVE_HERO_BUNDLE_ENTRY = "activeHero";

    protected ActiveHero activeHero;

    public static FollowerScreenFragment newInstance(ActiveHero activeHero) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ACTIVE_HERO_BUNDLE_ENTRY, activeHero);

        FollowerScreenFragment fragment = new FollowerScreenFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activeHero = (ActiveHero) getArguments().getSerializable(ACTIVE_HERO_BUNDLE_ENTRY);

        FollowerArrayAdapter adapter = new FollowerArrayAdapter(this.getActivity(), getFollowerAndType(activeHero.getFollowerMaster()));
        setListAdapter(adapter);
    }

    private List<FollowerAndType> getFollowerAndType(FollowerMaster followerMaster) {
        List<FollowerAndType> followers = new ArrayList<FollowerAndType>();

        for (FollowerType followerType : FollowerType.ALL) {
            Follower follower = followerMaster.getFollower(followerType);
            if (follower != null) {
                FollowerAndType followerAndType = new FollowerAndType(followerType, follower);
                followers.add(followerAndType);
            }
        }

        return followers;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follower_list_screen, container, false);

        //do stuff here

        return view;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id)
    {
        super.onListItemClick(listView, view, position, id);

        FollowerListItemHandler handler = getFollowerListItemHandler();

        FollowerType type = ((FollowerAndType)listView.getItemAtPosition(position)).getFollowerType();
        Follower follower = ((FollowerAndType)listView.getItemAtPosition(position)).getFollower();

        handler.getFollower(type, follower);
    }

    //For mocking purposes
    FollowerListItemHandler getFollowerListItemHandler() {
        return new FollowerListItemHandler(this.getActivity());
    }
}
