package com.principal.projetolivia.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.CropImageView;
import com.principal.projetolivia.com.principal.projetolivia.util.Subject;
import com.principal.projetolivia.com.principal.projetolivia.util.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by roosq on 19/01/2016.
 */
public class StatsActivity extends AppCompatActivity {
    private GridView gridProfile;
    private TextView profileNameText;
    private CropImageView profileBackground;

    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.RemoveTheBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        profileBackground = (CropImageView) findViewById(R.id.profileBackground);
        profileBackground.setOffset(1, 1);
        profileBackground.setImageDrawable(getResources().getDrawable(R.drawable.stats_olivia));

        User currentUser = MainActivity.getCurrentUser();

        profileNameText = (TextView) findViewById(R.id.profileNameText);
        profileNameText.setText(currentUser.getName());

        TextView profileAgeText = (TextView) findViewById(R.id.profileAgeText);
        profileAgeText.setText(currentUser.getAge() + " ans");

        gridProfile = (GridView) findViewById(R.id.gridProfile);

        List<Subject> subjectsListByScoreOrder = new ArrayList<Subject>();
        for (Subject subject : currentUser.getSubjectList()) {
            subjectsListByScoreOrder.add(subject);
        }

        Collections.sort(subjectsListByScoreOrder);
        final ItemSubjectDataAdapter adapter = new ItemSubjectDataAdapter(this, R.layout.item_grid_subject_data, subjectsListByScoreOrder);
        gridProfile.setAdapter(adapter);
    }
}
