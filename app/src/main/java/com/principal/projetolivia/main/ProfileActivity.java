package com.principal.projetolivia.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gc.materialdesign.views.ButtonFloat;
import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.CropImageView;

/**
 * Created by roosq on 20/01/2016.
 */
public class ProfileActivity extends AppCompatActivity{

    private static CropImageView mainBackground;
    private ButtonFloat buttonAddProfile;
    private ListView listViewProfiles;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mainBackground = (CropImageView) findViewById(R.id.mainBackground);
        mainBackground.setOffset(1, 1);
        mainBackground.setImageDrawable(getResources().getDrawable(R.drawable.background));

        listViewProfiles = (ListView) findViewById(R.id.listViewProfiles);

        final ItemProfileAdapter adapter = new ItemProfileAdapter(this, R.layout.item_list_profiles, MainActivity.userList);
        //listViewProfiles.setEmptyView(findViewById(R.id.textIfListEmpty));
        listViewProfiles.setAdapter(adapter);

        listViewProfiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.currentUser = position;
                Intent newActivity = new Intent(view.getContext(), SubjectsActivity.class);
                startActivity(newActivity);
            }
        });

        buttonAddProfile = (ButtonFloat) findViewById(R.id.buttonCreateProfile);
        buttonAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newActivity = new Intent(v.getContext(), NewProfileActivity.class);
                startActivity(newActivity);
            }
        });
    }
}
