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

import com.principal.projetolivia.com.principal.projetolivia.util.Subject;

import java.util.List;

/**
 * Created by roosq on 09/12/2015.
 */
public class ItemSubjectAdapter extends ArrayAdapter<Subject> {
    private List<Subject> items;
    private int layoutResourceId;
    private Context context;

    public ItemSubjectAdapter(Context context, int layoutResourceId, List<Subject> items) {
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
        holder.subject = items.get(position);
        holder.name = (TextView) row.findViewById(R.id.textNameSubject);
        holder.score = (TextView) row.findViewById(R.id.textScoreSubject);
        holder.image = (ImageView) row.findViewById(R.id.imageSubject);

        row.setTag(holder);

        setupItem(holder);
        return row;
    }

    private void setupItem(ItemSubjectHolder holder) {
        holder.name.setText(holder.subject.getName().getLabel(context));
        holder.score.setText(context.getString(R.string.profile_score) + " " + holder.subject.getPercentRightAnswers() + " %");
        holder.image.setImageResource(holder.subject.getName().getImageResourceId(context));
    }

    public static class ItemSubjectHolder {
        Subject subject;
        TextView name;
        TextView score;
        ImageView image;
    }
}
