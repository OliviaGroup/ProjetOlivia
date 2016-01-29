package com.principal.projetolivia.com.principal.projetolivia.util;

import java.util.List;

/**
 * Created by roosq on 27/01/2016.
 */
public class AchievementHolder {

    private Achievement achievement;
    private UserAchievement userAchievement;

    public AchievementHolder(Achievement achievement, UserAchievement userAchievement) {
        this.achievement = achievement;
        this.userAchievement = userAchievement;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public UserAchievement getUserAchievement() {
        return userAchievement;
    }

    public void setUserAchievement(UserAchievement userAchievement) {
        this.userAchievement = userAchievement;
    }
}
