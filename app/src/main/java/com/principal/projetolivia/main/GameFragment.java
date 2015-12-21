package com.principal.projetolivia.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.*;

import java.util.ArrayList;
import java.util.List;


public class GameFragment extends Fragment {
    private TextView question;
    private GridView gridGame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        question = (TextView) rootView.findViewById(R.id.question);
        gridGame = (GridView) rootView.findViewById(R.id.gridGame);

        refreshQuestions(rootView);


        return rootView;
    }

    private void refreshQuestions(View view) {
        List<String> answersList = new ArrayList<>();
        answersList.clear();
        if (MainActivity.userList.get(MainActivity.currentUser).getSubjectList().get(MainActivity.currentSubject).getName() == SubjectName.mathematics) {
            MathGenerator mathGenerator = new MathGenerator();
            mathGenerator.calculationGenerator();
            question.setText(mathGenerator.getOperation());
            answersList = mathGenerator.getResults();
        } else {
            Question randomQuestion = MainActivity.getOneQuestionOnSubject(MainActivity.userList.get(MainActivity.currentUser).getSubjectList().get(MainActivity.currentSubject).getName());
            question.setText(randomQuestion.getQuestion());
            answersList = randomQuestion.getAnswers();
        }

        ItemGameAdapter adapter = new ItemGameAdapter(getActivity(), R.layout.item_grid_game, answersList);
        gridGame.setAdapter(adapter);
    }
}
