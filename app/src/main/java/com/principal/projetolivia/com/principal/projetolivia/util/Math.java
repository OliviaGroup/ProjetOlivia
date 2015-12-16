package com.principal.projetolivia.com.principal.projetolivia.util;

import android.renderscript.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by roosq on 15/12/2015.
 */
public class Math {
    private int minQuestionElementValueSumSub;
    private int maxQuestionElementValueSumSub;
    private int minQuestionElementValueMulDiv;
    private int maxQuestionElementValueMulDiv;

    private final Random randomGenerator = new Random();

    private int leftNumber;
    private int rightNumber;
    private Operator operator;
    private int result;

    public Math(int minQuestionElementValueSumSub, int maxQuestionElementValueSumSub, int minQuestionElementValueMulDiv, int maxQuestionElementValueMulDiv) {
        this.minQuestionElementValueSumSub = minQuestionElementValueSumSub;
        this.maxQuestionElementValueSumSub = maxQuestionElementValueSumSub;
        this.minQuestionElementValueMulDiv = minQuestionElementValueMulDiv;
        this.maxQuestionElementValueMulDiv = maxQuestionElementValueMulDiv;
    }

    public void calculationGenerator() {
        operator = Operator.values()[randomGenerator.nextInt(Operator.values().length)];

        if (operator == Operator.PLUS || operator == Operator.MINUS) {
            leftNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);
            rightNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);

            if (operator == Operator.MINUS && leftNumber < rightNumber) {
                do {
                    leftNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);
                    rightNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);
                } while (leftNumber < rightNumber);
            }

        } else {
            leftNumber = getRandomIntergerFromRange(minQuestionElementValueMulDiv, maxQuestionElementValueMulDiv);
            rightNumber = getRandomIntergerFromRange(minQuestionElementValueMulDiv, maxQuestionElementValueMulDiv);

            if (operator == Operator.DIVIDER) {
                if (leftNumber < rightNumber) {
                    do {
                        leftNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);
                        rightNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);
                    } while (leftNumber < rightNumber);
                }
                double tempResultDouble = leftNumber / rightNumber;
                int tempResultInt = (int)tempResultDouble;
                leftNumber = tempResultInt * rightNumber;
            }
        }

        switch (operator) {
            case PLUS:
                result = leftNumber + rightNumber;
                break;
            case MINUS:
                result = leftNumber - rightNumber;
                break;
            case MULTIPLIER:
                result = leftNumber * rightNumber;
                break;
            case DIVIDER:
                result = leftNumber / rightNumber;
        }
    }

    private int getRandomIntergerFromRange(int min, int max) {
        return randomGenerator.nextInt(max - min + 1) + min;
    }

    public int getResult() {
        return result;
    }

    public String getOperation() {
        return leftNumber + " " + operator.getDisplayValue() + " " + rightNumber;
    }
}

enum Operator {

    PLUS("+"), MINUS("-"), MULTIPLIER("*"), DIVIDER("/");

    private String displayValue;

    Operator(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}