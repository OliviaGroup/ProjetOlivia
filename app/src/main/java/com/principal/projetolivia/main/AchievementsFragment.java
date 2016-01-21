package com.principal.projetolivia.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.principal.projetolivia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementsFragment extends Fragment {


    public AchievementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_game, container, false);


        return rootView;
    }
}
