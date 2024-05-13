/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator.eval;

import java.util.ArrayList;

/**
 * The lexer reads any given string to extract the individual smallest unit
 * it can extract, tokens. These are the smallest individual units that still
 * retains some form of meaning.
 *
 * @author Jeremy Lusuegro
 */
public class Lexer {
    private ArrayList<String> intFeed = new ArrayList<String>();
    private ArrayList<String> tokens = new ArrayList<String>();

    private boolean isInt(String character) {
        return character.matches("\\d");
    }

    private void tokenizeFeed() {
        // Do nothing if the feed is empty.
        if (!this.intFeed.isEmpty()) {
            String token = String.join("", this.intFeed);
            this.tokens.add(token);
            this.intFeed.clear();
        }
    }

    /**
     * Convert a string containing a Mathematical expression into an array of
     * tokens to be taken as input by a parser.
     * 
     * @param expression A Mathematic expression.
     * @return An array of tokens.
     */
    public ArrayList<String> lex(String expression) {
        for (final String character: expression.split("")) {
            // There is no reason to lex spaces.
            if (character.equals(" ")) continue;

            /*
             * The lexer does not know how long the digits of the number is, it
             * can only read each character in the expression one by one. We use
             * a feed in order to construct the number into a token.
             *
             * If it is established that the current character is not a number,
             * then it must be a valid binary operator. (+, -, *, /), and so we
             * can conclude the number can be tokenized.
             */
            if (isInt(character)) {
                this.intFeed.add(character);
                continue;
            } else tokenizeFeed();

            /*
             * By this point, it has already been established that the current
             * character is neither an integer nor space, so we can assume that
             * the next character is a binary operator which are guaranteed to
             * be one character.
             */
            this.tokens.add(character);
        }
        tokenizeFeed();

        final ArrayList<String> result = new ArrayList<String>(this.tokens);
        this.tokens.clear();  // Clear for next use.
        return result;
    }
}
