package com.principal.projetolivia.com.principal.projetolivia.util;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roosq on 03/12/2015.
 */
public class FileConnector {

    private final String fileUserData = "data";
    private InputStream fileQuestion;
    private InputStream fileSuccess;

    public FileConnector(InputStream fileQuestion, InputStream fileSuccess) {
        this.fileQuestion = fileQuestion;
        this.fileSuccess = fileSuccess;
    }


    public List<User> getProfileList(Context context) {
        List<User> returnObject = new ArrayList<>();
        returnObject.clear();
        try {
            FileInputStream fis = context.openFileInput(fileUserData);
            ObjectInputStream is = new ObjectInputStream(fis);
            returnObject = (List<User>) is.readObject();
            is.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return returnObject;
    }

    public void setProfileList(Context context, List<User> userList) {
        try {
            FileOutputStream fos = context.openFileOutput(fileUserData, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(userList);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Question> getQuestionList() {
        List<Question> questions = new ArrayList<>();
        String json = null;
        JSONObject jObj = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileQuestion, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            fileQuestion.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error",
                    "Error converting result " + e.toString());
        }

        try {
            jObj = new JSONObject(json);
        } catch (Exception e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        try {
            JSONArray questionJSON = jObj.getJSONArray("question");

            questions.clear();
            for (int i = 0; i < questionJSON.length(); i++) {
                JSONObject jsonObject = questionJSON.getJSONObject(i);
                String tempQuestion = jsonObject.getString("key");
                int tempGoodAnswer = Integer.parseInt(jsonObject.getString("good_answer"));
                SubjectEnum tempSubjectEnum = SubjectEnum.valueOf(jsonObject.getString("subject"));
                JSONArray answerJSON = jsonObject.getJSONArray("answer");
                List<GameAnswer> tempAnswers = new ArrayList<>();
                tempAnswers.clear();
                for (int j = 0; j < answerJSON.length(); j++) {
                    GameAnswer tempAnswer = new GameAnswer(false, answerJSON.getString(j));
                    tempAnswers.add(tempAnswer);
                }
                int  idGoodAnswer = Integer.parseInt(jsonObject.getString("good_answer"));
                tempAnswers.get(idGoodAnswer - 1).setGoodAnswer(true);
                Question question = new Question(tempQuestion, tempAnswers, tempSubjectEnum);
                questions.add(question);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
            e.printStackTrace();
        }


        return questions;
    }

    public List<Achievement> getSuccessList() {
        List<Achievement> achievementsList = new ArrayList<>();
        String json = null;
        JSONObject jObj = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileSuccess, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            fileSuccess.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error",
                    "Error converting result " + e.toString());
        }

        try {
            jObj = new JSONObject(json);
        } catch (Exception e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        try {
            achievementsList.clear();

            JSONArray achievementsJSON = jObj.getJSONArray("achievement");
            for (int i = 0; i < achievementsJSON.length(); i++) {
                JSONObject achievementJSON = achievementsJSON.getJSONObject(i);
                AchievementTypeEnum tempType = AchievementTypeEnum.valueOf(achievementJSON.getString("type"));
                JSONArray levelsJSON = achievementJSON.getJSONArray("level");
                for (int j = 0; j < levelsJSON.length(); j++) {
                    JSONObject levelJSON = levelsJSON.getJSONObject(j);
                    MedalLevelEnum tempLevel = MedalLevelEnum.valueOf(levelJSON.getString("level"));
                    int tempObjective = Integer.parseInt(levelJSON.getString("objective"));
                    JSONArray itemsJSON = levelJSON.getJSONArray("items");
                    for (int k = 0;k < itemsJSON.length(); k++) {
                        JSONObject itemJSON = itemsJSON.getJSONObject(k);
                        String tempTitle = itemJSON.getString("title");
                        SubjectEnum tempSubject = SubjectEnum.valueOf(itemJSON.getString("subject"));
                        Achievement achievement = new Achievement(tempTitle, tempType, tempSubject, tempLevel, tempObjective);
                        achievementsList.add(achievement);
                    }
                }
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
            e.printStackTrace();
        }

        return achievementsList;
    }
}
