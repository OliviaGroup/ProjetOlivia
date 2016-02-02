package com.principal.projetolivia.com.principal.projetolivia.util;

import java.util.List;

/**
 * Created by roosq on 17/12/2015.
 */
public class Question {
    private String question;
    private List<GameAnswer> answers;
    private SubjectEnum subject;

    public Question(String question, List<GameAnswer> answers, SubjectEnum subject) {
        this.question = question;
        this.answers = answers;
        this.subject = subject;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<GameAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<GameAnswer> answers) {
        this.answers = answers;
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public void setSubject(SubjectEnum subject) {
        this.subject = subject;
    }
}
