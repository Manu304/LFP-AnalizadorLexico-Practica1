package util;

/**
 * Clase que contiene métodos para la validación de chars.
 * 
 * @author Manu
 */
public class ValidadorChar {

    public static final char[] operatorSymbols = { '+', '-', '/', '*', '%' };
    public static final char[] agrupationSymbols = { '(', ')', '[', ']', '{', '}' };
    public static final char[] puntuacionSymbols = { '.', ',', ':', ';' };

    /**
     * Método que determina si un caracter es una letra.
     * 
     * @param c Caracter a evaluar
     * @return Retorna true si es el caracter es una letra del alfabeto (sin
     *         importar mayúsculas o minúsculas), de lo contrario retorna false.
     */
    public static boolean isLetter(char c) {
        c = Character.toLowerCase(c);
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        return false;
    }

    /**
     * Método que determina si un caracter es un dígito.
     * 
     * @param c Caracter a evaluar
     * @return Retorna true si es un dígito del 0 al 9, de lo contrario retorna
     *         false.
     */

    public static boolean isNumber(char c) {
        if (Character.isDigit(c)) {
            return true;
        }
        return false;
    }

    /**
     * Método que determina si un caracter es un simbolo operador.
     * 
     * @param c Caracter a evaluar
     * @return Retorna true si es un simbolo operador, de lo contrario retorna
     *         false.
     */

    public static boolean isOperatorSymbol(char c) {
        for (int i = 0; i < operatorSymbols.length; i++) {
            if (c == operatorSymbols[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que determina si un caracter es un simbolo de agrupación.
     * 
     * @param c Caracter a evaluar
     * @return Retorna true si es un simbolo de agrupación, de lo contrario retorna
     *         false.
     */

    public static boolean isAgrupationSymbol(char c) {
        for (int i = 0; i < agrupationSymbols.length; i++) {
            if (c == agrupationSymbols[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que determina si un caracter es un simbolo de puntuación.
     * 
     * @param c Caracter a evaluar
     * @return Retorna true si es un simbolo de puntuación, de lo contrario retorna
     *         false.
     */

    public static boolean isPuntuacionSymbols(char c) {
        for (int i = 0; i < puntuacionSymbols.length; i++) {
            if (c == puntuacionSymbols[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que determina si un caracter es un simbolo.
     * 
     * @param c Caracter a evaluar
     * @return Retorna true si es un simbolo, de lo contrario retorna false.
     */

    public static boolean isSymbol(char c) {
        return isAgrupationSymbol(c) || isOperatorSymbol(c) || isPuntuacionSymbols(c);
    }
    
    /**
     * Método que determina si un caracter pertenece al alfabeto definido para este automata
     * @param c Caracter a evaluar
     * @return Retorna true si el caracter pertenece al alfabeto, de lo contrario retorna false.
     */

    public static boolean isValidChar(char c){
        return isSymbol(c) || isLetter(c) || isNumber(c) || Character.isWhitespace(c);
    }

}
