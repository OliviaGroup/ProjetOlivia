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
 * Created by Marie on 17/12/2015.
 */
public class ItemSubjectDataAdapter extends ArrayAdapter<Subject> {
    private List<Subject> items;
    private int layoutResourceId;
    private Context context;

    public ItemSubjectDataAdapter(Context context, int layoutResourceId, List<Subject> items) {
        super(context, layoutResourceId, items);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.items = items;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemSubjectDataHolder holder;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder = new ItemSubjectDataHolder();
        holder.subject = items.get(position);
        holder.name = (TextView) row.findViewById(R.id.textNameSubject);
        holder.score = (com.github.lzyzsd.circleprogress.DonutProgress) row.findViewById(R.id.scoreSubject);
        holder.playedGames = (TextView) row.findViewById(R.id.textPlayedGamesSubject);
        holder.hiScore = (TextView) row.findViewById(R.id.textHiScoreSubject);
        holder.rightAnswers = (TextView) row.findViewById(R.id.textRightAnswersSubject);
        holder.wrongAnswers = (TextView) row.findViewById(R.id.textWrongAnswersSubject);
        holder.image = (ImageView) row.findViewById(R.id.imageOlivia);

        row.setTag(holder);

        setupItem(holder);
        return row;
    }

    private void setupItem(ItemSubjectDataHolder holder) {
        holder.name.setText(holder.subject.getName().getLabel(context));
        holder.score.setProgress(holder.subject.getPercentRightAnswers());
        holder.playedGames.setText(context.getString(R.string.profile_playedGames) + " " + holder.subject.getPlayedGames());
        holder.hiScore.setText(context.getString(R.string.profile_hiScore) + " " + holder.subject.getHiScore() + " pts");
        holder.rightAnswers.setText(context.getString(R.string.profile_rightAnswers) + " " + holder.subject.getRightAnswers());
        holder.wrongAnswers.setText(context.getString(R.string.profile_wrongAnswers) + " " + holder.subject.getWrongAnswers());
        holder.image.setImageResource(holder.subject.getName().getImageOliviaId(context));
    }

    public static class ItemSubjectDataHolder {
        Subject subject;
        TextView name;
        com.github.lzyzsd.circleprogress.DonutProgress score;
        TextView playedGames;
        TextView hiScore;
        TextView rightAnswers;
        TextView wrongAnswers;
        ImageView image;
    }
}
