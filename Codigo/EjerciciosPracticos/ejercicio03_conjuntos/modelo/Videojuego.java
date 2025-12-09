package ejercicio03_conjuntos.modelo;

import java.util.Objects;

/**
 * Representa un videojuego con sus atributos principales.
 * Implementa Comparable para ordenamiento y equals/hashCode para uso en Set.
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class Videojuego implements Comparable<Videojuego> {
    
    private String nombre;
    private String genero;
    private String plataforma;
    private int anioLanzamiento;
    private String desarrollador;
    
    /**
     * Constructor del videojuego.
     */
    public Videojuego(String nombre, String genero, String plataforma, 
                      int anioLanzamiento, String desarrollador) {
        this.nombre = nombre;
        this.genero = genero;
        this.plataforma = plataforma;
        this.anioLanzamiento = anioLanzamiento;
        this.desarrollador = desarrollador;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    
    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
    
    public int getAnioLanzamiento() { return anioLanzamiento; }
    public void setAnioLanzamiento(int anioLanzamiento) { this.anioLanzamiento = anioLanzamiento; }
    
    public String getDesarrollador() { return desarrollador; }
    public void setDesarrollador(String desarrollador) { this.desarrollador = desarrollador; }
    
    /**
     * Dos videojuegos son iguales si tienen el mismo nombre (ignorando mayúsculas).
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Videojuego otro = (Videojuego) obj;
        return nombre.equalsIgnoreCase(otro.nombre);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }
    
    /**
     * Ordenamiento alfabético por nombre.
     */
    @Override
    public int compareTo(Videojuego otro) {
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }
    
    @Override
    public String toString() {
        return nombre + " (" + anioLanzamiento + ")";
    }
    
    /**
     * Retorna información detallada del videojuego.
     */
    public String getInfoCompleta() {
        return String.format("%s | %s | %s | %d | %s", 
            nombre, genero, plataforma, anioLanzamiento, desarrollador);
    }
}

