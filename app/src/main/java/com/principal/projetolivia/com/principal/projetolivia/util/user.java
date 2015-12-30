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
        int rightAnswersSum = 0;
        int wrongAnswersSum = 0;
        for (Subject subject :
                subjectList) {
            rightAnswersSum += subject.getRightAnswers();
            wrongAnswersSum += subject.getWrongAnswers();
        }
        int totalAnswers = rightAnswersSum + wrongAnswersSum;
        if (totalAnswers != 0) {
            return rightAnswersSum * 100 / totalAnswers;
        } else {
            return 0;
        }

    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}
