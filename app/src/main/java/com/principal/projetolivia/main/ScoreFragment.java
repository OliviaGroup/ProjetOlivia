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
import com.principal.projetolivia.com.principal.projetolivia.util.AchievementTypeEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.GameResultEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.ImproveScoreEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.Subject;
import com.principal.projetolivia.com.principal.projetolivia.util.SubjectEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.UserAchievement;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {

    private int goodAnswerScore;
    private int badAnswerScore;
    private int oldPercentScore;
    private int oldHiScore;

    private GameResultEnum currentGameResult;
    private ImproveScoreEnum currentImproveScore;
    private Subject currentSubject;

    private TextView txtScoreTitle;
    private TextView txtHiScore;
    private ImageView imgOliviaScore;
    private TextView txtGoodAnswersScore;
    private TextView txtBadAnswersScore;
    private TextView txtOldPercent;
    private ImageView imgArrow;
    private TextView txtNewPercent;
    private ButtonFloat btnGoToSubjects;
    private ButtonFloat btnGoToRetry;

    public ScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        goodAnswerScore = ((GameActivity)getActivity()).getGoodAnswerScore();
        badAnswerScore = ((GameActivity)getActivity()).getBadAnswerScore();

        currentSubject = MainActivity.getCurrentSubject();
        oldPercentScore = currentSubject.getPercentRightAnswers();
        oldHiScore = currentSubject.getHiScore();

        txtScoreTitle = (TextView) rootView.findViewById(R.id.txtScoreTitle);
        txtHiScore = (TextView) rootView.findViewById(R.id.txtHiScore);
        imgOliviaScore = (ImageView) rootView.findViewById(R.id.imgOliviaScore);
        txtGoodAnswersScore = (TextView) rootView.findViewById(R.id.txtGoodAnswersScore);
        txtBadAnswersScore = (TextView) rootView.findViewById(R.id.txtBadAnswersScore);
        txtOldPercent = (TextView) rootView.findViewById(R.id.txtOldPercent);
        imgArrow = (ImageView) rootView.findViewById(R.id.imgArrow);
        txtNewPercent = (TextView) rootView.findViewById(R.id.txtNewPercent);
        btnGoToSubjects = (ButtonFloat) rootView.findViewById(R.id.btnGoToSubjects);
        btnGoToRetry = (ButtonFloat) rootView.findViewById(R.id.btnGoToRetry);

        txtGoodAnswersScore.setText(getString(R.string.profile_rightAnswers) + " " + goodAnswerScore);
        txtBadAnswersScore.setText(getString(R.string.profile_wrongAnswers) + " " + badAnswerScore);
        txtHiScore.setText(getString(R.string.profile_hiScore) + " " + oldHiScore);

        if (oldHiScore < goodAnswerScore) {
            currentGameResult = GameResultEnum.hi_score;
            currentSubject.setTimesBeatHiScore(currentSubject.getTimesBeatHiScore() + 1);
            currentSubject.setHiScore(goodAnswerScore);
        } else {
            if (badAnswerScore <= goodAnswerScore) {
                currentGameResult = GameResultEnum.win;
            } else {
                currentGameResult = GameResultEnum.loose;
            }
        }

        txtScoreTitle.setText(currentGameResult.getLabel(getContext()));
        imgOliviaScore.setImageDrawable(getResources().getDrawable(currentGameResult.getImageOliviaId(getContext())));


        txtOldPercent.setText(oldPercentScore + getString(R.string.min_percent));

        currentSubject.setRightAnswers(currentSubject.getRightAnswers() + goodAnswerScore);
        currentSubject.setWrongAnswers(currentSubject.getWrongAnswers() + badAnswerScore);
        currentSubject.setPlayedGames(currentSubject.getPlayedGames() + 1);


        txtNewPercent.setText(currentSubject.getPercentRightAnswers() + getString(R.string.min_percent));

        int currentDifferencePercent = currentSubject.getPercentRightAnswers() - oldPercentScore;

        if (currentDifferencePercent < 0) {
            currentImproveScore = ImproveScoreEnum.worse;
        } else if (currentDifferencePercent == 0) {
            currentImproveScore = ImproveScoreEnum.equal;
        } else if (currentDifferencePercent > 0) {
            currentImproveScore = ImproveScoreEnum.better;
        }

        imgArrow.setImageDrawable(getResources().getDrawable(currentImproveScore.getImageArrowId(getContext())));

        MainActivity.fileConnector.setProfileList(getContext(), MainActivity.userList);

        btnGoToRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(v.getContext(), GameActivity.class);
                startActivity(newActivity);
            }
        });

        btnGoToSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(v.getContext(), SubjectsActivity.class);
                startActivity(newActivity);
            }
        });

        updateAchievements();

        return rootView;
    }

    private void updateAchievements() {
        List<UserAchievement> userAchievementList = MainActivity.getCurrentUser().getUserAchievementList();

        for (UserAchievement userAchievement :
                userAchievementList) {
            if (userAchievement.isAchievementReceived() == false) {
                Achievement achievement = MainActivity.achievementsList.get(userAchievement.getId() - 1);

                if (currentSubject.getName() == achievement.getSubject()) {
                    int trueObjective = achievement.getObjective();

                    int value = 0;
                    switch (achievement.getType()) {
                        case played:
                            value = currentSubject.getPlayedGames();
                            break;
                        case highscore:
                            value = currentSubject.getTimesBeatHiScore();
                            break;
                        case nofault:
                            value = currentSubject.getPlayedGamesNoFault();
                            break;
                        case percent:
                            value = currentSubject.getPercentRightAnswers();
                            break;
                        case easteregg:
                            value = -1;
                            break;
                    }

                    if (value >= trueObjective) {
                        userAchievement.setAchievementReceived(true);
                    } else {
                        userAchievement.setValue(value);
                        if (trueObjective == 0) {
                            userAchievement.setPercent(0);
                        } else {
                            userAchievement.setPercent(value, trueObjective);
                        }
                    }
                }
            }
        }
        MainActivity.fileConnector.setProfileList(getContext(), MainActivity.userList);
    }
}
