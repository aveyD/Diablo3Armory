package com.somethingnifty.diablo3armory.activity.handlers.followerDetailsActivity;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.Follower;

public class FollowerEquipmentScreenFragment extends ListFragment
{
    private static final String FOLLOWER_BUNDLE_ENTRY = "follower";

    protected Follower follower;

    public static FollowerEquipmentScreenFragment newInstance(Follower follower) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(FOLLOWER_BUNDLE_ENTRY, follower);

        FollowerEquipmentScreenFragment fragment = new FollowerEquipmentScreenFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        follower = (Follower) getArguments().getSerializable(FOLLOWER_BUNDLE_ENTRY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipment_screen, container, false);

        //do stuff here

        return view;
    }
}
