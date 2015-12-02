package com.principal.projetolivia.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.widgets.SnackBar;
import com.principal.projetolivia.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewProfileFragment extends Fragment {

    private EditText textName;
    private EditText textAge;

    private ButtonRectangle buttonValidation;

    public NewProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_profile, container, false);

        textName = (EditText) rootView.findViewById(R.id.textName);
        textAge = (EditText) rootView.findViewById(R.id.textAge);

        buttonValidation = (ButtonRectangle) rootView.findViewById(R.id.buttonValidation);
        buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Data", textName.getText().toString());
                if (textName.getText().toString().matches("") && textAge.getText().toString().matches("")) {
                    View.OnClickListener onClickListener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    };
                    SnackBar snackBar = new SnackBar(getActivity(), getResources().getString(R.string.new_profile_error), null, onClickListener);
                    snackBar.show();
                } else {

                }
            }
        });


        return rootView;
    }
}
