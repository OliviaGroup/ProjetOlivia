package com.principal.projetolivia.com.principal.projetolivia.util;

/**
 * Created by roosq on 30/11/2015.
 */
public class subject extends subjectDefault {
    private int point;

    public subject(String name, int pointMax, int point) {
        super(name, pointMax);
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
