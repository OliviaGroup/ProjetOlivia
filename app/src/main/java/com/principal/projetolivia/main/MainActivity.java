package com.principal.projetolivia.main;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.CropImageView;
import com.principal.projetolivia.com.principal.projetolivia.util.DataContainer;

public class MainActivity extends AppCompatActivity {
    public static DataContainer dataContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainActivityFragment())
                    .commit();
        }
        setContentView(R.layout.activity_main);

        CropImageView mainBackground = (CropImageView) (ImageView) this.findViewById(R.id.mainBackground);
        mainBackground.setOffset(1, 1);
    }


}
