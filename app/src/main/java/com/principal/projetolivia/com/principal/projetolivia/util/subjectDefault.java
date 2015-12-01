package com.principal.projetolivia.com.principal.projetolivia.util;

/**
 * Created by roosq on 01/12/2015.
 */
public class subjectDefault {
    private String name;
    private int pointMax;

    public subjectDefault() {
    }

    public subjectDefault(String name, int pointMax) {
        this.name = name;
        this.pointMax = pointMax;
    }

    public String getName() {
        return name;
    }

    public int getPointMax() {
        return pointMax;
    }
}
