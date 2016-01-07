package com.principal.projetolivia.com.principal.projetolivia.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by roosq on 30/11/2015.
 */
public class Usera implements Serializable {

    // private attr

    private String name;
    private Calendar dateOfBirth;
    private List<Subject> subjectList;

    // constructors

    public Usera(String name, Calendar dateOfBirth) {
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

    public Usera(String name, Calendar dateOfBirth, List<Subject> subjectListBase) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;

        this.subjectList = subjectListBase;
    }

    // public getters
    public String getName() {
        return name;
    }

    public int getAge() {
        int age = 0;
        int yeardiff = Calendar.getInstance().get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        int monthdiff = Calendar.getInstance().get(Calendar.MONTH) - dateOfBirth.get(Calendar.MONTH);
        int daydiff = Calendar.getInstance().get(Calendar.DATE) - dateOfBirth.get(Calendar.DATE);

        if (monthdiff < 0) {
            age = yeardiff - 1;
        }
        else if (monthdiff == 0){
            if (daydiff < 0){
                age = yeardiff - 1;
            }
            else {
                age = yeardiff;
            }
        }
        else {
            age = yeardiff;
        }

        return age;
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
