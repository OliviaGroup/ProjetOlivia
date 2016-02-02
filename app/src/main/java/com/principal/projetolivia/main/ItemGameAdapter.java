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
import com.principal.projetolivia.com.principal.projetolivia.util.GameAnswer;
import com.principal.projetolivia.com.principal.projetolivia.util.Subject;

import java.util.List;

/**
 * Created by roosq on 17/12/2015.
 */
public class ItemGameAdapter extends ArrayAdapter<GameAnswer> {
    private List<GameAnswer> items;
    private int layoutResourceId;
    private Context context;

    public ItemGameAdapter(Context context, int layoutResourceId, List<GameAnswer> items) {
        super(context, layoutResourceId, items);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemSubjectHolder holder;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder = new ItemSubjectHolder();
        holder.answer = items.get(position).getAnswer();
        holder.textAnswer = (TextView) row.findViewById(R.id.textAnswer);
        row.setTag(holder);
        setupItem(holder);
        return row;
    }

    private void setupItem(ItemSubjectHolder holder) {
        holder.textAnswer.setText(holder.answer);
    }

    public static class ItemSubjectHolder {
        String answer;
        TextView textAnswer;
    }
}
