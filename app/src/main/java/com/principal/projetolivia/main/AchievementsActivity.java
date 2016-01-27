package com.principal.projetolivia.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.Achievement;
import com.principal.projetolivia.com.principal.projetolivia.util.CropImageView;
import com.principal.projetolivia.com.principal.projetolivia.util.User;

import java.util.List;

public class AchievementsActivity extends AppCompatActivity {
    private CropImageView achievementsBackground;
    private TextView profileNameTextAchievements;
    private TextView profileAgeTextAchievements;
    private GridView gridAchievements;

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.RemoveTheBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        currentUser = MainActivity.getCurrentUser();

        achievementsBackground = (CropImageView) findViewById(R.id.achievementsBackground);
        achievementsBackground.setOffset(1, 1);
        achievementsBackground.setImageDrawable(getResources().getDrawable(R.drawable.stats_olivia));

        profileNameTextAchievements = (TextView) findViewById(R.id.profileNameTextAchievements);
        profileNameTextAchievements.setText(currentUser.getName());

        profileAgeTextAchievements = (TextView) findViewById(R.id.profileAgeTextAchievements);
        profileAgeTextAchievements.setText(currentUser.getAge() + " " + getApplicationContext().getString(R.string.years_old));

        List<Achievement> test = MainActivity.achievementsList;

        gridAchievements = (GridView) findViewById(R.id.gridAchievements);
        final ItemAchievementsAdapter adapter = new ItemAchievementsAdapter(this, R.layout.item_grid_achievements, MainActivity.achievementsList);
        gridAchievements.setAdapter(adapter);
    }
}
