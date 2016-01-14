package com.principal.projetolivia.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.principal.projetolivia.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SubjectsFragment extends Fragment {
    GridView gridViewSubjects;
    TextView userNameText;

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

        userNameText = (TextView) rootView.findViewById(R.id.userNameText);
        userNameText.setText(MainActivity.getCurrentUser().getName());

        ButtonFloat userName = (ButtonFloat) rootView.findViewById(R.id.userName);
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment());
                ft.addToBackStack(getTag());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }
        });

        ButtonFloat buttonOptions = (ButtonFloat) rootView.findViewById(R.id.buttonOptions);
        buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction().add(R.id.container, new OptionsFragment());
                ft.addToBackStack(getTag());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                update();
            }
        });

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                        fm.popBackStackImmediate();
                    }

                    FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container, new MainActivityFragment());
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }

    private void update(){
        userNameText.setText(MainActivity.getCurrentUser().getName());
    }

    private void closeFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
