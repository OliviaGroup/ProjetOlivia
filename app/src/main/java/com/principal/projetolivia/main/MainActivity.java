package com.principal.projetolivia.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.principal.projetolivia.R;
import com.principal.projetolivia.com.principal.projetolivia.util.Achievement;
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
    public static List<Achievement> achievementsList;
    public static List<User> userList;
    public static int currentUser;
    public static int currentSubject;
    public static FileConnector fileConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RemoveTheBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int idQuestionsFile;
        int idSuccessFile;

        if (Locale.getDefault().getDisplayLanguage().toString().equals("français")) {
            idQuestionsFile = R.raw.questions;
            idSuccessFile = R.raw.achievement;
        } else {
            idQuestionsFile = R.raw.questions_eng;
            idSuccessFile = R.raw.achievement_eng;
        }

        InputStream isQuestion = getResources().openRawResource(idQuestionsFile);
        InputStream isSuccess = getResources().openRawResource(idSuccessFile);



        fileConnector = new FileConnector(isQuestion, isSuccess);

        if (userList != null) {
            fileConnector.setProfileList(this, userList);
        }

        questionList = fileConnector.getQuestionList();
        achievementsList = fileConnector.getSuccessList();

        userList = fileConnector.getProfileList(this);

        /*if (userList.size() == 0) {
            Intent newActivity = new Intent(this.getBaseContext(), NewProfileActivity.class);
            startActivity(newActivity);
        } else {*/
            Intent newActivity = new Intent(this.getBaseContext(), ProfileActivity.class);
            startActivity(newActivity);
        /*}*/



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

    public static void RemoveTheBar(Activity activity) {
//        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}