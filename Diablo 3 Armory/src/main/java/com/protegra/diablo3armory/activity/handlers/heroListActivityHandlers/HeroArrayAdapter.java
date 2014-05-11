package com.protegra.diablo3armory.activity.handlers.heroListActivityHandlers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.protegra.diablo3armory.R;
import com.protegra.diablo3armory.domain.ActiveHero;
import com.protegra.diablo3armory.domain.enums.Gender;
import com.protegra.diablo3armory.domain.enums.HeroGenderType;
import com.protegra.diablo3armory.domain.enums.HeroType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HeroArrayAdapter extends ArrayAdapter<ActiveHero>
{
    private final List<ActiveHero> list;
    private final Activity context;

    public HeroArrayAdapter(Activity context, List<ActiveHero> list)
    {
        super(context, R.layout.hero_row, list);
        this.context = context;

        // sort by level then last updated
        Collections.sort(list, new Comparator<ActiveHero>() {
            @Override
            public int compare(final ActiveHero hero1, final ActiveHero hero2) {
                int value1 = Integer.compare(hero2.getLevel(), hero1.getLevel());
                if (value1 == 0) {
                    return hero2.getLastUpdated().compareTo(hero1.getLastUpdated());
                }
                return value1;
            }
        });

        this.list = list;
    }

    static class ViewHolder {
        protected ImageView heroClassIcon;
        protected TextView heroName;
        protected TextView heroLevelAndClass;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.hero_row, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.heroClassIcon = (ImageView) view.findViewById(R.id.hero_class_icon);
            viewHolder.heroName = (TextView) view.findViewById(R.id.hero_name);
            viewHolder.heroLevelAndClass = (TextView) view.findViewById(R.id.hero_level_and_class);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.heroClassIcon.setImageResource(getHeroClassIcon(position));

        String name = list.get(position).getName();
        holder.heroName.setText(name);
        holder.heroLevelAndClass.setText(getHeroLevelAndClass(position));

        return view;
    }

    private String getHeroLevelAndClass(int position) {
        StringBuilder levelAndClass = new StringBuilder("Level ")
                .append(list.get(position).getLevel())
                .append(" - ")
                .append(list.get(position).getHeroType().getToString());

        return levelAndClass.toString();
    }

    private int getHeroClassIcon(int position) {
        HeroType heroType = list.get(position).getHeroType();
        Gender heroGender = list.get(position).getGender();
        String resourceName = HeroGenderType.getValue(heroType, heroGender);

        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }
}
