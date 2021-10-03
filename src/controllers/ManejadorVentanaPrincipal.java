package controllers;

import archives.ManejoArchivo;
import java.util.*;
import javax.swing.*;
import models.token.Token;
import services.AutomataFinito;
import services.GeneralAFD;
import user_interface.VentanaBuscar;
import user_interface.VentanaPrincipal;
import user_interface.VentanaValidarTexto;
import user_interface.reports.TipoReporte;
import user_interface.reports.VentanaReportes;

public class ManejadorVentanaPrincipal {

    private VentanaPrincipal frame;
    //private ValidadorTokens validador;
    private AutomataFinito validador;
    private List<Token> tokensValidos;
    private List<Token> tokensInvalidos;

    public ManejadorVentanaPrincipal(VentanaPrincipal frame) {
        this.frame = frame;
        tokensValidos = new ArrayList<>();
        tokensInvalidos = new ArrayList<>();
    }

    public void validarTexto() {
        String textEditor = frame.getjTextPaneEditor().getText();
        if (textEditor != null && !textEditor.isBlank()) {
            validador = new GeneralAFD(textEditor.toCharArray());
            tokensValidos = validador.getTokens(true);
            tokensInvalidos = validador.getTokens(false);
            new VentanaValidarTexto(validador.getMensajes());
        } else {
            JOptionPane.showMessageDialog(null, "No hay contenido para validar, escriba algo primero en el area de texto", "Area de texto vacía", JOptionPane.WARNING_MESSAGE);
            frame.getjTextPaneEditor().setText("");
        }

    }

    public void mostrarBuscador() {
        JTextPane editorArea = frame.getjTextPaneEditor();
        if (editorArea.getText() != null && !editorArea.getText().isBlank()) {
            new VentanaBuscar(frame.getjTextPaneEditor());
        } else {
            JOptionPane.showMessageDialog(null, "No hay contenido para buscar, escriba algo primero en el area de texto", "Area de texto vacía", JOptionPane.WARNING_MESSAGE);
            frame.getjTextPaneEditor().setText("");
        }

    }

    public void mostrarReportes(TipoReporte tipo) {

        List<Token> listadoTokens = switch (tipo) {
            case TOKENS ->
                tokensValidos;
            case RECUENTO ->
                tokensValidos;
            case ERRORES ->
                tokensInvalidos;
        };
        if (listadoTokens.isEmpty()) {
            String mensaje = switch (tipo) {
                case TOKENS, RECUENTO ->
                    "válidos";
                case ERRORES ->
                    "inválidos";
            };
            JOptionPane.showMessageDialog(null, "No hay tokens " + mensaje + " para mostrar en el reporte", "No hay tokens", JOptionPane.WARNING_MESSAGE);
        } else {
            if ((tipo == TipoReporte.TOKENS || tipo == TipoReporte.RECUENTO) && !tokensInvalidos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede mostrar el reporte de tokens cuando el texto contiene errores", "No se puede mostrar reporte", JOptionPane.WARNING_MESSAGE);
            } else {
                new VentanaReportes(listadoTokens, tipo);
            }

        }

    }

    public void guardarArchivo() {
        String texto = frame.getjTextPaneEditor().getText();
        if (texto != null && !texto.isBlank()) {
            ManejoArchivo.guardarArchivoTxt(ManejoArchivo.getFileChooserPath(), texto);
        } else {
            JOptionPane.showMessageDialog(null, "No hay contenido para guardar, escriba algo primero en el area de texto", "Area de texto vacía", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void cargarArchivo() {
        List<String> lineas = ManejoArchivo.getLinesTextFile(ManejoArchivo.getFileChooserPath());
        JTextPane panelEditor = frame.getjTextPaneEditor();
        panelEditor.setText("");
        if (!lineas.isEmpty()) {
            String salto;
            for (int i = 0; i < lineas.size(); i++) {
                salto = (i + 1 == lineas.size()) ? "" : "\n";
                panelEditor.setText(panelEditor.getText() + lineas.get(i) + salto);
            }
        }
    }

}
