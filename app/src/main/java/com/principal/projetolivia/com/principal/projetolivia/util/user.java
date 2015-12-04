package com.principal.projetolivia.com.principal.projetolivia.util;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    // constructors
    public user(String name, int age) {
        this.name = name;
        this.age = age;

        this.subjectList = new ArrayList<subject>();
        subjectList.clear();

        for (subjectName tempSubjectName :
                subjectName.values()) {
            subject tempSubject = new subject(tempSubjectName);
            subjectList.add(tempSubject);
        }
    }

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


}
