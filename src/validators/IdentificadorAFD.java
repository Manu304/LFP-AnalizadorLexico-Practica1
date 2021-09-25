package validators;

import models.token.*;
import models.validator.ModelAFD;
import util.ValidadorChar;
import java.util.List;

/**
 *
 * @author Manu
 */
public class IdentificadorAFD extends ModelAFD{

    public static final int LETRA = 0, DIGITO = 1;

    public IdentificadorAFD(List<Token> tokens, int posicion, char[] texto) {
        super(tokens, posicion, texto, 2, 2, 1);

    }

    @Override
    public Token crearToken(String textoToken) {
        TipoToken tipoToken = TipoToken.ERROR;
        if (estadoActual == estadosAceptacion[0]) {
            tipoToken = TipoToken.IDENTIFICADOR;
        }
        return new Token(textoToken, tipoToken, posicionInicial, posicion);
    }

    @Override
    public int getCaracterInt(char caracter) {
        int caracterInt = ERROR;
        if (ValidadorChar.isNumber(caracter)) {
            caracterInt = DIGITO;
        } else if (ValidadorChar.isLetter(caracter)) {
            caracterInt = LETRA;
        }
        return caracterInt;
    }

    @Override
    public void inicializarArreglos() {
        tablaTransiciones[0][0] = 1;
        tablaTransiciones[0][1] = ERROR;
        tablaTransiciones[1][0] = 1;
        tablaTransiciones[1][1] = 1;

        estadosAceptacion[0] = 1;
    }

    @Override
    public void iniciar() {
        transiciones();
    }
    
}
