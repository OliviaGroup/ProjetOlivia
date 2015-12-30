package com.principal.projetolivia.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.CropImageView;
import com.principal.projetolivia.com.principal.projetolivia.util.DataContainer;

public class GameActivity extends AppCompatActivity {
    public static DataContainer dataContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.gameContainer, new GameFragment())
                    .commit();
        }
        setContentView(R.layout.activity_game);

        CropImageView gameBackground = (CropImageView) (ImageView) this.findViewById(R.id.gameBackground);
        gameBackground.setOffset(1, 1);
    }
}
