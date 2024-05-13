/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator.eval;

import java.util.ArrayList;
import java.util.ArrayDeque;

/**
 * Parser that takes an array of tokens to produce a Mathematic expression in
 * Reverse Polish Notation.
 *
 * @author Jeremy Lusuegro
 */
public class Parser {
    private ArrayDeque<String> operators = new ArrayDeque<String>();
    private ArrayList<String> output = new ArrayList<String>();

    private boolean isInt(String character) {
        // Simple regex match to be fast.
        return character.matches("\\d");
    }

    /**
     * Get the precedence of the given operator. If it is negative, then it is
     * not one of the valid operators.
     * 
     * @param operator A single binary operator.
     * @return The precedence of the operator.
     */
    private int getPrecedence(String operator) {
        final char token = operator.charAt(0);

        switch (token) {
            case '×':
            case '*': return 2;
            case '÷':
            case '/': return 2;
            case '+': return 1;
            case '-': return 1;
            default: return -1;
        }
    }

    /**
     * Parse an array of tokens of an infix expression into Reverse Polish
     * Notation.
     * 
     * The implementation of this algorithm is based on the following resources.
     * - https://aquarchitect.github.io/swift-algorithm-club/Shunting%20Yard/
     * - https://brilliant.org/wiki/shunting-yard-algorithm/
     * 
     * @param infixTokens An array of infix Math expression tokens.
     * @return An array of Reverse Polish Notation tokens.
     * @throws java.lang.Exception
     */
    public ArrayList<String> parse(ArrayList<String> infixTokens) throws Exception {
        for (final String infixToken: infixTokens) {
            /*
             * As stated in the Shunting Yard algorithm, all numbers will be
             * automatically pushed to output.
             */
            if (isInt(infixToken)) {
                this.output.add(infixToken);
                continue;
            }

            /* !!
             * Beyond this point within the loop, it is assumed that the token
             * will be valid operators.
             */

            /** Precedence of the current token. */
            final int currentPrecedence = getPrecedence(infixToken);

            /** Precedence of the last token in the operator stack. */
            int lastPrecedence;

            if (!this.operators.isEmpty()) {
                final String lastOperator = this.operators.pop();
                lastPrecedence = getPrecedence(lastOperator);
                this.operators.push(lastOperator);
            } else {
                // It means no operators in the stack.
                lastPrecedence = 0;
            }

            // Invalid operator.
            if (lastPrecedence < 0) throw new Exception(
                    "An invalid operator is found in the operator stack."
                );

            // No operators are in the operator stack. Push it.
            if (lastPrecedence == 0) {
                this.operators.push(infixToken);
                continue;
            }

            /*
             * The current token has greater precedence than the token at the
             * top of the operator stack. The algorithm states it should be
             * pushed to the stack.
             */
            if (currentPrecedence > lastPrecedence) {
                this.operators.push(infixToken);
                continue;
            }

            /*
             * If the loop has reached this point, it means that the token has
             * a precedence equal to or lesser than the token at the top of the
             * operator stack. The algorithm states that tokens should be popped
             * from the operator stack until the current token has now greater
             * precedence than the token at the top of the operator stack.
             *
             * NOTE: Under no circumstances should you NOT clone the operator
             * stack within this loop. It not desirable to mutate the stack that
             * it is currently iterating over, and could cause errors.
             */
            for (final String operator: new ArrayDeque<String>(this.operators)) {
                lastPrecedence = getPrecedence(operator);

                /*
                 * The current token now finally has greater precedence than the
                 * token at the top of the operator stack. Push it to the stack.
                 */
                if (currentPrecedence > lastPrecedence) {
                    this.operators.push(infixToken);
                    break;
                }

                /*
                 * While the conditions above have not been satisfied yet, we
                 * pop the top operator token out of the operator stack and then
                 * push it into output.
                 */
                final String popped = this.operators.pop();
                this.output.add(popped);
            }

            /*
             * The operator stack is now empty. The token has no choice but
             * to be pushed into the stack.
             */
            if (this.operators.isEmpty()) this.operators.push(infixToken);
        }

        /*
         * We pop the remaining operator tokens in the operator stack into the
         * output.
         */
        for (final String __: new ArrayDeque<String>(this.operators)) {
            final String popped = this.operators.pop();
            this.output.add(popped);
        }

        final ArrayList<String> result = new ArrayList<String>(this.output);
        this.output.clear();
        return result;
    }
}
