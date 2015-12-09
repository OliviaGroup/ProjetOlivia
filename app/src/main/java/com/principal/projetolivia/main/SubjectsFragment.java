package com.principal.projetolivia.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.gc.materialdesign.views.ButtonFloat;
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

        gridViewSubjects = (GridView) rootView.findViewById(R.id.gridSubjects);

        final ItemSubjectAdapter adapter = new ItemSubjectAdapter(getActivity(), R.layout.item_grid_subjects, MainActivity.userList.get(MainActivity.currentUser).getSubjectList());
        gridViewSubjects.setAdapter(adapter);

        return rootView;
    }
}
