package com.principal.projetolivia.com.principal.projetolivia.util;

import java.io.Serializable;

/**
 * Created by roosq on 25/01/2016.
 */
public class UserAchievement implements Serializable {
    private int id;
    private int value;
    private boolean achievementReceived;

    public UserAchievement(int id, int value, boolean achievementReceived) {
        this.id = id;
        this.value = value;
        this.achievementReceived = achievementReceived;
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
}
