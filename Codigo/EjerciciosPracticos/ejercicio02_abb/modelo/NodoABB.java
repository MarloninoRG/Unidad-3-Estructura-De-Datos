package ejercicio02_abb.modelo;

/**
 * Representa un nodo del Árbol Binario de Búsqueda.
 * Cada nodo contiene un valor entero y referencias a sus hijos izquierdo y derecho.
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class NodoABB {
    
    private int valor;
    private NodoABB izquierdo;
    private NodoABB derecho;
    
    // Coordenadas para el dibujo (usadas por la vista)
    private int x;
    private int y;
    
    /**
     * Constructor del nodo.
     * @param valor Valor entero del nodo
     */
    public NodoABB(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
        this.x = 0;
        this.y = 0;
    }
    
    // Getters y Setters
    public int getValor() { return valor; }
    public void setValor(int valor) { this.valor = valor; }
    
    public NodoABB getIzquierdo() { return izquierdo; }
    public void setIzquierdo(NodoABB izquierdo) { this.izquierdo = izquierdo; }
    
    public NodoABB getDerecho() { return derecho; }
    public void setDerecho(NodoABB derecho) { this.derecho = derecho; }
    
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    
    /**
     * Verifica si el nodo es una hoja (sin hijos).
     */
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }
    
    /**
     * Cuenta el número de hijos del nodo.
     */
    public int contarHijos() {
        int cuenta = 0;
        if (izquierdo != null) cuenta++;
        if (derecho != null) cuenta++;
        return cuenta;
    }
}
