package com.principal.projetolivia.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.*;


public class GameFragment extends Fragment {
    private TextView question;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        question = (TextView) rootView.findViewById(R.id.question);




        refreshquestions(rootView);




        return rootView;
    }

    private void refreshquestions (View view) {
        if (MainActivity.userList.get(MainActivity.currentUser).getSubjectList().get(MainActivity.currentSubject).getName() == SubjectName.mathematics) {
            MathGenerator mathGenerator = new MathGenerator();
            mathGenerator.calculationGenerator();
            question.setText(mathGenerator.getOperation());
        }
    }
}
