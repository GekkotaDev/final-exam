/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator.eval;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Evaluate a Mathematical expression in Reverse Polish Notation.
 *
 * @author Jeremy Lusuegro
 */
public class RPN {
    private ArrayDeque<Double> operands = new ArrayDeque<Double>();

    private boolean isInt(String character) {
        // Simple regex match to be fast.
        return character.matches("\\d");
    }

    /**
     * Evaluate a Mathematical expression that is in Reverse Polish Notation.
     * 
     * @param tokens An array of tokens in RPN format.
     * @return The calculated result.
     */
    public double evaluate(ArrayList<String> tokens) throws Exception {
        for (final String token : tokens) {
            /*
             * For as long as the current token in the expression is not an
             * operator, all integer tokens should be pushed into the operands
             * stack.
             */
            if (isInt(token)) {
                final Double number = Double.parseDouble(token);
                this.operands.push(number);
                continue;
            }

            /*
             * Once the loop reaches this point, it is assumed that an operator
             * has been found. Pop two operands, perform the binary operation
             * on the operands, push the result back.
             */
            final char operator = token.charAt(0);
            final Double right = this.operands.pop();
            final Double left = this.operands.pop();
            double result;

            switch (operator) {
                case '×':
                case '*':
                    result = left * right;
                    break;

                case '÷':
                case '/':
                    result = left / right;
                    break;

                case '+':
                    result = left + right;
                    break;

                case '-':
                    result = left - right;
                    break;

                default:
                    throw new Exception("Invalid operator.");
            }

            this.operands.push(result);
        }

        return this.operands.pop();
    }
}
