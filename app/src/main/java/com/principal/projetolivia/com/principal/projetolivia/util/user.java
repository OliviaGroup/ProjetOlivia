package com.principal.projetolivia.com.principal.projetolivia.util;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roosq on 30/11/2015.
 */
public class user implements Serializable {

    // private attr

    private String name;
    private int age;
    private List<subject> subjectList;

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
