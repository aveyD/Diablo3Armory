package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.activity.EquipmentDetailsActivity;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.ItemLoadout;
import com.somethingnifty.diablo3armory.domain.ItemWearableEquippable;
import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EquipmentScreenFragment extends ListFragment
{
    private static final String ACTIVE_HERO_BUNDLE_ENTRY = "activeHero";

    protected ActiveHero activeHero;

    public static EquipmentScreenFragment newInstance(ActiveHero activeHero) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ACTIVE_HERO_BUNDLE_ENTRY, activeHero);

        EquipmentScreenFragment fragment = new EquipmentScreenFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activeHero = (ActiveHero) getArguments().getSerializable(ACTIVE_HERO_BUNDLE_ENTRY);

        ItemLoadout loadout = activeHero.getItemLoadoutActiveHero();

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

        Intent intent = new Intent(this.getActivity(), EquipmentDetailsActivity.class);
        intent.putExtra(getResources().getString(R.string.equipment_details_load), item);

        this.getActivity().startActivity(intent);
    }
}
