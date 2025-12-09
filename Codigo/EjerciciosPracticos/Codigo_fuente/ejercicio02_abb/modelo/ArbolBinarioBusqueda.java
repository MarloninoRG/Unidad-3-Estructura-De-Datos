package ejercicio02_abb.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de un Árbol Binario de Búsqueda (ABB).
 * 
 * Propiedad fundamental: Para cada nodo, todos los valores en el subárbol
 * izquierdo son menores, y todos los valores en el subárbol derecho son mayores.
 * 
 * Operaciones implementadas:
 * - Insertar, Eliminar, Buscar
 * - Recorridos: InOrden, PreOrden, PostOrden
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class ArbolBinarioBusqueda {
    
    private NodoABB raiz;
    
    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }
    
    public NodoABB getRaiz() { return raiz; }
    
    /**
     * Verifica si el árbol está vacío.
     */
    public boolean estaVacio() {
        return raiz == null;
    }
    
    // ==================== INSERCIÓN ====================
    
    /**
     * Inserta un nuevo valor en el árbol.
     * @param valor Valor a insertar
     * @return true si se insertó, false si ya existía (no duplicados)
     */
    public boolean insertar(int valor) {
        if (buscar(valor) != null) {
            return false; // No permitir duplicados
        }
        raiz = insertarRecursivo(raiz, valor);
        return true;
    }
    
    private NodoABB insertarRecursivo(NodoABB nodo, int valor) {
        // Caso base: encontramos posición vacía
        if (nodo == null) {
            return new NodoABB(valor);
        }
        
        // Decidir si ir a izquierda o derecha
        if (valor < nodo.getValor()) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), valor));
        } else {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), valor));
        }
        
        return nodo;
    }
    
    // ==================== BÚSQUEDA ====================
    
    /**
     * Busca un valor en el árbol.
     * @param valor Valor a buscar
     * @return El nodo encontrado o null si no existe
     */
    public NodoABB buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }
    
    private NodoABB buscarRecursivo(NodoABB nodo, int valor) {
        // Caso base: no encontrado o encontrado
        if (nodo == null || nodo.getValor() == valor) {
            return nodo;
        }
        
        // Buscar en subárbol correspondiente
        if (valor < nodo.getValor()) {
            return buscarRecursivo(nodo.getIzquierdo(), valor);
        } else {
            return buscarRecursivo(nodo.getDerecho(), valor);
        }
    }
    
    // ==================== ELIMINACIÓN ====================
    
    /**
     * Elimina un valor del árbol.
     * @param valor Valor a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminar(int valor) {
        if (buscar(valor) == null) {
            return false;
        }
        raiz = eliminarRecursivo(raiz, valor);
        return true;
    }
    
    private NodoABB eliminarRecursivo(NodoABB nodo, int valor) {
        if (nodo == null) {
            return null;
        }
        
        // Buscar el nodo a eliminar
        if (valor < nodo.getValor()) {
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), valor));
        } else {
            // Encontramos el nodo a eliminar
            
            // Caso 1: Nodo hoja (sin hijos)
            if (nodo.esHoja()) {
                return null;
            }
            
            // Caso 2: Nodo con un solo hijo
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }
            
            // Caso 3: Nodo con dos hijos
            // Encontrar el sucesor inorden (mínimo del subárbol derecho)
            NodoABB sucesor = encontrarMinimo(nodo.getDerecho());
            nodo.setValor(sucesor.getValor());
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), sucesor.getValor()));
        }
        
        return nodo;
    }
    
    /**
     * Encuentra el nodo con valor mínimo en un subárbol.
     */
    private NodoABB encontrarMinimo(NodoABB nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }
    
    // ==================== RECORRIDOS ====================
    
    /**
     * Recorrido InOrden: Izquierdo -> Raíz -> Derecho
     * Produce los valores en orden ascendente.
     */
    public List<Integer> recorridoInOrden() {
        List<Integer> resultado = new ArrayList<>();
        inOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    
    private void inOrdenRecursivo(NodoABB nodo, List<Integer> lista) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.getIzquierdo(), lista);
            lista.add(nodo.getValor());
            inOrdenRecursivo(nodo.getDerecho(), lista);
        }
    }
    
    /**
     * Recorrido PreOrden: Raíz -> Izquierdo -> Derecho
     * Útil para copiar el árbol.
     */
    public List<Integer> recorridoPreOrden() {
        List<Integer> resultado = new ArrayList<>();
        preOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    
    private void preOrdenRecursivo(NodoABB nodo, List<Integer> lista) {
        if (nodo != null) {
            lista.add(nodo.getValor());
            preOrdenRecursivo(nodo.getIzquierdo(), lista);
            preOrdenRecursivo(nodo.getDerecho(), lista);
        }
    }
    
    /**
     * Recorrido PostOrden: Izquierdo -> Derecho -> Raíz
     * Útil para eliminar el árbol.
     */
    public List<Integer> recorridoPostOrden() {
        List<Integer> resultado = new ArrayList<>();
        postOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    
    private void postOrdenRecursivo(NodoABB nodo, List<Integer> lista) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.getIzquierdo(), lista);
            postOrdenRecursivo(nodo.getDerecho(), lista);
            lista.add(nodo.getValor());
        }
    }
    
    // ==================== UTILIDADES ====================
    
    /**
     * Limpia el árbol completamente.
     */
    public void limpiar() {
        raiz = null;
    }
    
    /**
     * Calcula la altura del árbol.
     */
    public int getAltura() {
        return calcularAltura(raiz);
    }
    
    private int calcularAltura(NodoABB nodo) {
        if (nodo == null) {
            return 0;
        }
        int alturaIzq = calcularAltura(nodo.getIzquierdo());
        int alturaDer = calcularAltura(nodo.getDerecho());
        return 1 + Math.max(alturaIzq, alturaDer);
    }
    
    /**
     * Cuenta el número total de nodos.
     */
    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }
    
    private int contarNodosRecursivo(NodoABB nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodosRecursivo(nodo.getIzquierdo()) 
                 + contarNodosRecursivo(nodo.getDerecho());
    }
}

