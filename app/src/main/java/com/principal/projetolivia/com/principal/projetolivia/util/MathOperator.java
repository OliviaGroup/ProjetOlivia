package com.principal.projetolivia.com.principal.projetolivia.util;

/**
 * Created by roosq on 16/12/2015.
 */
enum MathOperator {

    PLUS("+"), MINUS("-"), MULTIPLIER("*"), DIVIDER("/");

    private String displayValue;

    MathOperator(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}