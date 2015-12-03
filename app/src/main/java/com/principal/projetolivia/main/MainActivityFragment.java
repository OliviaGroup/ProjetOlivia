package com.principal.projetolivia.main;

import  android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gc.materialdesign.views.ButtonFloat;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.fileConnector;
import com.principal.projetolivia.com.principal.projetolivia.util.user;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ButtonFloat buttonAddProfile;
    ListView listViewProfiles;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

        //TODO : à supprimer car test



        //

        // fin test

        listViewProfiles = (ListView) rootView.findViewById(R.id.listViewProfiles);

        fileConnector fileConnector = new fileConnector();
        MainActivity.userList = fileConnector.getProfileList(getActivity());
        if (MainActivity.userList.isEmpty()) {
            MainActivity.userList = new
        }



        ItemProfileAdapter adapter = new ItemProfileAdapter(getActivity(), R.layout.item_list_profiles, listProfiles);
        listViewProfiles.setAdapter(adapter);

        buttonAddProfile = (ButtonFloat) rootView.findViewById(R.id.buttonCreateProfile);
        buttonAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container, new NewProfileFragment());
                ft.addToBackStack(getTag());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }
        });

        return rootView;
    }
}
