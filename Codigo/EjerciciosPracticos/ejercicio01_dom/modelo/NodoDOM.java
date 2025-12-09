package ejercicio01_dom.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un nodo del árbol DOM.
 * Cada nodo tiene una etiqueta HTML, contenido de texto opcional y puede tener múltiples hijos.
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class NodoDOM {
    
    private String etiqueta;      // Nombre de la etiqueta HTML (html, body, p, h1, etc.)
    private String texto;         // Contenido de texto del nodo
    private List<NodoDOM> hijos;  // Lista de nodos hijos
    private NodoDOM padre;        // Referencia al nodo padre
    
    /**
     * Constructor para crear un nodo DOM.
     * @param etiqueta Nombre de la etiqueta HTML
     * @param texto Contenido de texto (puede ser vacío)
     */
    public NodoDOM(String etiqueta, String texto) {
        this.etiqueta = etiqueta;
        this.texto = texto;
        this.hijos = new ArrayList<>();
        this.padre = null;
    }
    
    /**
     * Agrega un nodo hijo a este nodo.
     * @param hijo Nodo a agregar como hijo
     */
    public void agregarHijo(NodoDOM hijo) {
        hijo.setPadre(this);
        this.hijos.add(hijo);
    }
    
    /**
     * Elimina un nodo hijo de este nodo.
     * @param hijo Nodo a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean eliminarHijo(NodoDOM hijo) {
        return this.hijos.remove(hijo);
    }
    
    /**
     * Genera el código HTML de este nodo y sus descendientes.
     * @param nivel Nivel de indentación actual
     * @return String con el HTML formateado
     */
    public String generarHTML(int nivel) {
        StringBuilder sb = new StringBuilder();
        String indent = "  ".repeat(nivel);
        
        // Etiqueta de apertura
        sb.append(indent).append("<").append(etiqueta).append(">");
        
        // Si tiene texto, lo agrega
        if (texto != null && !texto.isEmpty()) {
            sb.append(texto);
        }
        
        // Si tiene hijos, genera su HTML recursivamente
        if (!hijos.isEmpty()) {
            sb.append("\n");
            for (NodoDOM hijo : hijos) {
                sb.append(hijo.generarHTML(nivel + 1));
            }
            sb.append(indent);
        }
        
        // Etiqueta de cierre
        sb.append("</").append(etiqueta).append(">\n");
        
        return sb.toString();
    }
    
    // Getters y Setters
    public String getEtiqueta() { return etiqueta; }
    public void setEtiqueta(String etiqueta) { this.etiqueta = etiqueta; }
    
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    
    public List<NodoDOM> getHijos() { return hijos; }
    
    public NodoDOM getPadre() { return padre; }
    public void setPadre(NodoDOM padre) { this.padre = padre; }
    
    /**
     * Representación del nodo para mostrar en el JTree.
     */
    @Override
    public String toString() {
        if (texto != null && !texto.isEmpty()) {
            return etiqueta + " – " + texto;
        }
        return etiqueta;
    }
}

