package com.principal.projetolivia.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.Achievement;
import com.principal.projetolivia.com.principal.projetolivia.util.GameResultEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.ImproveScoreEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.MedalLevelEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.Subject;
import com.principal.projetolivia.com.principal.projetolivia.util.SubjectEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.UserAchievement;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryFragment extends Fragment {

    private int goodAnswerScore;
    private int badAnswerScore;

    private TextView txtStoryTitle;
    private ImageView imgStoryMedal;
    private ButtonFloat btnGoToStory;


    public StoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_story, container, false);

        goodAnswerScore = ((GameActivity) getActivity()).getGoodAnswerScore();
        badAnswerScore = ((GameActivity) getActivity()).getBadAnswerScore();

        imgStoryMedal = (ImageView) rootView.findViewById(R.id.imgStoryMedal);

        MedalLevelEnum medalLevel;

        if (goodAnswerScore >= 10 && badAnswerScore == 0) {
            medalLevel = MedalLevelEnum.gold;
        } else if (10 > goodAnswerScore && goodAnswerScore >= 5 && badAnswerScore == 0) {
            medalLevel = MedalLevelEnum.silver;
        } else {
            medalLevel = MedalLevelEnum.bronze;
        }

        imgStoryMedal.setImageDrawable(rootView.getResources().getDrawable(rootView.getResources().getIdentifier("story_" + MainActivity.getCurrentSubject().getName().name() + "_" + medalLevel.name(), "drawable", rootView.getContext().getPackageName())));

        int currentSubjectInt = 0;

        for (int i = 0; i < SubjectEnum.values().length; i++) {
            if (SubjectEnum.values()[i] == MainActivity.getCurrentSubject().getName()) {
                currentSubjectInt = i;
            }
        }

        MedalLevelEnum currentMedalLevel = MainActivity.getCurrentStory().getMedalLevelList().get(currentSubjectInt);

        if ((currentMedalLevel == MedalLevelEnum.none) || (currentMedalLevel == MedalLevelEnum.bronze && (medalLevel == MedalLevelEnum.silver || medalLevel == MedalLevelEnum.gold) || (currentMedalLevel == MedalLevelEnum.silver && medalLevel == MedalLevelEnum.gold))) {
            MainActivity.getCurrentStory().getMedalLevelList().set(currentSubjectInt, medalLevel);
        }

        if (MainActivity.getCurrentStory().getStage() == currentSubjectInt && MainActivity.getCurrentStory().getStage() != SubjectEnum.values().length) {
            MainActivity.getCurrentStory().setStage(MainActivity.getCurrentStory().getStage() + 1);
        }

        MainActivity.fileConnector.setProfileList(rootView.getContext(), MainActivity.userList);

        btnGoToStory = (ButtonFloat) rootView.findViewById(R.id.btnGoToStory);
        btnGoToStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(v.getContext(), StoryActivity.class);
                startActivity(newActivity);
            }
        });

        return rootView;
    }


}
