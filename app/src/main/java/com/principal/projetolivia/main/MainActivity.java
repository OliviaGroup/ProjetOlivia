package com.principal.projetolivia.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.FileConnector;
import com.principal.projetolivia.com.principal.projetolivia.util.Question;
import com.principal.projetolivia.com.principal.projetolivia.util.Subject;
import com.principal.projetolivia.com.principal.projetolivia.util.SubjectEnum;
import com.principal.projetolivia.com.principal.projetolivia.util.User;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static List<Question> questionList;
    public static List<User> userList;
    public static int currentUser;
    public static int currentSubject;
    public static FileConnector fileConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int idQuestionsFile;

        if (Locale.getDefault().getDisplayLanguage().toString().equals("français")) {
            idQuestionsFile = R.raw.questions;
        } else {
            idQuestionsFile = R.raw.questions_eng;
        }

        InputStream is = getResources().openRawResource(idQuestionsFile);


        fileConnector = new FileConnector(is);

        if (userList != null) {
            fileConnector.setProfileList(this, userList);
        }

        questionList = fileConnector.getQuestionList();

        userList = fileConnector.getProfileList(this);

        Intent newActivity = new Intent(this.getBaseContext(), ProfileActivity.class);
        startActivity(newActivity);

    }

    public static Question getOneQuestionOnSubject(SubjectEnum subjectEnum) {
        List<Question> subjectQuestions = new ArrayList<>();
        subjectQuestions.clear();

        for (Question question : questionList) {
            if (question.getSubject() == subjectEnum) {
                subjectQuestions.add(question);
            }
        }

        Random random = new Random();

        return subjectQuestions.get(random.nextInt(subjectQuestions.size()));
    }

    public static Subject getCurrentSubject() {
        return userList.get(currentUser).getSubjectList().get(currentSubject);
    }

    public static User getCurrentUser() {
        return userList.get(currentUser);
    }
}