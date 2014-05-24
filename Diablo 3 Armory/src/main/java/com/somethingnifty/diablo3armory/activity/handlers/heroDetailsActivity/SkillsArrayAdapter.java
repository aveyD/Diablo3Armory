package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
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

import java.io.InputStream;
import java.util.List;

public class SkillsArrayAdapter extends ArrayAdapter<Skill>
{
    private final List<Skill> list;
    private final Activity context;

    public SkillsArrayAdapter(Activity context, List<Skill> list) {
        super(context, R.layout.follower_row, list);
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

            new DownloadImageTask(holder.skillIcon).execute(iconUrl);
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

        return view;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
    {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}