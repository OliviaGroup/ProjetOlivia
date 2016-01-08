package com.principal.projetolivia.main;

import android.content.Intent;
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

/**
 * A placeholder fragment containing a simple view.
 */
public class SubjectsFragment extends Fragment {

    GridView gridViewSubjects;

    public SubjectsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_subjects, container, false);

        MainActivity.changeBackground(R.drawable.subjects_background);

        gridViewSubjects = (GridView) rootView.findViewById(R.id.gridSubjects);
        gridViewSubjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.currentSubject = position;
                FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container, new GameFragment());
                ft.addToBackStack(getTag());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }
        });

        final ItemSubjectAdapter adapter = new ItemSubjectAdapter(getActivity(), R.layout.item_grid_subjects, MainActivity.getCurrentUser().getSubjectList());
        gridViewSubjects.setAdapter(adapter);

        TextView userName = (TextView) rootView.findViewById(R.id.userName);
        userName.setText(MainActivity.getCurrentUser().getName());
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment());
                ft.addToBackStack(getTag());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }
        });

        return rootView;
    }

}
