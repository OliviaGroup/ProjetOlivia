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

    private static final String ns = null;

    public FileConnector(InputStream fileQuestion, InputStream fileSuccess) {
        this.fileQuestion = fileQuestion;
        this.fileSuccess = fileSuccess;
    }


    public List<User> getProfileList(Context context) {
        List<User> returnObject = new ArrayList<User>();
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
                List<String> tempAnswers = new ArrayList<>();
                tempAnswers.clear();
                for (int j = 0; j < answerJSON.length(); j++) {
                    String tempAnswer = answerJSON.getString(j);
                    tempAnswers.add(tempAnswer);
                }
                Question question = new Question(tempQuestion, tempGoodAnswer, tempAnswers, tempSubjectEnum);
                questions.add(question);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
            e.printStackTrace();
        }


        return questions;
    }

    public List<Success> getSuccessList() {
        List<Success> successList = new ArrayList<>();
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
            JSONArray successJSON = jObj.getJSONArray("success");

            successList.clear();
            for (int i = 0; i < successJSON.length(); i++) {
                JSONObject jsonObject = successJSON.getJSONObject(i);
                int tempId = Integer.parseInt(jsonObject.getString("id"));
                String tempTitle = jsonObject.getString("title");
                String tempDescription = jsonObject.getString("description");
                SuccessTypeEnum tempType = SuccessTypeEnum.valueOf("type");
                SubjectEnum tempSubject = SubjectEnum.valueOf("subject");
                SuccessLevelEnum tempLevel = SuccessLevelEnum.valueOf("level");
                int tempObjective = Integer.parseInt(jsonObject.getString("objective"));

                Success success = new Success(tempId, tempTitle, tempDescription, tempType, tempSubject, tempLevel, tempObjective);
                successList.add(success);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
            e.printStackTrace();
        }

        return successList;
    }
}
