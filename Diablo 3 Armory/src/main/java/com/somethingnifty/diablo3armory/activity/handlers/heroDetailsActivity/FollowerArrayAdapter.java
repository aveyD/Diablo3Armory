package com.somethingnifty.diablo3armory.activity.handlers.heroDetailsActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.somethingnifty.diablo3armory.R;
import com.somethingnifty.diablo3armory.domain.FollowerAndType;
import com.somethingnifty.diablo3armory.domain.enums.FollowerType;

import java.util.List;

public class FollowerArrayAdapter extends ArrayAdapter<FollowerAndType>
{
    private final List<FollowerAndType> list;
    private final Activity context;

    public FollowerArrayAdapter(Activity context, List<FollowerAndType> list) {
        super(context, R.layout.follower_row, list);
        this.context = context;

        if (list.isEmpty()) {
            list.add(new FollowerAndType(null, null));
        }

        this.list = list;
    }

    static class ViewHolder {
        protected ImageView followerTypeIcon;
        protected TextView followerType;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.follower_row, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.followerTypeIcon = (ImageView) view.findViewById(R.id.follower_type_icon);
            viewHolder.followerType = (TextView) view.findViewById(R.id.follower_type);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();

        if (list.get(position).getFollowerType() != null) {
            holder.followerTypeIcon.setImageResource(getFollowerTypeIcon(position));
        }

        String type;
        if (list.get(position).getFollowerType() == null) {
            type = "No Followers Found";
        }
        else {
            type = list.get(position).getFollowerType().toString();
        }
        holder.followerType.setText(type);

        return view;
    }

    private int getFollowerTypeIcon(int position) {
        FollowerType followerType = list.get(position).getFollowerType();

        String resourceName = "ic_" + followerType.getValue();

        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }
}