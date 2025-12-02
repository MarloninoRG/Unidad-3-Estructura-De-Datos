package mx.edu.utng.mrg.arbolbinario;

/**
 *
 * @author Marlon Rojas Galindo
 */
public class NodoArbol {
    //Atributos
    private int dato;
    
    public NodoArbol hijoDerecho;
    public NodoArbol hijoIzquierdo;
    //Constructor
    public NodoArbol(int valor) {
        this.dato = valor;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }
    //Metodos Getters y Setters
    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public NodoArbol getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoArbol hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public NodoArbol getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoArbol hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }
}
