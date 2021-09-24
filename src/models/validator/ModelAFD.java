package models.validator;

import java.util.*;
import models.token.Token;

/**
 *
 * @author Manu
 */
public abstract class ModelAFD {

    protected List<Token> tokens;
    protected int[][] tablaTransiciones;
    protected int[] estadosAceptacion;
    protected int posicion, estadoActual, posicionInicial;
    protected char[] texto;
    protected static final int ERROR = -1;

    public ModelAFD(List<Token> tokens, int posicion, char[] texto, int cantEstados, int caracteresEnAlfabeto,
            int cantEstadosAceptacion) {
        this.posicion = posicion;
        this.texto = texto;
        this.posicionInicial = posicion;
        this.tokens = tokens;
        this.tablaTransiciones = new int[cantEstados][caracteresEnAlfabeto];
        this.estadosAceptacion = new int[cantEstadosAceptacion];
        inicializarArreglos();
        iniciar();
    }

    public int getPosicion() {
        return posicion;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public abstract void crearToken(String textoToken);

    public abstract int getCaracterInt(char caracter);

    public abstract void inicializarArreglos();

    public abstract void iniciar();

    public void transiciones() {
        estadoActual = 0;
        char tmp;
        String tokenText = "";

        while (posicion < texto.length && !Character.isWhitespace(tmp = texto[posicion]) && estadoActual != ERROR) {
            int estadoTmp = getEstadoSiguiente(estadoActual, texto[posicion]);
            System.out.println("estado temporal = " + estadoTmp);
            tokenText += tmp;
            estadoActual = estadoTmp;
            System.out.println("me movi con " + tmp + " estado: " + estadoActual);
            posicion++;
        }

        if (!tokenText.isBlank()) {
            System.out.println(estadoActual + "EN MODELO ESTADO ACTUAL");
            crearToken(tokenText);
        }
    }

    public int getEstadoSiguiente(int actual, char caracter) {
        int siguiente = ERROR;
        int caracterInt = getCaracterInt(caracter);
        if (perteneceAlfabeto(caracter)) {
            System.out.println("analizando " + caracter + " asignado = " + caracterInt);
            System.out.println("getEStado actual = " + actual);
            siguiente = tablaTransiciones[actual][caracterInt];
            System.out.println("valor en matriz " + siguiente);
        }
        return siguiente;
    }

    private boolean perteneceAlfabeto(char caracter) {
        if (getCaracterInt(caracter) != ERROR) {
            return true;
        }

        return false;
    }
}
