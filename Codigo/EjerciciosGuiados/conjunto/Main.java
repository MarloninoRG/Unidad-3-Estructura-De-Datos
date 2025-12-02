package mx.edu.utng.mrg.conjunto;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Marlon Rojas Galindo
 */
public class Main {
    public static void main(String[] args) {
        //Ejemplo 1
        Set<String> frutas = new HashSet<>();
        
        frutas.add("Manzana");
        frutas.add("Pera");
        frutas.add("Naranja");
        frutas.add("Manzana"); //Duplicado -> no se agrega
        
        System.out.println("Conjunto: " + frutas);
        System.out.println("¿Contiene Pera? " + frutas.contains("Pera"));
        
        frutas.remove("Naranja");
        
        System.out.println("Tamaño: " + frutas.size());
        
        //Ejemplo 2
        TreeSet<Integer> numeros = new TreeSet<>();
        
        numeros.add(30);
        numeros.add(10);
        numeros.add(20);
        
        System.out.println(numeros); //[10,20,30]
    }
}
