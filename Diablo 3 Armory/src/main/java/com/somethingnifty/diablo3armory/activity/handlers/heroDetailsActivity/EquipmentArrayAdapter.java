package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.ItemWearableActiveHero;
import com.somethingnifty.diablo3armory.domain.enums.ItemWearableType;
import com.somethingnifty.diablo3armory.helpers.ImageDownloader;

import java.util.List;
import java.util.Map;

public class EquipmentArrayAdapter extends ArrayAdapter<Map.Entry<ItemWearableType, ItemWearableActiveHero>> {
    private final ImageDownloader imageDownloader = new ImageDownloader();

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

        ImageView icon = (ImageView) view.findViewById(R.id.equipment_row_icon);
        imageDownloader.download(getIconUrl(item), icon);
        icon.setBackgroundResource(item.getDisplayColor().getColorId());

//        ImageView backgroundImage = (ImageView) view.findViewById(R.id.equipment_row_icon_background);
//        imageDownloader.download("http://us.battle.net/d3/static/images/item/icon-bgs/" + item.getDisplayColor().value + ".png", backgroundImage);


        // String iconItemDomain = "http://media.blizzard.com/d3/icons/items/large/";

        return view;
    }

    private String getIconUrl(ItemWearableActiveHero item) {
        String iconDomain = "http://media.blizzard.com/d3/icons/items/large/";
        String icon = item.getIcon();
        String png = ".png";
        return iconDomain + icon + png;
    }
}
