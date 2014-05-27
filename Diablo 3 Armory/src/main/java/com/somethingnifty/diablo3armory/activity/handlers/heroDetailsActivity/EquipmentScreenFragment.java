package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.ActiveHero;
import com.somethingnifty.diablo3armory.domain.ItemLoadoutActiveHero;
import com.somethingnifty.diablo3armory.domain.ItemWearableActiveHero;
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

        ItemLoadoutActiveHero loadout = activeHero.getItemLoadoutActiveHero();

        List<Map.Entry<ItemWearableType, ItemWearableActiveHero>> items = new ArrayList<Map.Entry<ItemWearableType, ItemWearableActiveHero>>();
        items.addAll(loadout.getItemTypeByItem());

        EquipmentArrayAdapter adapter = new EquipmentArrayAdapter(this.getActivity(), items);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipment_screen, container, false);

        return view;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Map.Entry<ItemWearableType, ItemWearableActiveHero> entry = (Map.Entry<ItemWearableType, ItemWearableActiveHero>) listView.getItemAtPosition(position);
        ItemWearableType itemWearableType = entry.getKey();
        ItemWearableActiveHero item = entry.getValue();

        Toast.makeText(this.getActivity(), item.getName(), Toast.LENGTH_LONG).show();
    }
}
