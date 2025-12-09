package ejercicio03_conjuntos.modelo;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Gestor de colecciones de videojuegos usando Set de Java.
 * 
 * Operaciones de conjuntos implementadas:
 * 1. add (agregar)
 * 2. remove (eliminar)
 * 3. contains (contiene/buscar)
 * 4. union (unión de conjuntos)
 * 5. intersection (intersección)
 * 6. difference (diferencia)
 * 7. size (tamaño)
 * 8. clear (limpiar)
 * 9. isEmpty (está vacío)
 * 10. addAll (agregar todos)
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class GestorConjuntos {
    
    // Dos conjuntos para demostrar operaciones entre conjuntos
    private Set<Videojuego> coleccionA;  // Ej: "Mis juegos favoritos"
    private Set<Videojuego> coleccionB;  // Ej: "Juegos que quiero comprar"
    
    public GestorConjuntos() {
        // Usamos TreeSet para mantener orden alfabético
        this.coleccionA = new TreeSet<>();
        this.coleccionB = new TreeSet<>();
    }
    
    // ==================== OPERACIÓN 1: ADD ====================
    /**
     * Agrega un videojuego a la colección especificada.
     * @return true si se agregó, false si ya existía
     */
    public boolean agregar(Videojuego juego, boolean esColeccionA) {
        Set<Videojuego> coleccion = esColeccionA ? coleccionA : coleccionB;
        return coleccion.add(juego);
    }
    
    // ==================== OPERACIÓN 2: REMOVE ====================
    /**
     * Elimina un videojuego de la colección especificada.
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminar(Videojuego juego, boolean esColeccionA) {
        Set<Videojuego> coleccion = esColeccionA ? coleccionA : coleccionB;
        return coleccion.remove(juego);
    }
    
    /**
     * Elimina un videojuego por nombre.
     */
    public boolean eliminarPorNombre(String nombre, boolean esColeccionA) {
        Set<Videojuego> coleccion = esColeccionA ? coleccionA : coleccionB;
        return coleccion.removeIf(j -> j.getNombre().equalsIgnoreCase(nombre));
    }
    
    // ==================== OPERACIÓN 3: CONTAINS ====================
    /**
     * Verifica si un videojuego existe en la colección.
     */
    public boolean contiene(String nombre, boolean esColeccionA) {
        Set<Videojuego> coleccion = esColeccionA ? coleccionA : coleccionB;
        return coleccion.stream()
            .anyMatch(j -> j.getNombre().equalsIgnoreCase(nombre));
    }
    
    /**
     * Busca un videojuego por nombre.
     * @return El videojuego encontrado o null
     */
    public Videojuego buscar(String nombre, boolean esColeccionA) {
        Set<Videojuego> coleccion = esColeccionA ? coleccionA : coleccionB;
        return coleccion.stream()
            .filter(j -> j.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElse(null);
    }
    
    // ==================== OPERACIÓN 4: UNION ====================
    /**
     * Retorna la unión de ambas colecciones (A ∪ B).
     * Contiene todos los elementos que están en A o en B (o ambos).
     */
    public Set<Videojuego> union() {
        Set<Videojuego> resultado = new TreeSet<>(coleccionA);
        resultado.addAll(coleccionB);
        return resultado;
    }
    
    // ==================== OPERACIÓN 5: INTERSECTION ====================
    /**
     * Retorna la intersección de ambas colecciones (A ∩ B).
     * Contiene solo los elementos que están en A y en B.
     */
    public Set<Videojuego> interseccion() {
        Set<Videojuego> resultado = new TreeSet<>(coleccionA);
        resultado.retainAll(coleccionB);
        return resultado;
    }
    
    // ==================== OPERACIÓN 6: DIFFERENCE ====================
    /**
     * Retorna la diferencia A - B.
     * Contiene los elementos que están en A pero no en B.
     */
    public Set<Videojuego> diferenciaAmenosB() {
        Set<Videojuego> resultado = new TreeSet<>(coleccionA);
        resultado.removeAll(coleccionB);
        return resultado;
    }
    
    /**
     * Retorna la diferencia B - A.
     * Contiene los elementos que están en B pero no en A.
     */
    public Set<Videojuego> diferenciaBmenosA() {
        Set<Videojuego> resultado = new TreeSet<>(coleccionB);
        resultado.removeAll(coleccionA);
        return resultado;
    }
    
    /**
     * Retorna la diferencia simétrica (A △ B).
     * Contiene elementos que están en A o B, pero no en ambos.
     */
    public Set<Videojuego> diferenciaSimetrica() {
        Set<Videojuego> resultado = new TreeSet<>();
        resultado.addAll(diferenciaAmenosB());
        resultado.addAll(diferenciaBmenosA());
        return resultado;
    }
    
    // ==================== OPERACIÓN 7: SIZE ====================
    /**
     * Retorna el tamaño de la colección.
     */
    public int tamanio(boolean esColeccionA) {
        return esColeccionA ? coleccionA.size() : coleccionB.size();
    }
    
    // ==================== OPERACIÓN 8: CLEAR ====================
    /**
     * Limpia la colección especificada.
     */
    public void limpiar(boolean esColeccionA) {
        if (esColeccionA) {
            coleccionA.clear();
        } else {
            coleccionB.clear();
        }
    }
    
    /**
     * Limpia ambas colecciones.
     */
    public void limpiarTodo() {
        coleccionA.clear();
        coleccionB.clear();
    }
    
    // ==================== OPERACIÓN 9: IS_EMPTY ====================
    /**
     * Verifica si la colección está vacía.
     */
    public boolean estaVacia(boolean esColeccionA) {
        return esColeccionA ? coleccionA.isEmpty() : coleccionB.isEmpty();
    }
    
    // ==================== OPERACIÓN 10: ADD_ALL ====================
    /**
     * Agrega todos los elementos de un conjunto a la colección.
     */
    public void agregarTodos(Set<Videojuego> juegos, boolean esColeccionA) {
        if (esColeccionA) {
            coleccionA.addAll(juegos);
        } else {
            coleccionB.addAll(juegos);
        }
    }
    
    // ==================== GETTERS ====================
    public Set<Videojuego> getColeccionA() {
        return new TreeSet<>(coleccionA);
    }
    
    public Set<Videojuego> getColeccionB() {
        return new TreeSet<>(coleccionB);
    }
    
    // ==================== FILTROS ADICIONALES ====================
    /**
     * Filtra videojuegos por género.
     */
    public Set<Videojuego> filtrarPorGenero(String genero, boolean esColeccionA) {
        Set<Videojuego> coleccion = esColeccionA ? coleccionA : coleccionB;
        return coleccion.stream()
            .filter(j -> j.getGenero().equalsIgnoreCase(genero))
            .collect(Collectors.toCollection(TreeSet::new));
    }
    
    /**
     * Filtra videojuegos por plataforma.
     */
    public Set<Videojuego> filtrarPorPlataforma(String plataforma, boolean esColeccionA) {
        Set<Videojuego> coleccion = esColeccionA ? coleccionA : coleccionB;
        return coleccion.stream()
            .filter(j -> j.getPlataforma().equalsIgnoreCase(plataforma))
            .collect(Collectors.toCollection(TreeSet::new));
    }
}

