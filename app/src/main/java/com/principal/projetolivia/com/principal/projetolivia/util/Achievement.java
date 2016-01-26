package com.principal.projetolivia.com.principal.projetolivia.util;


import android.content.Context;
import android.content.res.Resources;

import java.io.Serializable;

/**
 * Created by roosq on 21/01/2016.
 */
public class Achievement implements Serializable {
    private String title;
    private AchievementTypeEnum type;
    private SubjectEnum subject;
    private AchievementLevelEnum level;
    private int objective;

    public Achievement() {

    }

    public Achievement(String title, AchievementTypeEnum type, SubjectEnum subject, AchievementLevelEnum level, int objective) {
        this.title = title;
        this.type = type;
        this.subject = subject;
        this.level = level;
        this.objective = objective;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AchievementTypeEnum getType() {
        return type;
    }

    public void setType(AchievementTypeEnum type) {
        this.type = type;
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public void setSubject(SubjectEnum subject) {
        this.subject = subject;
    }

    public AchievementLevelEnum getLevel() {
        return level;
    }

    public void setLevel(AchievementLevelEnum level) {
        this.level = level;
    }

    public int getObjective() {
        return objective;
    }

    public void setObjective(int objective) {
        this.objective = objective;
    }

    public int getImageResourceId (Context context) {
        Resources res = context.getResources();

        int resId = res.getIdentifier(type.name() + "_" + subject.name() + "_" + level.name(), "drawable", context.getPackageName());

        if (0 != resId) {
            return resId;
        }

        return res.getIdentifier("error_button", "drawable", context.getPackageName());
    }

    public String getDescription (Context context) {
        Resources res = context.getResources();


            int resId = res.getIdentifier(this.name(), "string", context.getPackageName());
            if (0 != resId) {
                return (res.getString(resId));
            }
            return (name());
        }
    }
}
