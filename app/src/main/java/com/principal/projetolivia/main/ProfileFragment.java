package com.principal.projetolivia.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.Subject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    GridView gridProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_profile, container, false);

        TextView profileNametext = (TextView) rootView.findViewById(R.id.profileNameText);
        profileNametext.setText(MainActivity.userList.get(MainActivity.currentUser).getName());

        TextView profileAgeText = (TextView) rootView.findViewById(R.id.profileAgeText);
        profileAgeText.setText(MainActivity.userList.get(MainActivity.currentUser).getAge() + " ans");

        gridProfile = (GridView) rootView.findViewById(R.id.gridProfile);
        MainActivity.userList.get(MainActivity.currentUser).getSubjectList().get(3).setRightAnswers(3);
        MainActivity.userList.get(MainActivity.currentUser).getSubjectList().get(3).setWrongAnswers(7);

        List<Subject> subjectsListByScoreOrder = new ArrayList<Subject>();
        for (Subject subject : MainActivity.userList.get(MainActivity.currentUser).getSubjectList()){
            subjectsListByScoreOrder.add(subject);
        }

        Collections.sort(subjectsListByScoreOrder);
        final ItemSubjectDataAdapter adapter = new ItemSubjectDataAdapter(getActivity(), R.layout.item_grid_subject_data, subjectsListByScoreOrder);
        gridProfile.setAdapter(adapter);

        return rootView;
    }

}
