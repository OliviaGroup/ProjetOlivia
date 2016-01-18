package com.principal.projetolivia.com.principal.projetolivia.util;

import java.io.Serializable;

/**
 * Created by roosq on 30/11/2015.
 */
public class Subject implements Serializable, java.lang.Comparable{
    private SubjectEnum name;

    private int playedGames;
    private int hiScore;
    private int rightAnswers;
    private int wrongAnswers;

    public Subject(SubjectEnum name, int playedGames, int hiScore, int rightAnswers, int wrongAnswers) {
        this.name = name;
        this.playedGames = playedGames;
        this.hiScore = hiScore;
        this.rightAnswers = rightAnswers;
        this.wrongAnswers = wrongAnswers;
    }

    public Subject(SubjectEnum name) {
        this.name = name;
        this.playedGames = 0;
        this.hiScore = 0;
        this.rightAnswers = 0;
        this.wrongAnswers = 0;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public int getHiScore() {
        return hiScore;
    }

    public void setHiScore(int hiScore) {
        this.hiScore = hiScore;
    }

    public int getPercentRightAnswers () {
        int totalAnswers =  rightAnswers + wrongAnswers;
        if (totalAnswers != 0) {
            return rightAnswers * 100 / totalAnswers;
        } else {
            return 0;
        }
    }

    public SubjectEnum getName() {
        return name;
    }

    public void setName(SubjectEnum name) {
        this.name = name;
    }

    public int compareTo(Object other) {
        int nombre1 = ((Subject) other).getPercentRightAnswers();
        int nombre2 = this.getPercentRightAnswers();
        if (nombre1 > nombre2)  return 1;
        else if(nombre1 == nombre2) return 0;
        else return -1;
    }
}
