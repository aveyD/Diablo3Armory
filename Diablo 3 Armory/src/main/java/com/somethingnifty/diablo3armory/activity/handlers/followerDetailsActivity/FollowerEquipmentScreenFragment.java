package com.somethingnifty.diablo3armory.activity.handlers.followerDetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.FollowerEquipmentDetailsActivity;
import com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity.EquipmentArrayAdapter;
import com.somethingnifty.diablo3armory.domain.Follower;
import com.somethingnifty.diablo3armory.domain.ItemLoadout;
import com.somethingnifty.diablo3armory.domain.ItemWearableEquippable;
import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        ItemLoadout loadout = follower.getItemLoadout();

        List<Map.Entry<ItemWearableType, ItemWearableEquippable>> items = new ArrayList<Map.Entry<ItemWearableType, ItemWearableEquippable>>();
        items.addAll(loadout.getItemTypeByItem());

        EquipmentArrayAdapter adapter = new EquipmentArrayAdapter(this.getActivity(), items);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_equipment_screen, container, false);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Map.Entry<ItemWearableType, ItemWearableEquippable> entry = (Map.Entry<ItemWearableType, ItemWearableEquippable>) listView.getItemAtPosition(position);
        ItemWearableEquippable item = entry.getValue();

        Intent intent = new Intent(this.getActivity(), FollowerEquipmentDetailsActivity.class);
        intent.putExtra(getResources().getString(R.string.follower_equipment_details_load), item);

        this.getActivity().startActivity(intent);
    }
}
