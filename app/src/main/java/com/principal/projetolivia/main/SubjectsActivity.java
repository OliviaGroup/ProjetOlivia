package com.principal.projetolivia.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.CropImageView;

/**
 * Created by roosq on 19/01/2016.
 */
public class SubjectsActivity extends AppCompatActivity {

    private GridView gridViewSubjects;
    private TextView userNameText;
    private TextView profileAgeText;
    private ButtonRectangle btnSwitchToStory;
    private CropImageView subjectsBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.RemoveTheBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        subjectsBackground = (CropImageView) findViewById(R.id.subjectsBackground);
        subjectsBackground.setOffset(1, 1);
        subjectsBackground.setImageDrawable(getResources().getDrawable(R.drawable.subjects_background));

        gridViewSubjects = (GridView) findViewById(R.id.gridSubjects);
        gridViewSubjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.currentSubject = position;
                Intent newActivity = new Intent(view.getContext(), GameActivity.class);
                startActivity(newActivity);
            }
        });

        final ItemSubjectAdapter adapter = new ItemSubjectAdapter(this, R.layout.item_grid_subjects, MainActivity.getCurrentUser().getSubjectList());
        gridViewSubjects.setAdapter(adapter);

        userNameText = (TextView) findViewById(R.id.userNameText);
        userNameText.setText(MainActivity.getCurrentUser().getName());

        profileAgeText = (TextView) findViewById(R.id.profileAgeTextSubjects);
        profileAgeText.setText(MainActivity.getCurrentUser().getAge() + " " + getApplicationContext().getString(R.string.years_old));

        ButtonFloat userName = (ButtonFloat) findViewById(R.id.userName);
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(v.getContext(), StatsActivity.class);
                startActivity(newActivity);
            }
        });

        ButtonFloat achievementsButton = (ButtonFloat) findViewById(R.id.achievementsButton);
        achievementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(v.getContext(), AchievementsActivity.class);
                startActivity(newActivity);
            }
        });

        ButtonFloat buttonOptions = (ButtonFloat) findViewById(R.id.buttonOptions);
        buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction().add(R.id.containerSubjects, new OptionsFragment());
                ft.addToBackStack("SubjectsToOptions");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }
        });

        btnSwitchToStory = (ButtonRectangle) findViewById(R.id.btnSwitchToStory);
        btnSwitchToStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.getCurrentStory().getStage() != 0 ) {
                    Intent newActivity = new Intent(v.getContext(), StoryActivity.class);
                    startActivity(newActivity);
                } else {
                    Intent newActivity = new Intent(v.getContext(), ComicsActivity.class);
                    startActivity(newActivity);
                }
            }
        });
    }

    public void updateProfileData(){
        userNameText.setText(MainActivity.getCurrentUser().getName());
        profileAgeText.setText(MainActivity.getCurrentUser().getAge() + " " + getApplicationContext().getString(R.string.years_old));
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            Intent newActivity = new Intent(this.getBaseContext(), ProfileActivity.class);
            this.finish();
            startActivity(newActivity);
        } else{
            getSupportFragmentManager().popBackStack();
        }
        return;
    }

}
