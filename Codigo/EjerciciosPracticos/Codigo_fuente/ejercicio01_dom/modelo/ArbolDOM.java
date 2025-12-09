package ejercicio01_dom.modelo;

/**
 * Clase que representa el árbol DOM completo.
 * Gestiona la raíz del árbol y operaciones globales.
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class ArbolDOM {
    
    private NodoDOM raiz;  // Nodo raíz del árbol (siempre será <html>)
    
    /**
     * Constructor que inicializa el árbol con un nodo raíz HTML.
     */
    public ArbolDOM() {
        this.raiz = new NodoDOM("html", "");
    }
    
    /**
     * Obtiene el nodo raíz del árbol.
     * @return Nodo raíz
     */
    public NodoDOM getRaiz() {
        return raiz;
    }
    
    /**
     * Genera el código HTML completo del árbol.
     * @return String con todo el HTML formateado
     */
    public String generarHTMLCompleto() {
        return raiz.generarHTML(0);
    }
    
    /**
     * Agrega un nodo como hijo de otro nodo específico.
     * @param padre Nodo padre donde se agregará el hijo
     * @param etiqueta Etiqueta HTML del nuevo nodo
     * @param texto Texto del nuevo nodo
     * @return El nuevo nodo creado
     */
    public NodoDOM agregarNodo(NodoDOM padre, String etiqueta, String texto) {
        NodoDOM nuevoNodo = new NodoDOM(etiqueta, texto);
        padre.agregarHijo(nuevoNodo);
        return nuevoNodo;
    }
    
    /**
     * Elimina un nodo del árbol (excepto la raíz).
     * @param nodo Nodo a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean eliminarNodo(NodoDOM nodo) {
        // No permitir eliminar la raíz
        if (nodo == raiz) {
            return false;
        }
        
        NodoDOM padre = nodo.getPadre();
        if (padre != null) {
            return padre.eliminarHijo(nodo);
        }
        return false;
    }
    
    /**
     * Cuenta el total de nodos en el árbol.
     * @return Número total de nodos
     */
    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }
    
    private int contarNodosRecursivo(NodoDOM nodo) {
        int cuenta = 1;
        for (NodoDOM hijo : nodo.getHijos()) {
            cuenta += contarNodosRecursivo(hijo);
        }
        return cuenta;
    }
    
    /**
     * Limpia el árbol dejando solo la raíz HTML.
     */
    public void limpiar() {
        raiz.getHijos().clear();
    }
}
