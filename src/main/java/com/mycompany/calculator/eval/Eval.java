/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator.eval;

import java.util.ArrayList;

/**
 * Mathematical expression evaluator.
 *
 * @author Jeremy Lusuegro
 */
public class Eval {
    /**
     * Evaluate the Mathematical expression, returning the numeric result. It
     * must be in infix notation, not in Reverse Polish Notation.
     * 
     * Infix Notation: 1 + 2 - 3
     * Reverse Polish Notation: 1 2 + 3 -
     * 
     * @param expression A Mathematical expression in infix notation.
     * @return The result as string.
     */
    public static String evaluate(String expression) throws Exception {
        if (expression.replaceAll("\\s", "").equals("")) {
            return "No expression provided.";
        }
        
        final Lexer lexer = new Lexer();
        final Parser parser = new Parser();
        final RPN calculator = new RPN();

        final ArrayList<String> tokens = lexer.lex(expression);
        final ArrayList<String> rpnTokens = parser.parse(tokens);
        String result;

        try {
            result = "" + calculator.evaluate(rpnTokens);
        } catch (ArithmeticException exception) {
            result = "NaN";
        }

        if (result.equals("Infinity")) {
            result = "Can't divide by zero!";
        }

        return result;
    }
}
