package models.validator;

import java.util.*;
import models.token.Token;

/**
 * Clase modelo para la creación de automatas finitos deterministas.
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

    /**
     * Contructor del objeto modelo para automatas finitos deterministas
     * 
     * @param tokens                Listado de tokens a rellenar cuando se
     *                              encuentren tokens validos o no validos.
     * @param posicion              Posición del texto en la que comenzará a
     *                              analizar el autómata.
     * @param texto                 Arreglo de chars que analizará el autómata, es
     *                              el texto que se va a analizar.
     * @param cantEstados           Cantidad de estados que posee el autómata
     * @param caracteresEnAlfabeto  Cantidad de caracteres que conforman el alfabeto
     *                              del autómata.
     * @param cantEstadosAceptacion Cantidad de estados de aceptación que posee el
     *                              autómata.
     */

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

    /**
     * Método para obtener la posición actual del autómata.
     * 
     * @return Retorna la posición del autómata en el texto.
     */

    public int getPosicion() {
        return posicion;
    }

    /**
     * Método para obtener la lista de tokens generados hasta el momento.
     * 
     * @return Retorna una lista de tokens válidos y/o no válidos.
     */

    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * Método para cambiar la lista de tokens acumulados.
     * 
     * @param tokens Lista de nuevos tokens acumulados.
     */

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Método en el que se define el proceso para crear el token una vez finalizado
     * el autómata.
     * 
     * @param textoToken Cadena que almacenará el token a crear.
     */

    public abstract void crearToken(String textoToken);

    /**
     * Método en el que se define el proceso de obtención del valor númerico
     * representativo de un caracter dentro del autómata.
     * 
     * @param caracter Caracter a evaluar.
     * @return Retorna el índice correspondiente en la matriz de transición si es un
     *         caracter perteneciente al alfabeto, de lo contrario retorna -1.
     */
    public abstract int getCaracterInt(char caracter);

    /**
     * Método en el que se definen la matriz de transición para la tabla de
     * transiciones y los estados de aceptación del autómata.
     */
    public abstract void inicializarArreglos();

    /**
     * Método en el que se define cómo se inicia el autómata
     */
    public abstract void iniciar();

    /**
     * Método donde el autómata lee el texto ingresado y se mueve a través de la
     * tabla de transiciones.
     */

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

    /**
     * Método en el que se obtiene el siguiente estado del autómata.
     * 
     * @param actual   Estado en donde se encuentra actualmente el autómata.
     * @param caracter Caracter que se está evaluando
     * @return Retorna el valor del estado al que se mueve el autómata según la
     *         posición actual y el caracter evaluado.
     */

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
