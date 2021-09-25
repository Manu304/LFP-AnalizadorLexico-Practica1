package validators;

import models.token.*;
import models.validator.ModelAFD;
import util.ValidadorChar;
import java.util.List;

/**
 *
 * @author Manu
 */
public class SignoAFD extends ModelAFD {

    public static final int CHAR_AGRUPACION = 0, CHAR_OPERADOR = 1, CHAR_PUNTUACION = 2;

    public SignoAFD(List<Token> tokens, int posicion, char[] texto) {
        super(tokens, posicion, texto, 4, 3, 3);
    }

    @Override
    public Token crearToken(String textoToken) {
        TipoToken tipoToken = TipoToken.ERROR;

        if (estadoActual == estadosAceptacion[0]) {
            tipoToken = TipoToken.SIG_AGRUPACION;
        } else if (estadoActual == estadosAceptacion[1]) {
            tipoToken = TipoToken.SIG_OPERADOR;
        } else if (estadoActual == estadosAceptacion[2]) {
            tipoToken = TipoToken.SIG_PUNTUACION;
        }
        return new Token(Character.toString(textoToken.charAt(0)), tipoToken, posicionInicial, posicion);

    }

    @Override
    public int getCaracterInt(char caracter) {
        int caracterInt = ERROR;

        if (ValidadorChar.isAgrupationSymbol(caracter)) {
            caracterInt = CHAR_AGRUPACION;
        } else if (ValidadorChar.isOperatorSymbol(caracter)) {
            caracterInt = CHAR_OPERADOR;
        } else if (ValidadorChar.isPuntuacionSymbols(caracter)) {
            caracterInt = CHAR_PUNTUACION;
        }

        return caracterInt;
    }

    @Override
    public void inicializarArreglos() {
        for (int i = 0; i < tablaTransiciones.length; i++) {
            for (int j = 0; j < tablaTransiciones[i].length; j++) {
                tablaTransiciones[i][j] = ERROR;
            }
        }

        tablaTransiciones[0][0] = 1;
        tablaTransiciones[0][1] = 2;
        tablaTransiciones[0][2] = 3;

        estadosAceptacion[0] = 1;
        estadosAceptacion[1] = 2;
        estadosAceptacion[2] = 3;
    }

    @Override
    public void iniciar() {
        transiciones();

    }

    @Override
    public void transiciones() {
        estadoActual = 0;
        char tmp;
        String tokenText = "";

        if (posicion < texto.length && !Character.isWhitespace(tmp = texto[posicion]) && estadoActual != ERROR) {
            int estadoTmp = getEstadoSiguiente(estadoActual, texto[posicion]);
            tokenText += tmp;
            estadoActual = estadoTmp;
            posicion++;
        }

        if (!tokenText.isBlank()) {
            addToken(crearToken(tokenText));
        }
    }
}