package com.principal.projetolivia.com.principal.projetolivia.util;

import java.io.Serializable;

/**
 * Created by roosq on 25/01/2016.
 */
public class UserAchievement implements Serializable {
    private int id;
    private int value;
    private int percent;
    private boolean achievementReceived;

    public UserAchievement(int id) {
        this.id = id;
        this.value = 0;
        this.percent = 0;
        this.achievementReceived = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isAchievementReceived() {
        return achievementReceived;
    }

    public void setAchievementReceived(boolean achievementReceived) {
        this.achievementReceived = achievementReceived;
    }

    public int getPercent() {
        if (achievementReceived == true) {
            return 100;
        } else {
            return percent;
        }

    }

    public void setPercent(int value, int objective) {
        this.percent = value * 100 / objective;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
