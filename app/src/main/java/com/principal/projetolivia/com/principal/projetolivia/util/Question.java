package com.principal.projetolivia.com.principal.projetolivia.util;

import java.util.List;

/**
 * Created by roosq on 17/12/2015.
 */
public class Question {
    private String question;
    private int goodAnswer;
    private List<String> answers;
    private SubjectName subject;

    public Question(String question, int goodAnswer, List<String> answers, SubjectName subject) {
        this.question = question;
        this.goodAnswer = goodAnswer;
        this.answers = answers;
        this.subject = subject;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getGoodAnswer() {
        return goodAnswer;
    }

    public void setGoodAnswer(int goodAnswer) {
        this.goodAnswer = goodAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public SubjectName getSubject() {
        return subject;
    }

    public void setSubject(SubjectName subject) {
        this.subject = subject;
    }
}
