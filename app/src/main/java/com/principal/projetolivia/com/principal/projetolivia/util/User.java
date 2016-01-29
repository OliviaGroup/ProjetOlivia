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
    private List<UserAchievement> userAchievementList;

    // constructors

    public User(String name, Calendar dateOfBirth, int numberOfAchievement) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;

        this.subjectList = new ArrayList<>();
        this.userAchievementList = new ArrayList<>();
        subjectList.clear();

        for (SubjectEnum tempSubjectEnum :
                SubjectEnum.values()) {
            Subject tempSubject = new Subject(tempSubjectEnum);
            subjectList.add(tempSubject);
        }

        userAchievementList.clear();

        for (int i = 1; i <= numberOfAchievement; i++) {
            UserAchievement userAchievement = new UserAchievement(i);
            userAchievementList.add(userAchievement);
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

    public Calendar getDateOfBirth() {
        return dateOfBirth;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<UserAchievement> getUserAchievementList() {
        return userAchievementList;
    }

    public void setUserAchievementList(List<UserAchievement> userAchievementList) {
        this.userAchievementList = userAchievementList;
    }
}
