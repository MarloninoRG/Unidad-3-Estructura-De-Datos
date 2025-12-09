package ejercicio02_abb;

import ejercicio02_abb.modelo.ArbolBinarioBusqueda;
import ejercicio02_abb.vista.VistaABB;
import ejercicio02_abb.controlador.ControladorABB;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Clase principal del Visualizador de Árbol Binario de Búsqueda.
 * 
 * Ejercicio 02: Aplicación educativa para comprender ABB.
 * 
 * Funcionalidades:
 * - Visualización gráfica del árbol con nodos y conexiones
 * - Inserción de nodos respetando propiedad ABB (izq < raíz < der)
 * - Eliminación con los 3 casos: hoja, 1 hijo, 2 hijos
 * - Búsqueda con resaltado visual del nodo encontrado
 * - Recorridos: InOrden, PreOrden, PostOrden
 * 
 * Casos de prueba sugeridos (del PDF):
 * - P1.1: Insertar 50, 30, 70, 20, 40, 60, 80 (árbol balanceado)
 * - P1.2: Insertar 10, 20, 30, 40, 50, 60, 70 (árbol degenerado derecha)
 * - P1.3: Insertar 70, 60, 50, 40, 30, 20, 10 (árbol degenerado izquierda)
 * 
 * Patrón utilizado: MVC (Modelo-Vista-Controlador)
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.out.println("Usando Look and Feel por defecto");
            }
            
            // Crear componentes MVC
            ArbolBinarioBusqueda modelo = new ArbolBinarioBusqueda();
            VistaABB vista = new VistaABB();
            ControladorABB controlador = new ControladorABB(modelo, vista);
            
            // Mostrar la ventana
            vista.setVisible(true);
        });
    }
}
