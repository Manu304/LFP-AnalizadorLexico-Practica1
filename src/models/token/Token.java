package models.token;

import models.token.TipoToken;

/**
 *
 * @author Manu
 */
public class Token {

    private String cadena;
    private TipoToken tipo;
    private int fila;
    private int columIni;
    private int columFin;

    public Token(String cadena, TipoToken tipo, int columIni, int columFin) {
        this.cadena = cadena;
        this.tipo = tipo;
        this.columIni = columIni;
        this.columFin = columFin;
    }

    public Token(String cadena, TipoToken tipo, int fila, int columIni, int columFin) {
        this(cadena, tipo, columIni, columFin);
        this.fila = fila;
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumIni() {
        return columIni;
    }

    public void setColumIni(int columIni) {
        this.columIni = columIni;
    }

    public int getColumFin() {
        return columFin;
    }

    public void setColumFin(int columFin) {
        this.columFin = columFin;
    }

    @Override
    public String toString() {
        return "Token [cadena=" + cadena + "]";
    }
}
