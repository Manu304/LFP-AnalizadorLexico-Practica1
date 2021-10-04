package controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import user_interface.VentanaBuscar;

/**
 *
 * @author Manu
 */
public class ManejadorVentanaBuscar {

    private VentanaBuscar frame;
    private String textActual;
    private int coincidenciaTotal;
    private int coincidenciaActual;
    private boolean caseSensitive;

    public ManejadorVentanaBuscar(VentanaBuscar frame) {
        this.frame = frame;
        coincidenciaActual = 0;
        caseSensitive = frame.getjCheckBox1().isSelected();
    }

    public void activarBotones() {
        boolean activar = false;
        if (frame.getTextBuscar().getText() != null && !frame.getTextBuscar().getText().isBlank()) {
            activar = true;
        }
        frame.getBotonSiguiente().setEnabled(activar);
        frame.getBotonAnterior().setEnabled(activar);
    }

    public void buscarPalabra(JTextComponent componente, String cadena, boolean caseSensitive, boolean buscarSiguiente) {
        this.caseSensitive = caseSensitive; //VERIFICAR SI AUN SE PUEDE UTILIZAR
        String textoArea;
        int posicion = componente.getCaretPosition(), textoAreaTamanio = 0;
        int encontrado;
        try {
            textoAreaTamanio = componente.getDocument().getLength();
            textoArea = componente.getDocument().getText(0, textoAreaTamanio);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
            textoArea = "";
        }

        if (!caseSensitive) {
            textoArea = textoArea.toLowerCase();
            cadena = cadena.toLowerCase();
        }

        int ultimoResultado = buscarSiguiente ? textoArea.lastIndexOf(cadena) : textoArea.indexOf(cadena);
        boolean condicionBuscar = buscarSiguiente ? posicion >= textoAreaTamanio : false;

        if (condicionBuscar || (posicion - cadena.length()) == ultimoResultado) {
            posicion = buscarSiguiente ? 0 : textoAreaTamanio + 2;
            System.out.println("he encontrado el ultimo, ahora posicion = " + posicion);
        }
        if (buscarSiguiente) {
            encontrado = textoArea.indexOf(cadena, posicion - 1);
        } else {
            encontrado = textoArea.lastIndexOf(cadena, posicion - cadena.length() - 1);
        }

        if (encontrado > -1) {
            componente.setSelectionStart(encontrado);
            componente.setSelectionEnd(encontrado + cadena.length());
            componente.getCaret().setSelectionVisible(true);
            cambiarLabel(textoArea, cadena);
        } else {
            JOptionPane.showMessageDialog(null, "No se han encontrado coincidencias", "No encontrado", JOptionPane.INFORMATION_MESSAGE);
        }
        System.out.println("tamañio: " + textoAreaTamanio + " posicion: " + posicion + " encontrado: " + encontrado);
    }

    public void cambiarLabel(String textoTotal, String cadena) {
        if (textActual == null || !cadena.equals(textActual) || caseSensitive != frame.getjCheckBox1().isSelected()) {
            caseSensitive = frame.getjCheckBox1().isSelected();
            textActual = cadena;
            int posicion = 0, contador = 0;
            while (posicion > -1 && posicion + cadena.length() <= textoTotal.length()) {
                posicion = textoTotal.indexOf(cadena, posicion + cadena.length());
                contador++;
            }
            coincidenciaTotal = contador;
        }
        coincidenciaActual++;
        if (coincidenciaActual > coincidenciaTotal) {
            coincidenciaActual = 1;
        }
        frame.getLabelBusqueda().setText("Coincidencia " + coincidenciaActual + " de " + coincidenciaTotal);

    }

}
