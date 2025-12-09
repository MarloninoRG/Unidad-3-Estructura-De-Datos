package ejercicio01_dom;

import ejercicio01_dom.modelo.ArbolDOM;
import ejercicio01_dom.vista.VistaDOMSimulador;
import ejercicio01_dom.controlador.ControladorDOM;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Clase principal del Simulador DOM.
 * Punto de entrada de la aplicación.
 * 
 * Ejercicio 01: Simulación de creación de página Web mediante árbol DOM.
 * 
 * Funcionalidades:
 * - Visualización del árbol DOM con JTree
 * - Vista HTML sincronizada en tiempo real
 * - Operaciones: Agregar, Eliminar, Editar nodos
 * - Exportación del HTML generado
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
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            try {
                // Usar look and feel del sistema operativo
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Si falla, usar el look and feel por defecto
                System.out.println("Usando Look and Feel por defecto");
            }
            
            // Crear componentes MVC
            ArbolDOM modelo = new ArbolDOM();
            VistaDOMSimulador vista = new VistaDOMSimulador();
            ControladorDOM controlador = new ControladorDOM(modelo, vista);
            
            // Mostrar la ventana
            vista.setVisible(true);
        });
    }
}
