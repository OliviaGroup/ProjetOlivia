package com.principal.projetolivia.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonFloat;
import com.principal.projetolivia.R;

import java.util.Locale;

/**
 * Created by roosq on 10/02/2016.
 */
public class ComicsActivity extends AppCompatActivity{
    private int stage = 1;
    private String textLanguage = "";
    private ImageView imgComic;

    private ButtonFloat btnPrev;
    private ButtonFloat btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.RemoveTheBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);

        imgComic = (ImageView) findViewById(R.id.imgComic);
        btnPrev = (ButtonFloat) findViewById(R.id.btnPrev);
        btnNext = (ButtonFloat) findViewById(R.id.btnNext);

        if (Locale.getDefault().getDisplayLanguage().toString().equals("français")) {
            textLanguage = "fra";
        } else {
            textLanguage = "eng";
        }

        imgComic.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("comics_" + textLanguage + "_1", "drawable", getPackageName())));

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (stage) {
                    case 1:
                        imgComic.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("comics_" + textLanguage + "_2", "drawable", getPackageName())));
                        stage = 2;
                        break;
                    case 2:
                        imgComic.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("comics_" + textLanguage + "_3", "drawable", getPackageName())));
                        stage = 3;
                        break;
                    case 3:
                        Intent newActivity = new Intent(v.getContext(), StoryActivity.class);
                        startActivity(newActivity);
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (stage) {
                    case 1:
                        Intent newActivity = new Intent(v.getContext(), SubjectsActivity.class);
                        startActivity(newActivity);
                        break;
                    case 2:
                        imgComic.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("comics_" + textLanguage + "_1", "drawable", getPackageName())));
                        stage = 1;
                        break;
                    case 3:
                        imgComic.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("comics_" + textLanguage + "_2", "drawable", getPackageName())));
                        stage = 2;
                        break;
                }
            }
        });
    }

}
