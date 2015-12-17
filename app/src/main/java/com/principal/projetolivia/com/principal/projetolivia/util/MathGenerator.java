package com.principal.projetolivia.com.principal.projetolivia.util;

import java.util.Random;


/**
 * Created by roosq on 15/12/2015.
 */
public class MathGenerator {
    private int minQuestionElementValueSumSub = 1;
    private int maxQuestionElementValueSumSub = 100;
    private int minQuestionElementValueMulDiv = 1;
    private int maxQuestionElementValueMulDiv = 10;

    private final Random randomGenerator = new Random();

    private int leftNumber;
    private int rightNumber;
    private MathOperator operator;
    private int result;
    private int[] results = new int[4];

    public MathGenerator() {
    }

    public void calculationGenerator() {
        operator = MathOperator.values()[randomGenerator.nextInt(MathOperator.values().length)];

        if (operator == MathOperator.PLUS || operator == MathOperator.MINUS) {
            leftNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);
            rightNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);

            if (operator == MathOperator.MINUS && leftNumber < rightNumber) {
                do {
                    leftNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);
                    rightNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);
                } while (leftNumber < rightNumber);
            }

        } else {
            leftNumber = getRandomIntergerFromRange(minQuestionElementValueMulDiv, maxQuestionElementValueMulDiv);
            rightNumber = getRandomIntergerFromRange(minQuestionElementValueMulDiv, maxQuestionElementValueMulDiv);

            if (operator == MathOperator.DIVIDER) {
                if (leftNumber < rightNumber) {
                    do {
                        leftNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);
                        rightNumber = getRandomIntergerFromRange(minQuestionElementValueSumSub, maxQuestionElementValueSumSub);
                    } while (leftNumber < rightNumber);
                }
                double tempResultDouble = leftNumber / rightNumber;
                int tempResultInt = (int) tempResultDouble;
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

        for (int i = 0; i < 3; i++) {
            int falseResult;
            do {
                falseResult = getRandomIntergerFromRange(result - 20, result + 20);
            } while (falseResult == result && falseResult < 0);

            results[i] = falseResult;
        }
        results[getRandomIntergerFromRange(0, 3)] = result;
    }

    private int getRandomIntergerFromRange(int min, int max) {
        return randomGenerator.nextInt(max - min + 1) + min;
    }

    public int getResult() {
        return result;
    }

    public int[] getResults() {
        return results;
    }

    public String getOperation() {
        return leftNumber + " " + operator.getDisplayValue() + " " + rightNumber;
    }
}