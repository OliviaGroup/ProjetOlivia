package com.principal.projetolivia.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.principal.projetolivia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsFragment extends Fragment {


    public OptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_options, container, false);

        RelativeLayout changeProfile = (RelativeLayout) rootView.findViewById(R.id.changeProfile);
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction().add(R.id.container, new ChangeProfileFragment());
                ft.addToBackStack("OptionsToChangeProfile");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }
        });

      return rootView;
    }

}
