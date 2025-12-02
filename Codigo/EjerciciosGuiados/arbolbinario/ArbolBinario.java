package mx.edu.utng.mrg.arbolbinario;

/**
 *
 * @author Marlon Rojas Galindo
 */
public class ArbolBinario {
    //Atributos
    private NodoArbol raiz;
    
    //Constructor
    public ArbolBinario() {
        this.raiz = null;
    }
    
    //Metodos de inserción
    public void insertar(int valor) {
        this.raiz = insertarRecursivo(this.raiz, valor);
    }
    
    private NodoArbol insertarRecursivo(NodoArbol actual, int valor) {
        //Caso base:
        if (actual == null) {
            return new NodoArbol(valor);
        }
        
        if (valor < actual.getDato()) {
            actual.hijoIzquierdo = insertarRecursivo(actual.hijoIzquierdo, valor);
        } else if (valor > actual.getDato()) {
            actual.hijoDerecho = insertarRecursivo(actual.hijoDerecho, valor);
        }
        
        return actual;
    }
    
    //Metodo de recorrido Inorden
    public void recorrerInorden() {
        System.out.println("Recorrido Inorden: ");
        recorrerInordenRecursivo(this.raiz);
        System.out.println();
    }
    
    private void recorrerInordenRecursivo(NodoArbol nodo) {
        if (nodo != null) {
            recorrerInordenRecursivo(nodo.hijoIzquierdo);
            System.out.println(nodo.getDato() + " ");
            recorrerInordenRecursivo(nodo.hijoDerecho);
        }
    }
}
