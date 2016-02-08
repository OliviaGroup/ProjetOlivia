package com.principal.projetolivia.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.MarginOlivia;

/**
 * Created by roosq on 19/01/2016.
 */
public class StoryActivity extends AppCompatActivity {

    private TextView userNameText;
    private TextView profileAgeText;
    private ButtonRectangle btnSwitchToQuiz;
    private ImageView imgOliviaStory;

    private final MarginOlivia marginPosition1 = new MarginOlivia(380, 430);
    private final MarginOlivia marginPosition2 = new MarginOlivia(370, 100);
    private final MarginOlivia marginPosition3 = new MarginOlivia(600, 180);
    private final MarginOlivia marginPosition4 = new MarginOlivia(890, 130);
    private final MarginOlivia marginPosition5 = new MarginOlivia(810, 280);
    private final MarginOlivia marginPosition6 = new MarginOlivia(760, 470);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.RemoveTheBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        imgOliviaStory = (ImageView) findViewById(R.id.imgOliviaStory);
        RelativeLayout.MarginLayoutParams mlp = new RelativeLayout.MarginLayoutParams(imgOliviaStory.getLayoutParams());
        mlp.setMargins(marginPosition6.getLeftMargin(), marginPosition6.getTopMargin(), 0, 0);
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
                Intent newActivity = new Intent(v.getContext(), AchievementsActivity.class);
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


