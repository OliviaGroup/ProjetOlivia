package com.principal.projetolivia.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.CropImageView;
import com.principal.projetolivia.com.principal.projetolivia.util.Question;
import com.principal.projetolivia.com.principal.projetolivia.util.SubjectName;
import com.principal.projetolivia.com.principal.projetolivia.util.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static List<Question> questionList;
    public static List<User> userList;
    public static int currentUser;
    public static int currentSubject;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Question getOneQuestionOnSubject(SubjectName subjectName) {
        List<Question> subjectQuestions = new ArrayList<>();
        subjectQuestions.clear();

        for (Question question : questionList) {
            if (question.getSubject() == subjectName) {
                subjectQuestions.add(question);
            }
        }

        Random random = new Random();

        return subjectQuestions.get(random.nextInt(subjectQuestions.size()));
    }
}
