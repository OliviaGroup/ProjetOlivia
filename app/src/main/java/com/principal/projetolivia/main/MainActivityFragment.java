package com.principal.projetolivia.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gc.materialdesign.views.ButtonFloat;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.DataContainer;
import com.principal.projetolivia.com.principal.projetolivia.util.fileConnector;

import java.io.InputStream;

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
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        listViewProfiles = (ListView) rootView.findViewById(R.id.listViewProfiles);

        InputStream is = getResources().openRawResource(R.raw.questions);

        fileConnector fileConnector = new fileConnector(is);

        if (MainActivity.dataContainer != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container, new SubjectsFragment());
            ft.addToBackStack(getTag());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        } else {
            MainActivity.dataContainer = new DataContainer();
        }


        if (MainActivity.dataContainer.getUserList() != null) {
            fileConnector.setProfileList(getActivity(), MainActivity.dataContainer.getUserList());
        }

        MainActivity.dataContainer.setQuestionList(fileConnector.getQuestionList());

        MainActivity.dataContainer.setUserList(fileConnector.getProfileList(getActivity()));


        final ItemProfileAdapter adapter = new ItemProfileAdapter(getActivity(), R.layout.item_list_profiles, MainActivity.dataContainer.getUserList());
        //listViewProfiles.setEmptyView(rootView.findViewById(R.id.textIfListEmpty));
        listViewProfiles.setAdapter(adapter);

        listViewProfiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.dataContainer.setCurrentUser(position);
                FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.container, new SubjectsFragment());
                ft.addToBackStack(getTag());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }
        });

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
