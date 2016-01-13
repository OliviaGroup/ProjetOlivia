package com.principal.projetolivia.main;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.MathGenerator;
import com.principal.projetolivia.com.principal.projetolivia.util.Question;
import com.principal.projetolivia.com.principal.projetolivia.util.Subject;
import com.principal.projetolivia.com.principal.projetolivia.util.SubjectName;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class GameFragment extends Fragment {
    private TextView question;
    private GridView gridGame;

    private TextView txtTimer;
    private TextView txtGoodAnswers;
    private TextView txtBadAnswers;
    private ButtonRectangle btnStopGame;
    private CircleProgress prgTimer;
    private ImageView imgOlivia;

    private int goodAnswerScore;
    private int badAnswerScore;
    private int goodAnswerID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        MainActivity.changeBackground(MainActivity.getCurrentSubject().getName().getImageQuestionId(getContext()));

        txtTimer = (TextView) rootView.findViewById(R.id.txtTimer);
        txtGoodAnswers = (TextView) rootView.findViewById(R.id.txtGoodAnswers);
        txtBadAnswers = (TextView) rootView.findViewById(R.id.txtBadAnswers);
        btnStopGame = (ButtonRectangle) rootView.findViewById(R.id.btnStopGame);
        prgTimer = (CircleProgress) rootView.findViewById(R.id.prgTimer);
        imgOlivia = (ImageView) rootView.findViewById(R.id.imgOlivia);


        goodAnswerScore = 0;
        badAnswerScore = 0;
        updateScore();

        final CountTimer timer = new CountTimer(60000, 1000);
        timer.start();


        question = (TextView) rootView.findViewById(R.id.question);
        gridGame = (GridView) rootView.findViewById(R.id.gridGame);

        refreshQuestions(rootView);

        imgOlivia.setImageDrawable(getResources().getDrawable(MainActivity.getCurrentSubject().getName().getImageOliviaId(getContext())));

        btnStopGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTheGame();
            }
        });

        gridGame.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelativeLayout layoutParent = (RelativeLayout) view;
                RelativeLayout lytRoundBackground = (RelativeLayout) layoutParent.findViewById(R.id.lytRoundBackground);

                if (position + 1 == goodAnswerID) {

                    goodAnswerScore++;
                    updateScore();

                    refreshQuestions(rootView);
                } else {
                    if (!lytRoundBackground.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.round_background_red).getConstantState())) {
                        badAnswerScore++;
                    }
                    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        lytRoundBackground.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_background_red));
                    } else {
                        lytRoundBackground.setBackground(getResources().getDrawable(R.drawable.round_background_red));
                    }


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

    private void stopTheGame () {
        Subject currentSubject = MainActivity.getCurrentSubject();
        currentSubject.setRightAnswers(currentSubject.getRightAnswers() + goodAnswerScore);
        currentSubject.setWrongAnswers(currentSubject.getWrongAnswers() + badAnswerScore);
        currentSubject.setPlayedGames(currentSubject.getPlayedGames() + goodAnswerScore + badAnswerScore);

        MainActivity.fileConnector.setProfileList(getContext(), MainActivity.userList);

        FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container, new SubjectsFragment());
        ft.addToBackStack(getTag());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }


    private void refreshQuestions(View view) {
        List<String> answersList = new ArrayList<>();
        answersList.clear();
        if (MainActivity.getCurrentSubject().getName() == SubjectName.mathematics) {
            MathGenerator mathGenerator = new MathGenerator();
            mathGenerator.calculationGenerator();
            question.setText(mathGenerator.getOperation());
            answersList = mathGenerator.getResults();
            goodAnswerID = mathGenerator.getGoodAnswer();
        } else {
            Question randomQuestion = MainActivity.getOneQuestionOnSubject(MainActivity.getCurrentSubject().getName());
            question.setText(randomQuestion.getQuestion());
            answersList = randomQuestion.getAnswers();
            goodAnswerID = randomQuestion.getGoodAnswer();
        }

        ItemGameAdapter adapter = new ItemGameAdapter(getActivity(), R.layout.item_grid_game, answersList);
        gridGame.setAdapter(adapter);
    }

    public class CountTimer extends CountDownTimer {

        public CountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int timer = (int) TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
            prgTimer.setProgress(timer);
        }

        @Override
        public void onFinish() {
            stopTheGame();
        }
    }
}

