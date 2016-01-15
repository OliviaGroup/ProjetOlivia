package com.principal.projetolivia.main;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.CropImageView;
import com.principal.projetolivia.com.principal.projetolivia.util.FileConnector;
import com.principal.projetolivia.com.principal.projetolivia.util.Question;
import com.principal.projetolivia.com.principal.projetolivia.util.Subject;
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
    public static FileConnector fileConnector;
    private static CropImageView mainBackground;
    private static Resources resources ;

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

        resources = getResources();

        mainBackground = (CropImageView) (ImageView) this.findViewById(R.id.mainBackground);
        mainBackground.setOffset(1, 1);
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

    public static void changeBackground(int idBackground) {
        mainBackground.setImageDrawable(resources.getDrawable(idBackground));
    }

    public static Subject getCurrentSubject() {
        return userList.get(currentUser).getSubjectList().get(currentSubject);
    }

    public static User getCurrentUser() {
        return userList.get(currentUser);
    }
}