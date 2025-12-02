package mx.edu.utng.mrg.arbolbinario;

/**
 * 
 * @author Marlon Rojas Galindo
 */
public class PruebaArbol {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        
        System.out.println("Insertando valores: 50, 30, 70, 20, 40");
        
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        
        arbol.recorrerInorden();
        
        //Ejemplo de prueba 1. Arbol balanceado sencillo
        ArbolBinario arbol1 = new ArbolBinario();
        
        System.out.println("Ejemplo 1. Arbol binario balanceado sencillo");
        System.out.println("Insertando valores: [50,30,70,20,40,60,80]");
        
        arbol1.insertar(50);
        arbol1.insertar(30);
        arbol1.insertar(70);
        arbol1.insertar(20);
        arbol1.insertar(40);
        arbol1.insertar(60);
        arbol1.insertar(80);
        
        arbol1.recorrerInorden();
        
        //Ejemplo de prueba 2. Arbol binario desbalanceado (inclinado a la derecha)
        ArbolBinario arbol2 = new ArbolBinario();
        
        System.out.println("Ejemplo 2. Arbol binario desbalanceado (inclinado a la derecha)");
        System.out.println("Insertando valores: [10,20,30,40,50]");
        
        arbol2.insertar(10);
        arbol2.insertar(20);
        arbol2.insertar(30);
        arbol2.insertar(40);
        arbol2.insertar(50);
        
        arbol2.recorrerInorden();
        
        //Ejemplo de prueba 3. Árbol con multiples ramificaciones
        ArbolBinario arbol3 = new ArbolBinario();
        
        System.out.println("Ejemplo 3. Arbol con multiples ramificaciones");
        System.out.println("Insertando valores: [45,25,75,15,35,65,85,5,18,30,38]");
        
        arbol3.insertar(45);
        arbol3.insertar(25);
        arbol3.insertar(75);
        arbol3.insertar(15);
        arbol3.insertar(35);
        arbol3.insertar(65);
        arbol3.insertar(85);
        arbol3.insertar(5);
        arbol3.insertar(18);
        arbol3.insertar(30);
        arbol3.insertar(38);
        
        arbol3.recorrerInorden();
        
        //Ejemplo de prueba 4. Con datos duplicados asumiendo que se ignoran
        ArbolBinario arbol4 = new ArbolBinario();
        
        System.out.println("Ejemplo 4. Con datos duplicados");
        System.out.println("Insertando valores: [50,30,70,30,50,20,70]");
        
        arbol4.insertar(50);
        arbol4.insertar(30);
        arbol4.insertar(70);
        arbol4.insertar(30);
        arbol4.insertar(50);
        arbol4.insertar(20);
        arbol4.insertar(70);
        
        arbol4.recorrerInorden();
    }
}
