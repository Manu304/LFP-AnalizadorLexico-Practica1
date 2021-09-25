package principal;

import java.util.*;

import models.token.TipoToken;
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
            if (ValidadorChar.isValidChar(tmp) && !Character.isWhitespace(tmp)) {
                if (ValidadorChar.isLetter(tmp)) {
                    afd = new IdentificadorAFD(tokens, posicion, text);
                } else if (ValidadorChar.isNumber(tmp)) {
                    afd = new NumeroAFD(tokens, posicion, text);
                } else if (ValidadorChar.isSymbol(tmp)) {
                    afd = new SignoAFD(tokens, posicion, text);
                }

                posicion = afd.getPosicion();
                System.out.println("posicion act =" + posicion);
            } else {
                if (!ValidadorChar.isValidChar(tmp)) {
                    tokens.add(new Token(Character.toString(tmp), TipoToken.ERROR, posicion, posicion+1));
                }
                posicion++;
            }

        }
    }

    public boolean isCadenaValida() {
        for (Token e : tokens) {
            if (e.getTipo() == TipoToken.ERROR) {
                return false;
            }
        }
        return true;
    }

}
