/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Manu
 */
public class ValidadorChar {

    public static final char[] operatorSymbols = { '+', '-', '/', '*', '%' };
    public static final char[] agrupationSymbols = { '(', ')', '[', ']', '{', '}' };
    public static final char[] puntuacionSymbols = { '.', ',', ':', ';' };

    public static boolean isLetter(char c) {
        c = Character.toLowerCase(c);
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        return false;
    }

    public static boolean isNumber(char c) {
        if (Character.isDigit(c)) {
            return true;
        }
        return false;
    }

    public static boolean isOperatorSymbol(char c) {
        for (int i = 0; i < operatorSymbols.length; i++) {
            if (c == operatorSymbols[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAgrupationSymbol(char c) {
        for (int i = 0; i < agrupationSymbols.length; i++) {
            if (c == agrupationSymbols[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPuntuacionSymbols(char c) {
        for (int i = 0; i < puntuacionSymbols.length; i++) {
            if (c == puntuacionSymbols[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSymbol(char c) {
        if (isAgrupationSymbol(c) || isOperatorSymbol(c) || isPuntuacionSymbols(c)) {
            return true;
        }
        return false;
    }

}
