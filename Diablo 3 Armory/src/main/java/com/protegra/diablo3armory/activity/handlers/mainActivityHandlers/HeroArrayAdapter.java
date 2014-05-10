package com.protegra.diablo3armory.activity.handlers.mainActivityHandlers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.protegra.diablo3armory.R;
import com.protegra.diablo3armory.activity.handlers.EventHandler;
import com.protegra.diablo3armory.domain.ActiveHero;
import com.protegra.diablo3armory.domain.Gender;
import com.protegra.diablo3armory.domain.enums.HeroType;

import java.util.List;

public class HeroArrayAdapter extends ArrayAdapter<ActiveHero>
{
    private final List<ActiveHero> list;
    private final Activity context;

    public HeroArrayAdapter(Activity context, List<ActiveHero> list) {
        super(context, R.layout.hero_row, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected ImageView heroClassIcon;
        protected TextView heroName;
        protected TextView heroLevelAndClass;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
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
        holder.heroName.setText(list.get(position).getName());
        holder.heroLevelAndClass.setText(getHeroLevelAndClass(position));
        return view;
    }

    private String getHeroLevelAndClass(int position) {
        StringBuilder levelAndClass = new StringBuilder("Level ");
        levelAndClass.append(list.get(position).getLevel())
                .append(" - ")
                .append(list.get(position).getHeroType().getValue());
        return levelAndClass.toString();
    }

    private int getHeroClassIcon(int position) {
        HeroType heroType = list.get(position).getHeroType();
        Gender heroGender = list.get(position).getGender();

        int id = 0;

        switch (heroType) {
            case BARBARIAN:
                if (heroGender.equals(Gender.MALE)) {
                    id = R.drawable.barb_male;
                }
                else if (heroGender.equals(Gender.FEMALE)) {
                    id = R.drawable.barb_female;
                }
                break;
            case CRUSADER:
                if (heroGender.equals(Gender.MALE)) {
                    id = R.drawable.crusader_male;
                }
                else if (heroGender.equals(Gender.FEMALE)) {
                    id = R.drawable.crusader_female;
                }
                break;
            case DEMON_HUNTER:
                if (heroGender.equals(Gender.MALE)) {
                    id = R.drawable.demon_hunter_male;
                }
                else if (heroGender.equals(Gender.FEMALE)) {
                    id = R.drawable.demon_hunter_female;
                }
                break;
            case MONK:
                if (heroGender.equals(Gender.MALE)) {
                    id = R.drawable.monk_male;
                }
                else if (heroGender.equals(Gender.FEMALE)) {
                    id = R.drawable.monk_female;
                }
                break;
            case WITCH_DOCTOR:
                if (heroGender.equals(Gender.MALE)) {
                    id = R.drawable.witch_doctor_male;
                }
                else if (heroGender.equals(Gender.FEMALE)) {
                    id = R.drawable.witch_doctor_female;
                }
                break;
            case WIZARD:
                if (heroGender.equals(Gender.MALE)) {
                    id = R.drawable.wizard_male;
                }
                else if (heroGender.equals(Gender.FEMALE)) {
                    id = R.drawable.wizard_female;
                }
                break;
            default:
                id = R.drawable.ic_launcher;
                break;
        }
        return id;
    }
}
