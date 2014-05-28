package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.ItemWearableEquippable;
import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;
import com.somethingnifty.diablo3armory.helpers.ImageDownloader;

import java.util.List;
import java.util.Map;

public class EquipmentArrayAdapter extends ArrayAdapter<Map.Entry<ItemWearableType, ItemWearableEquippable>> {
    private final ImageDownloader imageDownloader = new ImageDownloader();

    private List<Map.Entry<ItemWearableType, ItemWearableEquippable>> items;
    private Activity context;

    public EquipmentArrayAdapter(Activity context, List<Map.Entry<ItemWearableType, ItemWearableEquippable>> items) {
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

        Map.Entry<ItemWearableType, ItemWearableEquippable> entry = items.get(position);
        ItemWearableType itemWearableType = entry.getKey();
        ItemWearableEquippable item = entry.getValue();

        TextView textView = (TextView) view.findViewById(R.id.item_type);
        textView.setText(itemWearableType.getToString());

        if (entry.getValue() != null) {
            textView = (TextView) view.findViewById(R.id.item_name);
            textView.setText(item.getName());

            ImageView icon = (ImageView) view.findViewById(R.id.equipment_row_icon);
            imageDownloader.download(getIconUrl(item), icon);
            icon.setBackgroundResource(item.getDisplayColor().getColorId());
        }
        else {
            textView = (TextView) view.findViewById(R.id.item_name);
            textView.setText(context.getResources().getString(R.string.no_item_equipped));
        }

        return view;
    }

    private String getIconUrl(ItemWearableEquippable item) {
        String iconDomain = "http://media.blizzard.com/d3/icons/items/large/";
        String icon = item.getIcon();
        String png = ".png";
        return iconDomain + icon + png;
    }
}
