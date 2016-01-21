package com.principal.projetolivia.main;


import android.app.AlertDialog;
import android.content.DialogInterface;
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

        RelativeLayout transparentBackground = (RelativeLayout) rootView.findViewById(R.id.transparentBackground);
        transparentBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
                closeFragment();
            }
        });

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

        RelativeLayout deleteProfile = (RelativeLayout) rootView.findViewById(R.id.deleteProfile);
        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });

      return rootView;
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(getContext())
                //set message, title, and icon
                .setTitle(getContext().getString(R.string.delete_profile))
                .setMessage(getContext().getString(R.string.delete_message))

                .setPositiveButton(getContext().getString(R.string.delete), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        MainActivity.userList.remove(MainActivity.getCurrentUser());
                        MainActivity.fileConnector.setProfileList(getContext(), MainActivity.userList);

                        dialog.dismiss();
                        getFragmentManager().popBackStackImmediate();
                        getFragmentManager().popBackStackImmediate();
                        closeFragment();
                    }

                })



                .setNegativeButton(getContext().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    private void closeFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
