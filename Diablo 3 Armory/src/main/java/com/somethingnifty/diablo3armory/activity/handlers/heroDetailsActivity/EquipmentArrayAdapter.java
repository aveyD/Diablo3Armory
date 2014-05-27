package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.ItemWearableActiveHero;
import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;

import java.util.List;
import java.util.Map;

public class EquipmentArrayAdapter extends ArrayAdapter<Map.Entry<ItemWearableType, ItemWearableActiveHero>> {

    private List<Map.Entry<ItemWearableType, ItemWearableActiveHero>> items;
    private Activity context;

    public EquipmentArrayAdapter(Activity context, List<Map.Entry<ItemWearableType, ItemWearableActiveHero>> items) {
        super(context, R.layout.equipment_row, items);

        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.equipment_row, null);
        } else {
            view = convertView;
        }

        Map.Entry<ItemWearableType, ItemWearableActiveHero> entry = items.get(position);
        ItemWearableType itemWearableType = entry.getKey();
        ItemWearableActiveHero item = entry.getValue();

        TextView textView = (TextView) view.findViewById(R.id.item_name);
        textView.setText(item.getName());

        textView = (TextView) view.findViewById(R.id.item_type);
        textView.setText(itemWearableType.getToString());

        textView = (TextView) view.findViewById(R.id.item_description);
        textView.setText("temporary description");

        return view;
    }
}
