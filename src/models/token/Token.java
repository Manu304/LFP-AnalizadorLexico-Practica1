package models.token;

/**
 * Clase modelo para la creacion de Tokens
 *
 * @author Manu
 */
public class Token {

    private String cadena;
    private TipoToken tipo;
    private int fila;
    private int columIni;
    private int columFin;
    private int cantidad;

    /**
     * Constructor del objeto Token
     * 
     * @param cadena   valor que almacenará el toquen a crear
     * @param tipo     Tipo de token que se va a crear
     * @param columIni Posición inicial del token en la fila
     * @param columFin Posición final del token en la fila
     */

    public Token(String cadena, TipoToken tipo, int columIni, int columFin) {
        this.cadena = cadena;
        this.tipo = tipo;
        this.columIni = columIni;
        this.columFin = columFin;
        this.cantidad = 1;
    }

    /**
     * Constructor del objeto Token
     * 
     * @param cadena   valor que almacenará el toquen a crear
     * @param tipo     Tipo de token que se va a crear
     * @param fila     Fila donde se encuentra el token a crear
     * @param columIni Posición inicial del token en la fila
     * @param columFin Posición final del token en la fila
     */

    public Token(String cadena, TipoToken tipo, int fila, int columIni, int columFin) {
        this(cadena, tipo, columIni, columFin);
        this.fila = fila;
    }

    /**
     * Método para obtener el tipo de token
     * 
     * @return Retornar el tipo del token, si no lo tiene asignado retorna null.
     */

    public TipoToken getTipo() {
        return tipo;
    }

    /**
     * Método para obtener el valor de la cadena del token
     * 
     * @return Retorna un string con el contenido del token
     */

    public String getCadena() {
        return cadena;
    }

    /**
     * Método para obtener la fila en la que se encuentra el token
     * 
     * @return Retorna el número de fila en la que está el token.
     */

    public int getFila() {
        return fila;
    }

    /**
     * Método para cambmiar el número de fila donde se encuentra el token
     * 
     * @param fila Número de fila en la que se encuentra el token
     */

    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * Método para obtener la columna en la que inicia el token
     * 
     * @return Retorna el número de columna en la que inicia el token.
     */

    public int getColumIni() {
        return columIni;
    }

    /**
     * Método para cambmiar el número de columna donde inicia el token
     * 
     * @param columIni Número de columna en la que inicia el token
     */

    public void setColumIni(int columIni) {
        this.columIni = columIni;
    }

    /**
     * Método para obtener la columna en la que termina el token
     * 
     * @return Retorna el número de columna en la que termina el token.
     */

    public int getColumFin() {
        return columFin;
    }

    /**
     * Método para cambmiar el número de columna donde termina el token
     * 
     * @param columFin Número de columna en la que termina el token
     */

    public void setColumFin(int columFin) {
        this.columFin = columFin;
    }

    /**
     * Método para obtener la cantidad de veces que se repite el token
     * 
     * @return Valor entero que define cuantas veces se repite un token.
     */

    public int getCantidad() {
        return cantidad;
    }

    /**
     * Método para aumentar la cantidad de veces que se repite un toquen de unidad
     * en unidad.
     */

    public void setCantidad() {
        this.cantidad++;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cadena == null) ? 0 : cadena.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Token other = (Token) obj;
        if (cadena == null) {
            if (other.cadena != null)
                return false;
        } else if (!cadena.equals(other.cadena))
            return false;
        if (tipo != other.tipo)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Token [cadena=" + cadena + " cantidad=" + cantidad + "]";
    }
}
