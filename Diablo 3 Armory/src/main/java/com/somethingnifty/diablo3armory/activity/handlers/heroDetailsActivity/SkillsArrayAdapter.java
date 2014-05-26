package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.ActiveSkill;
import com.somethingnifty.diablo3armory.domain.PassiveSkill;
import com.somethingnifty.diablo3armory.domain.Skill;
import com.somethingnifty.diablo3armory.helpers.ImageDownloader;

import java.util.List;

public class SkillsArrayAdapter extends ArrayAdapter<Skill>
{
    private final List<Skill> list;
    private final Activity context;
    private final ImageDownloader imageDownloader = new ImageDownloader();

    public SkillsArrayAdapter(Activity context, List<Skill> list) {
        super(context, R.layout.skill_row, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected ImageView skillIcon;
        protected TextView skillName;
        protected TextView runeName;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.skill_row, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.skillIcon = (ImageView) view.findViewById(R.id.skill_icon);
            viewHolder.skillName = (TextView) view.findViewById(R.id.skill_name);
            viewHolder.runeName = (TextView) view.findViewById(R.id.rune_name);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();

        if (list.get(position).getIcon() != null) {
            // String iconItemDomain = "http://media.blizzard.com/d3/icons/items/large/";
            String iconDomain = "http://media.blizzard.com/d3/icons/skills/64/";
            String icon = list.get(position).getIcon();
            String png = ".png";
            String iconUrl = iconDomain + icon + png;

            imageDownloader.download(iconUrl, holder.skillIcon);
        }

        String skillName = "";
        if (list.get(position).getName() != null) {
            skillName = list.get(position).getName();
        }
        holder.skillName.setText(skillName);

        if (list.get(position) instanceof ActiveSkill) {
            holder.runeName.setVisibility(View.VISIBLE);
            String runeName = "";
            if (((ActiveSkill) list.get(position)).getRune() != null) {
                if (((ActiveSkill) list.get(position)).getRune().getName() != null) {
                    runeName = ((ActiveSkill) list.get(position)).getRune().getName();
                }
            }
            holder.runeName.setText(runeName);
        }
        else if (list.get(position) instanceof PassiveSkill) {
            holder.runeName.setVisibility(View.GONE);
        }
        else {
            holder.runeName.setVisibility(View.GONE);
        }

        return view;
    }
}