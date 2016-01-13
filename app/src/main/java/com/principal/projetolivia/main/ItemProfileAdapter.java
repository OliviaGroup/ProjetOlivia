package com.principal.projetolivia.main;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.User;

import java.util.List;

/**
 * Created by roosq on 01/12/2015.
 */
public class ItemProfileAdapter extends ArrayAdapter<User>{

    private List<User> items;
    private int layoutResourceId;
    private Context context;

    public ItemProfileAdapter(Context context, int layoutResourceId, List<User> items) {
        super(context, layoutResourceId, items);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemProfileHolder holder;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder = new ItemProfileHolder();
        holder.user = items.get(position);
        holder.name = (TextView)row.findViewById(R.id.textNameProfile);
        holder.age = (TextView)row.findViewById(R.id.textAgeProfile);
        holder.score = (TextView)row.findViewById(R.id.textScoreProfile);

        row.setTag(holder);

        setupItem(holder);
        return row;
    }

    private void setupItem(ItemProfileHolder holder) {
        holder.name.setText(holder.user.getName());
        holder.age.setText(context.getString(R.string.profile_age) + " " + holder.user.getAge());
        holder.score.setText(context.getString(R.string.profile_score) + " " + holder.user.getTotalPercentRightAnswers() + " %");
    }

    public static class ItemProfileHolder {
        User user;
        TextView name;
        TextView age;
        TextView score;
    }
}


