package com.principal.projetolivia.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.MarginOlivia;
import com.principal.projetolivia.com.principal.projetolivia.util.SubjectEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.UserStory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

/**
 * Created by roosq on 19/01/2016.
 */
public class StoryActivity extends AppCompatActivity {

    private TextView userNameText;
    private TextView profileAgeText;
    private ButtonRectangle btnSwitchToQuiz;
    private ImageView imgOliviaStory;
    private List<ImageView> imgMedalStoryList;
    private List<MarginOlivia> marginOliviaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.RemoveTheBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        imgMedalStoryList = new ArrayList<>();
        marginOliviaList = new ArrayList<>();

        marginOliviaList.add(new MarginOlivia(560, 660));
        marginOliviaList.add(new MarginOlivia(550, 170));
        marginOliviaList.add(new MarginOlivia(910, 270));
        marginOliviaList.add(new MarginOlivia(1340, 200));
        marginOliviaList.add(new MarginOlivia(1210, 430));
        marginOliviaList.add(new MarginOlivia(1140, 720));

        for (int i = 0; i < SubjectEnum.values().length; i++) {
            imgMedalStoryList.add((ImageView) findViewById(getResources().getIdentifier("imgMedalStory" + i, "id", getPackageName())));
        }

        UserStory userStory = MainActivity.getCurrentStory();

        for (int i = 0; i < imgMedalStoryList.size(); i++) {
            imgMedalStoryList.get(i).setImageDrawable(getResources().getDrawable(getResources().getIdentifier("story_" + userStory.getMedalLevelList().get(i).name(), "drawable", getPackageName())));
            RelativeLayout.MarginLayoutParams mlp = new RelativeLayout.MarginLayoutParams(imgMedalStoryList.get(i).getLayoutParams());
            MarginOlivia marginOlivia = marginOliviaList.get(i);
            mlp.setMargins(marginOlivia.getLeftMargin(), marginOlivia.getTopMargin(), 0, 0);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(mlp);
            imgMedalStoryList.get(i).setLayoutParams(lp);
        }

        MarginOlivia marginOlivia = marginOliviaList.get(userStory.getStage());

        imgOliviaStory = (ImageView) findViewById(R.id.imgOliviaStory);
        RelativeLayout.MarginLayoutParams mlp = new RelativeLayout.MarginLayoutParams(imgOliviaStory.getLayoutParams());
        mlp.setMargins(marginOlivia.getLeftMargin(), marginOlivia.getTopMargin(), 0, 0);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(mlp);
        imgOliviaStory.setLayoutParams(lp);


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

        btnSwitchToQuiz = (ButtonRectangle) findViewById(R.id.btnSwitchToQuiz);
        btnSwitchToQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(v.getContext(), SubjectsActivity.class);
                startActivity(newActivity);
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


