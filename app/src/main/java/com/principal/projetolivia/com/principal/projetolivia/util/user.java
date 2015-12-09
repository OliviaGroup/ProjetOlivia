package com.principal.projetolivia.com.principal.projetolivia.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roosq on 30/11/2015.
 */
public class User implements Serializable {

    // private attr

    private String name;
    private int age;
    private List<Subject> subjectList;

    // constructors
    public User(String name, int age) {
        this.name = name;
        this.age = age;

        this.subjectList = new ArrayList<Subject>();
        subjectList.clear();

        for (SubjectName tempSubjectName :
                SubjectName.values()) {
            Subject tempSubject = new Subject(tempSubjectName);
            subjectList.add(tempSubject);
        }
    }

    public User(String name, int age, List<Subject> subjectListBase) {
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

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public int getTotalPercentRightAnswers() {
        int sum = 0;
        for (Subject subject :
                subjectList) {
            sum =+ subject.getPercentRightAnswers();
        }
        return sum / subjectList.size();
    }


}
