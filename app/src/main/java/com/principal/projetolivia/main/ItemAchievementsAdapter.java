package com.principal.projetolivia.main;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.Achievement;

import java.util.List;

/**
 * Created by Marie on 21/01/2016.
 */
public class ItemAchievementsAdapter extends ArrayAdapter<Achievement> {
    private List<Achievement> items;
    private int layoutResourceId;
    private Context context;

    public ItemAchievementsAdapter(Context context, int layoutResourceId, List<Achievement> items) {
        super(context, layoutResourceId, items);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemAchievementsHolder holder;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder = new ItemAchievementsHolder();
        holder.achievement = items.get(position);
        holder.title = (TextView) row.findViewById(R.id.achievementTitle);
        holder.description = (TextView) row.findViewById(R.id.achievementDescription);
        holder.image = (ImageView) row.findViewById(R.id.achievementImage);

        row.setTag(holder);

        setupItem(holder);
        return row;
    }

    private void setupItem(ItemAchievementsHolder holder) {
        holder.title.setText(holder.achievement.getTitle());
        holder.description.setText(holder.achievement.getDescription());
        holder.image.setImageResource(holder.achievement.getImageResourceId(context));
    }

    public static class ItemAchievementsHolder {
        Achievement achievement;
        ImageView image;
        TextView title;
        TextView description;
        com.github.lzyzsd.circleprogress.DonutProgress progress;
    }
}
