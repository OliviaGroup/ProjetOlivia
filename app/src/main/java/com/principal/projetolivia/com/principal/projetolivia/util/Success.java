package com.principal.projetolivia.com.principal.projetolivia.util;


import android.content.Context;
import android.content.res.Resources;

/**
 * Created by roosq on 21/01/2016.
 */
public class Success {
    private int id;
    private String title;
    private String description;
    private SuccessTypeEnum type;
    private SubjectEnum subject;
    private SuccessLevelEnum level;
    private int objective;

    public Success() {

    }

    public Success(int id, String title, String description, SuccessTypeEnum type, SubjectEnum subject, SuccessLevelEnum level, int objective) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SuccessTypeEnum getType() {
        return type;
    }

    public void setType(SuccessTypeEnum type) {
        this.type = type;
    }

    public SubjectEnum getSubject() {
        return subject;
    }

    public void setSubject(SubjectEnum subject) {
        this.subject = subject;
    }

    public SuccessLevelEnum getLevel() {
        return level;
    }

    public void setLevel(SuccessLevelEnum level) {
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
}
