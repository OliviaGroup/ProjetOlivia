package com.principal.projetolivia.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.CircleProgress;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.CropImageView;
import com.principal.projetolivia.com.principal.projetolivia.util.MathGenerator;
import com.principal.projetolivia.com.principal.projetolivia.util.Question;
import com.principal.projetolivia.com.principal.projetolivia.util.SubjectEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by roosq on 20/01/2016.
 */
public class GameActivity extends AppCompatActivity {
    private TextView question;
    private GridView gridGame;
    private CropImageView gameBackground;

    private TextView txtGoodAnswers;
    private TextView txtBadAnswers;
    private CircleProgress prgTimer;
    private ImageView imgOlivia;

    private int goodAnswerScore;
    private int badAnswerScore;
    private int goodAnswerID;

    private CountTimer timer;

    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.RemoveTheBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameBackground = (CropImageView) findViewById(R.id.gameBackground);
        gameBackground.setOffset(1, 1);
        gameBackground.setImageDrawable(getResources().getDrawable(MainActivity.getCurrentSubject().getName().getImageQuestionId(this)));

        txtGoodAnswers = (TextView) findViewById(R.id.txtGoodAnswers);
        txtBadAnswers = (TextView) findViewById(R.id.txtBadAnswers);
        prgTimer = (CircleProgress) findViewById(R.id.prgTimer);
        imgOlivia = (ImageView) findViewById(R.id.imgOlivia);


        goodAnswerScore = 0;
        badAnswerScore = 0;
        updateScore();

        timer = new CountTimer(20000, 1000);
        timer.start();


        question = (TextView) findViewById(R.id.question);
        gridGame = (GridView) findViewById(R.id.gridGame);

        refreshQuestions();

        imgOlivia.setImageDrawable(getResources().getDrawable(MainActivity.getCurrentSubject().getName().getImageOliviaId(this)));

        gridGame.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelativeLayout layoutParent = (RelativeLayout) view;
                RelativeLayout lytRoundBackground = (RelativeLayout) layoutParent.findViewById(R.id.lytRoundBackground);

                if (position + 1 == goodAnswerID) {

                    goodAnswerScore++;
                    updateScore();

                    refreshQuestions();
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
    }

    private void updateScore() {
        txtGoodAnswers.setText(getString(R.string.profile_rightAnswers) + " " + goodAnswerScore);
        txtBadAnswers.setText(getString(R.string.profile_wrongAnswers) + " " + badAnswerScore);
    }

    private void stopTheGame() {
        /*Subject currentSubject = MainActivity.getCurrentSubject();
        currentSubject.setRightAnswers(currentSubject.getRightAnswers() + goodAnswerScore);
        currentSubject.setWrongAnswers(currentSubject.getWrongAnswers() + badAnswerScore);
        currentSubject.setPlayedGames(currentSubject.getPlayedGames() + goodAnswerScore + badAnswerScore);

        MainActivity.fileConnector.setProfileList(getContext(), MainActivity.userList);*/

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().add(R.id.containerGame, new ScoreFragment());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }


    private void refreshQuestions() {
        List<String> answersList = new ArrayList<>();
        answersList.clear();
        if (MainActivity.getCurrentSubject().getName() == SubjectEnum.mathematics) {
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

        ItemGameAdapter adapter = new ItemGameAdapter(this, R.layout.item_grid_game, answersList);
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

    @Override
    public void onBackPressed() {
        timer.cancel();
        Intent newActivity = new Intent(this.getBaseContext(), SubjectsActivity.class);
        this.finish();
        startActivity(newActivity);
        return;
    }

    public int getGoodAnswerScore() {
        return goodAnswerScore;
    }

    public int getBadAnswerScore() {
        return badAnswerScore;
    }
}
