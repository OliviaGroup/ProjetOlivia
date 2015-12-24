package com.principal.projetolivia.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.*;

import java.util.ArrayList;
import java.util.List;


public class GameFragment extends Fragment {
    private TextView question;
    private GridView gridGame;

    private TextView txtTimer;
    private TextView txtGoodAnswers;
    private TextView txtBadAnswers;

    private int goodAnswerScore;
    private int badAnswerScore;
    private int goodAnswerID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        txtTimer = (TextView) rootView.findViewById(R.id.txtTimer);
        txtGoodAnswers = (TextView) rootView.findViewById(R.id.txtGoodAnswers);
        txtBadAnswers = (TextView) rootView.findViewById(R.id.txtBadAnswers);

        goodAnswerScore = 0;
        badAnswerScore = 0;
        updateScore();


        question = (TextView) rootView.findViewById(R.id.question);
        gridGame = (GridView) rootView.findViewById(R.id.gridGame);

        refreshQuestions(rootView);

        gridGame.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelativeLayout layoutParent = (RelativeLayout) view;
                RelativeLayout lytRoundBackground = (RelativeLayout) layoutParent.findViewById(R.id.lytRoundBackground);

                if (position + 1 == goodAnswerID) {
                    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        lytRoundBackground.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_background_green));
                    } else {
                        lytRoundBackground.setBackground(getResources().getDrawable(R.drawable.round_background_green));
                    }
                    goodAnswerScore ++;
                    updateScore();

                    refreshQuestions(rootView);
                } else {
                    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        lytRoundBackground.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_background_red));
                    } else {
                        lytRoundBackground.setBackground(getResources().getDrawable(R.drawable.round_background_red));
                    }

                    badAnswerScore ++;
                    updateScore();
                }
            }
        });


        return rootView;
    }

    private void updateScore() {
        txtGoodAnswers.setText(getString(R.string.profile_rightAnswers) + " " + goodAnswerScore);
        txtBadAnswers.setText(getString(R.string.profile_wrongAnswers) + " " + badAnswerScore);
    }


    private void refreshQuestions(View view) {
        List<String> answersList = new ArrayList<>();
        answersList.clear();
        if (MainActivity.userList.get(MainActivity.currentUser).getSubjectList().get(MainActivity.currentSubject).getName() == SubjectName.mathematics) {
            MathGenerator mathGenerator = new MathGenerator();
            mathGenerator.calculationGenerator();
            question.setText(mathGenerator.getOperation());
            answersList = mathGenerator.getResults();
            goodAnswerID = mathGenerator.getGoodAnswer();
        } else {
            Question randomQuestion = MainActivity.getOneQuestionOnSubject(MainActivity.userList.get(MainActivity.currentUser).getSubjectList().get(MainActivity.currentSubject).getName());
            question.setText(randomQuestion.getQuestion());
            answersList = randomQuestion.getAnswers();
            goodAnswerID = randomQuestion.getGoodAnswer();
        }

        ItemGameAdapter adapter = new ItemGameAdapter(getActivity(), R.layout.item_grid_game, answersList);
        gridGame.setAdapter(adapter);
    }
}
