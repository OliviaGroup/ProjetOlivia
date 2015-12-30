package com.principal.projetolivia.com.principal.projetolivia.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by roosq on 30/11/2015.
 */
public class User implements Serializable {

    // private attr

    private String name;
    private Calendar dateOfBirth;
    private List<Subject> subjectList;

    // constructors

    public User(String name, Calendar dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;

        this.subjectList = new ArrayList<Subject>();
        subjectList.clear();

        for (SubjectName tempSubjectName :
                SubjectName.values()) {
            Subject tempSubject = new Subject(tempSubjectName);
            subjectList.add(tempSubject);
        }
    }

    public User(String name, Calendar dateOfBirth, List<Subject> subjectListBase) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;

        this.subjectList = subjectListBase;
    }

    // public getters
    public String getName() {
        return name;
    }

    public int getAge() {
        int temp = Calendar.getInstance().get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        return temp;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public int getTotalPercentRightAnswers() {
        int sum = 0;
        for (Subject subject :
                subjectList) {
            sum += subject.getPercentRightAnswers();
        }
        return sum / subjectList.size();
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}
