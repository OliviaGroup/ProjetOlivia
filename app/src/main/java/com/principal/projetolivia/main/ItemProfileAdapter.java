package com.principal.projetolivia.main;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.principal.projetolivia.R;

import com.principal.projetolivia.com.principal.projetolivia.util.user;

import java.util.List;

/**
 * Created by roosq on 01/12/2015.
 */
public class ItemProfileAdapter extends ArrayAdapter<user>{

    private List<user> items;
    private int layoutRessourceId;
    private Context context;

    public ItemProfileAdapter(Context context, int layoutResourceId, List<user> items) {
        super(context, layoutResourceId, items);
        this.layoutRessourceId = layoutResourceId;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemProfileHolder holder = null;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutRessourceId, parent, false);

        holder = new ItemProfileHolder();
        holder.user = items.get(position);
        holder.name = (TextView)row.findViewById(R.id.textName);
        holder.age = (TextView)row.findViewById(R.id.textAge);
        holder.point = (TextView)row.findViewById(R.id.textPoint);

        row.setTag(holder);

        setupItem(holder);
        return row;
    }

    private void setupItem(ItemProfileHolder holder) {
        holder.name.setText(holder.user.getName());
        holder.age.setText(R.string.profile_age + " " + holder.user.getAge());
        // TODO : Change value
        holder.age.setText(R.string.profile_score + " TODO " + 0);
    }

    public static class ItemProfileHolder {
        user user;
        TextView name;
        TextView age;
        TextView point;
    }
}


