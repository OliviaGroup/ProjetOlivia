package com.principal.projetolivia.com.principal.projetolivia.util;

import java.io.Serializable;

/**
 * Created by roosq on 05/02/2016.
 */
public class MarginOlivia implements Serializable {

    private int leftMargin;
    private int topMargin;

    public MarginOlivia(int leftMargin, int topMargin) {
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public int getTopMargin() {
        return topMargin;
    }
}
