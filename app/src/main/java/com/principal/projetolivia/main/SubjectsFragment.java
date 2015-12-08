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

    ButtonFloat buttonAddProfile;
    ListView listViewProfiles;

    public SubjectsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_subjects, container, false);



        return rootView;
    }
}
