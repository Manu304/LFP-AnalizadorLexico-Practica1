package controllers;

import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import user_interface.VentanaBuscar;

/**
 *
 * @author Manu
 */
public class ManejadorVentanaBuscar {

    private VentanaBuscar frame;
    //private String textActual;
    //private int coincidenciaTotal;
    //private int coincidenciaActual;

    public ManejadorVentanaBuscar(VentanaBuscar frame) {
        this.frame = frame;
        //coincidenciaActual = 0;
    }

    public void activarBotones() {
        boolean activar = false;
        if (frame.getTextBuscar().getText() != null && !frame.getTextBuscar().getText().isBlank()) {
            activar = true;
        }
        frame.getBotonAnterior().setEnabled(activar);
        frame.getBotonSiguiente().setEnabled(activar);
    }
/*
    private int getCoincidencias(String textBuscar, String textTotal) {
        int posicion = 0;
        int contador = 0;
        while (posicion > -1) {
            /*if (posicion > 0 && (textTotal.charAt(posicion - 1) == '\n')) {
                posicion--;
                System.out.println("estoy en el while");

            }*/
            /*
            posicion = textTotal.indexOf(textBuscar, posicion + textBuscar.length());
            contador++;
        }
        System.out.println("contador : " + contador);
        return contador;
    }

    private void cambiarLabelBusqueda(String textBuscar) {
        if (textActual.equals(textBuscar)) {
            coincidenciaActual++;
        } else {
            coincidenciaActual = 0;
            coincidenciaTotal = 0;
        }
        frame.getLabelBusqueda().setText("Coincidencia " + coincidenciaActual + " de " + coincidenciaTotal);

    }
    */

    public void highLight(JTextComponent component, String patteren) {
        try {
            Document doc = component.getDocument();
            //String text = component.getText(0, doc.getLength());
            int pos = component.getCaretPosition();
            boolean found = false;
            int findLength = patteren.length();
            // Rest the search position if we're at the end of the document
            if (pos + findLength > doc.getLength()) {
                pos = 0;
            }
            while (pos + findLength <= doc.getLength()) {
                // Extract the text from teh docuemnt
                String match = doc.getText(pos, findLength).toLowerCase();
                // Check to see if it matches or request
                if (match.equals(patteren)) {
                    found = true;
                    break;
                }
                pos++;
            }

            if (found) {
                component.setSelectionStart(pos);
                component.setSelectionEnd(pos + patteren.length());
                component.getCaret().setSelectionVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
