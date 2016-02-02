package com.principal.projetolivia.com.principal.projetolivia.util;

import java.util.List;

/**
 * Created by roosq on 01/02/2016.
 */
public class GameAnswer {
    private boolean goodAnswer;
    private String answer;

    public GameAnswer(boolean goodAnswer, String answer) {
        this.goodAnswer = goodAnswer;
        this.answer = answer;
    }

    public boolean isGoodAnswer() {
        return goodAnswer;
    }

    public void setGoodAnswer(boolean goodAnswer) {
        this.goodAnswer = goodAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
