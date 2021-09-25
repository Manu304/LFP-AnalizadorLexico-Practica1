package principal;

import java.util.*;
import models.token.Token;
import models.validator.ModelAFD;
import util.ValidadorChar;
import validators.*;

/**
 *
 * @author Manu
 */
public class ValidadorTokens {

    private List<Token> tokens;
    private char[] text;
    private int posicion;
    

    public ValidadorTokens(String text, List<Token> tokens) {
        this.text = text.toCharArray();
        this.tokens = tokens;
        this.posicion = 0;
        iniciar();

        System.out.println("-----------IMPRIMIENDO TOKENS----------");
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    private void iniciar() {
        ModelAFD afd = null;
        while (posicion < text.length) {

            char tmp = text[posicion];
            if (!Character.isWhitespace(tmp)) {
                if (ValidadorChar.isLetter(tmp)) {
                    afd = new IdentificadorAFD(tokens, posicion, text);
                } else if (ValidadorChar.isNumber(tmp)) {
                    afd = new NumeroAFD(tokens, posicion, text);
                } else if (ValidadorChar.isSymbol(tmp)) {
                    afd = new SignoAFD(tokens, posicion, text);
                }

                posicion = afd.getPosicion();
            }
            posicion++;

        }
    }



}
