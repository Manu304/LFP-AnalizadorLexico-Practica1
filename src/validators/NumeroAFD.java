package validators;

import models.token.*;
import models.validator.ModelAFD;
import util.ValidadorChar;
import java.util.List;

/**
 *
 * @author Manu
 */
public class NumeroAFD extends ModelAFD {

    public static final int CHAR_DIGITO = 0, CHAR_PUNTO = 1;

    public NumeroAFD(List<Token> tokens, int posicion, char[] texto) {
        super(tokens, posicion, texto, 4, 2, 2);

    }

    @Override
    public Token crearToken(String textoToken) {
        TipoToken tipoToken = TipoToken.ERROR;
        System.out.println(estadoActual + " estado actual xdxd");

        if (estadoActual == estadosAceptacion[0]) {
            tipoToken = TipoToken.NUMERO_ENTERO;
        } else if (estadoActual == estadosAceptacion[1]) {
            tipoToken = TipoToken.NUMERO_DECIMAL;
        }

        return new Token(textoToken, tipoToken, posicionInicial, posicion);

    }

    @Override
    public int getCaracterInt(char caracter) {
        int caracterInt = ERROR;

        System.out.println("analizando en num, caracter =  " + caracter);

        if (ValidadorChar.isNumber(caracter)) {
            caracterInt = CHAR_DIGITO;
        } else if (caracter == '.') {
            caracterInt = CHAR_PUNTO;
        }
        return caracterInt;
    }

    @Override
    public void inicializarArreglos() {
        tablaTransiciones[0][0] = 1;
        tablaTransiciones[0][1] = ERROR;
        tablaTransiciones[1][0] = 1;
        tablaTransiciones[1][1] = 2;
        tablaTransiciones[2][0] = 3;
        tablaTransiciones[2][1] = ERROR;
        tablaTransiciones[3][0] = 3;
        tablaTransiciones[3][1] = ERROR;

        estadosAceptacion[0] = 1;
        estadosAceptacion[1] = 3;
    }

    @Override
    public void iniciar() {
        transiciones();
    }
}
