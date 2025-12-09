package ejercicio01_dom.controlador;

import ejercicio01_dom.modelo.ArbolDOM;
import ejercicio01_dom.modelo.NodoDOM;
import ejercicio01_dom.vista.VistaDOMSimulador;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador del Simulador DOM.
 * Gestiona la comunicación entre el modelo (ArbolDOM) y la vista (VistaDOMSimulador).
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class ControladorDOM {
    
    private ArbolDOM modelo;
    private VistaDOMSimulador vista;
    
    // Mapa para vincular nodos visuales (JTree) con nodos del modelo
    private Map<DefaultMutableTreeNode, NodoDOM> mapaVisualModelo;
    
    /**
     * Constructor del controlador.
     * @param modelo El modelo del árbol DOM
     * @param vista La vista del simulador
     */
    public ControladorDOM(ArbolDOM modelo, VistaDOMSimulador vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.mapaVisualModelo = new HashMap<>();
        
        // Vincular el nodo raíz visual con el nodo raíz del modelo
        mapaVisualModelo.put(vista.getNodoRaizVisual(), modelo.getRaiz());
        
        // Conectar controlador con la vista
        vista.setControlador(this);
        
        // Actualizar vista inicial
        actualizarVistaHTML();
    }
    
    /**
     * Agrega un nuevo nodo al árbol.
     */
    public void agregarNodo() {
        DefaultMutableTreeNode nodoVisualSeleccionado = vista.getNodoSeleccionado();
        
        // Validar que hay un nodo seleccionado
        if (nodoVisualSeleccionado == null) {
            vista.mostrarError("Selecciona un nodo padre en el árbol.");
            return;
        }
        
        String etiqueta = vista.getEtiquetaSeleccionada();
        String texto = vista.getTextoIngresado();
        
        // Obtener el nodo del modelo correspondiente
        NodoDOM nodoPadreModelo = mapaVisualModelo.get(nodoVisualSeleccionado);
        
        if (nodoPadreModelo == null) {
            vista.mostrarError("Error interno: nodo no encontrado en el modelo.");
            return;
        }
        
        // Crear nodo en el modelo
        NodoDOM nuevoNodoModelo = modelo.agregarNodo(nodoPadreModelo, etiqueta, texto);
        
        // Crear representación visual
        String textoVisual = texto.isEmpty() ? etiqueta : etiqueta + " – " + texto;
        DefaultMutableTreeNode nuevoNodoVisual = vista.agregarNodoVisual(nodoVisualSeleccionado, textoVisual);
        
        // Vincular nodo visual con nodo del modelo
        mapaVisualModelo.put(nuevoNodoVisual, nuevoNodoModelo);
        
        // Actualizar vista HTML
        actualizarVistaHTML();
        
        // Limpiar campo de texto
        vista.limpiarCampoTexto();
    }
    
    /**
     * Elimina el nodo seleccionado del árbol.
     */
    public void eliminarNodo() {
        DefaultMutableTreeNode nodoVisualSeleccionado = vista.getNodoSeleccionado();
        
        // Validar selección
        if (nodoVisualSeleccionado == null) {
            vista.mostrarError("Selecciona un nodo para eliminar.");
            return;
        }
        
        // No permitir eliminar la raíz
        if (nodoVisualSeleccionado == vista.getNodoRaizVisual()) {
            vista.mostrarError("No se puede eliminar el nodo raíz (html).");
            return;
        }
        
        // Confirmar eliminación
        int confirmacion = JOptionPane.showConfirmDialog(
            null, 
            "¿Estás seguro de eliminar este nodo y todos sus hijos?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }
        
        // Obtener nodo del modelo
        NodoDOM nodoModelo = mapaVisualModelo.get(nodoVisualSeleccionado);
        
        if (nodoModelo == null) {
            vista.mostrarError("Error interno: nodo no encontrado en el modelo.");
            return;
        }
        
        // Eliminar del modelo
        if (modelo.eliminarNodo(nodoModelo)) {
            // Eliminar del mapa (incluyendo hijos)
            eliminarDelMapaRecursivo(nodoVisualSeleccionado);
            
            // Eliminar de la vista
            vista.eliminarNodoVisual(nodoVisualSeleccionado);
            
            // Actualizar vista HTML
            actualizarVistaHTML();
        }
    }
    
    /**
     * Elimina recursivamente los nodos del mapa de vinculación.
     */
    private void eliminarDelMapaRecursivo(DefaultMutableTreeNode nodoVisual) {
        // Eliminar hijos primero
        for (int i = 0; i < nodoVisual.getChildCount(); i++) {
            eliminarDelMapaRecursivo((DefaultMutableTreeNode) nodoVisual.getChildAt(i));
        }
        // Eliminar el nodo actual
        mapaVisualModelo.remove(nodoVisual);
    }
    
    /**
     * Edita el nodo seleccionado.
     */
    public void editarNodo() {
        DefaultMutableTreeNode nodoVisualSeleccionado = vista.getNodoSeleccionado();
        
        // Validar selección
        if (nodoVisualSeleccionado == null) {
            vista.mostrarError("Selecciona un nodo para editar.");
            return;
        }
        
        // No permitir editar la raíz
        if (nodoVisualSeleccionado == vista.getNodoRaizVisual()) {
            vista.mostrarError("No se puede editar el nodo raíz (html).");
            return;
        }
        
        // Obtener nodo del modelo
        NodoDOM nodoModelo = mapaVisualModelo.get(nodoVisualSeleccionado);
        
        if (nodoModelo == null) {
            vista.mostrarError("Error interno: nodo no encontrado en el modelo.");
            return;
        }
        
        // Mostrar diálogo de edición
        String[] resultado = vista.mostrarDialogoEdicion(
            nodoModelo.getEtiqueta(), 
            nodoModelo.getTexto()
        );
        
        if (resultado != null) {
            // Actualizar modelo
            nodoModelo.setEtiqueta(resultado[0]);
            nodoModelo.setTexto(resultado[1]);
            
            // Actualizar vista
            String textoVisual = resultado[1].isEmpty() ? 
                resultado[0] : resultado[0] + " – " + resultado[1];
            vista.actualizarNodoVisual(nodoVisualSeleccionado, textoVisual);
            
            // Actualizar HTML
            actualizarVistaHTML();
        }
    }
    
    /**
     * Exporta el HTML generado a un archivo.
     */
    public void exportarHTML() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar HTML");
        fileChooser.setSelectedFile(new java.io.File("pagina.html"));
        
        int resultado = fileChooser.showSaveDialog(null);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
                if (!rutaArchivo.endsWith(".html")) {
                    rutaArchivo += ".html";
                }
                
                FileWriter writer = new FileWriter(rutaArchivo);
                writer.write("<!DOCTYPE html>\n");
                writer.write(modelo.generarHTMLCompleto());
                writer.close();
                
                vista.mostrarMensaje("HTML exportado exitosamente a:\n" + rutaArchivo);
            } catch (IOException e) {
                vista.mostrarError("Error al guardar el archivo: " + e.getMessage());
            }
        }
    }
    
    /**
     * Limpia todo el árbol dejando solo la raíz.
     */
    public void limpiarArbol() {
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "¿Estás seguro de limpiar todo el árbol?",
            "Confirmar limpieza",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Limpiar modelo
            modelo.limpiar();
            
            // Limpiar mapa (excepto raíz)
            mapaVisualModelo.clear();
            mapaVisualModelo.put(vista.getNodoRaizVisual(), modelo.getRaiz());
            
            // Limpiar vista
            vista.limpiarArbolVisual();
            
            // Actualizar HTML
            actualizarVistaHTML();
        }
    }
    
    /**
     * Actualiza el área de HTML en la vista con el código generado por el modelo.
     */
    private void actualizarVistaHTML() {
        String html = modelo.generarHTMLCompleto();
        vista.actualizarVistaHTML(html);
    }
}
