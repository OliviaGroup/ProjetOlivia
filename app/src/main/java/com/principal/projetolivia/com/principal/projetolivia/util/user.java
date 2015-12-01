package com.principal.projetolivia.com.principal.projetolivia.util;

import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by roosq on 30/11/2015.
 */
public class user implements Serializable {

    // private attr
    private String name;
    private int age;
    private List<subject> subjectList;

    private static InputStream is;
    private static JSONObject jObj = null;
    private static String json = "";

    // constructor
    public user(String name, int age, List<subject> subjectListBase) {
        this.name = name;
        this.age = age;

        this.subjectList = subjectListBase;
    }

    // public getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<subject> getSubjectList() {
        return subjectList;
    }

    public int getPointTotal() {
        int sum = 0;
        for (subject subject : subjectList
                ) {
            sum += subject.getPoint();
        }
        return sum;
    }

    public void getProfileList() {
        File dataFile = new File(Environment.getExternalStorageDirectory() + "/OOlivia", "data.json");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            reader.close();
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
            JSONArray users = jObj.getJSONArray("users");

            if (users.length() != 0) {
                JSONObject oneUser = users.getJSONObject(0);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setProfileList() {

    }
}
