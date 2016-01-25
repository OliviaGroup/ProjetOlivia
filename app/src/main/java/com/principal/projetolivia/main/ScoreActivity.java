package com.principal.projetolivia.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.GameResultEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.ImproveScoreEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.Subject;

/**
 * Created by roosq on 20/01/2016.
 */
public class ScoreActivity extends AppCompatActivity {

    private int goodAnswerScore;
    private int badAnswerScore;
    private int oldPercentScore;
    private int oldHiScore;

    private GameResultEnum currentGameResult;
    private ImproveScoreEnum currentImproveScore;

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

    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.RemoveTheBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        goodAnswerScore = (int) getIntent().getSerializableExtra("goodAnswerScore");
        badAnswerScore = (int) getIntent().getSerializableExtra("badAnswerScore");

        Subject currentSubject = MainActivity.getCurrentSubject();
        oldPercentScore = currentSubject.getPercentRightAnswers();
        oldHiScore = currentSubject.getHiScore();

        txtScoreTitle = (TextView) findViewById(R.id.txtScoreTitle);
        txtHiScore = (TextView) findViewById(R.id.txtHiScore);
        imgOliviaScore = (ImageView) findViewById(R.id.imgOliviaScore);
        txtGoodAnswersScore = (TextView) findViewById(R.id.txtGoodAnswersScore);
        txtBadAnswersScore = (TextView) findViewById(R.id.txtBadAnswersScore);
        txtOldPercent = (TextView) findViewById(R.id.txtOldPercent);
        imgArrow = (ImageView) findViewById(R.id.imgArrow);
        txtNewPercent = (TextView) findViewById(R.id.txtNewPercent);
        btnGoToSubjects = (ButtonFloat) findViewById(R.id.btnGoToSubjects);
        btnGoToRetry = (ButtonFloat) findViewById(R.id.btnGoToRetry);

        txtGoodAnswersScore.setText(getString(R.string.profile_rightAnswers) + " " + goodAnswerScore);
        txtBadAnswersScore.setText(getString(R.string.profile_wrongAnswers) + " " + badAnswerScore);
        txtHiScore.setText(getString(R.string.profile_hiScore) + " " + oldHiScore);

        if (oldHiScore < goodAnswerScore) {
            currentGameResult = GameResultEnum.hi_score;
            currentSubject.setHiScore(goodAnswerScore);
        } else {
            if (badAnswerScore <= goodAnswerScore) {
                currentGameResult = GameResultEnum.win;
            } else {
                currentGameResult = GameResultEnum.loose;
            }
        }

        txtScoreTitle.setText(currentGameResult.getLabel(this));
        imgOliviaScore.setImageDrawable(getResources().getDrawable(currentGameResult.getImageOliviaId(this)));



        txtOldPercent.setText(oldPercentScore + getString(R.string.min_percent));

        currentSubject.setRightAnswers(currentSubject.getRightAnswers() + goodAnswerScore);
        currentSubject.setWrongAnswers(currentSubject.getWrongAnswers() + badAnswerScore);
        currentSubject.setPlayedGames(currentSubject.getPlayedGames() + goodAnswerScore + badAnswerScore);


        txtNewPercent.setText(currentSubject.getPercentRightAnswers() + getString(R.string.min_percent));

        int currentDifferencePercent = currentSubject.getPercentRightAnswers() - oldPercentScore;

        if (currentDifferencePercent < 0) {
            currentImproveScore = ImproveScoreEnum.worse;
        } else if (currentDifferencePercent == 0) {
            currentImproveScore = ImproveScoreEnum.equal;
        } else if (currentDifferencePercent > 0) {
            currentImproveScore = ImproveScoreEnum.better;
        }

        imgArrow.setImageDrawable(getResources().getDrawable(currentImproveScore.getImageArrowId(this)));

        MainActivity.fileConnector.setProfileList(this, MainActivity.userList);

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
    }

}
