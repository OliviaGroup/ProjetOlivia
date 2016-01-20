package com.principal.projetolivia.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.principal.projetolivia.R;

/**
 * Created by roosq on 20/01/2016.
 */
public class OptionsActivity extends AppCompatActivity {
    private RelativeLayout changeProfile;
    private RelativeLayout deleteProfile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        changeProfile = (RelativeLayout) findViewById(R.id.changeProfile);
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(v.getContext(), ChangeProfileActivity.class);
                startActivity(newActivity);
            }
        });

        deleteProfile = (RelativeLayout) findViewById(R.id.deleteProfile);
        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });

    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle(getString(R.string.delete_profile))
                .setMessage(getString(R.string.delete_message))

                .setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        MainActivity.userList.remove(MainActivity.getCurrentUser());
                        MainActivity.fileConnector.setProfileList(getApplicationContext(), MainActivity.userList);

                        dialog.dismiss();
                        getFragmentManager().popBackStackImmediate();
                        getFragmentManager().popBackStackImmediate();
                        // TODO : Voir avec marie ce qu'il faut faire ici avant closeFragment();
                    }

                })



                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }
}

