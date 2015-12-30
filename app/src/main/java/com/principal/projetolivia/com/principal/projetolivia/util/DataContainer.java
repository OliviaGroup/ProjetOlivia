package com.principal.projetolivia.com.principal.projetolivia.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Azerty on 29/12/2015.
 */
public class DataContainer {
    private List<Question> questionList;
    private List<User> userList;
    private int currentUser;
    private int currentSubject;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void setCurrentUser(int currentUser) {
        this.currentUser = currentUser;
    }

    public void setCurrentSubject(int currentSubject) {
        this.currentSubject = currentSubject;
    }

    public Question getOneQuestionOnSubject(SubjectName subjectName) {
        List<Question> subjectQuestions = new ArrayList<>();
        subjectQuestions.clear();

        for (Question question : questionList) {
            if (question.getSubject() == subjectName) {
                subjectQuestions.add(question);
            }
        }

        Random random = new Random();

        return subjectQuestions.get(random.nextInt(subjectQuestions.size()));
    }

    public Subject getCurrentSubject() {
        return userList.get(currentUser).getSubjectList().get(currentSubject);
    }

    public User getCurrentUser() {
        return userList.get(currentUser);
    }


}
