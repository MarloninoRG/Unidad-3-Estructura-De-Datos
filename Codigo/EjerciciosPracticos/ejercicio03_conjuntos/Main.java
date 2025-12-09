package ejercicio03_conjuntos;

import ejercicio03_conjuntos.vista.VistaVideojuegos;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Clase principal de la aplicación de Gestión de Videojuegos.
 * 
 * Ejercicio 03: Aplicación con Conjuntos (Set) de Java.
 * 
 * Datos manejados (5 atributos por videojuego):
 * 1. Nombre
 * 2. Género
 * 3. Plataforma
 * 4. Año de lanzamiento
 * 5. Desarrollador
 * 
 * Operaciones de conjuntos implementadas (más de 6):
 * 1. add - Agregar videojuego
 * 2. remove - Eliminar videojuego
 * 3. contains - Buscar/verificar si existe
 * 4. union - Unión de colecciones (A ∪ B)
 * 5. intersection (retainAll) - Intersección (A ∩ B)
 * 6. difference (removeAll) - Diferencia (A - B, B - A)
 * 7. size - Obtener tamaño
 * 8. clear - Limpiar colección
 * 9. isEmpty - Verificar si está vacía
 * 10. addAll - Agregar múltiples elementos
 * 
 * Funcionalidad adicional:
 * - Diferencia simétrica (A △ B)
 * - Filtros por género y plataforma
 * - Datos de ejemplo precargados
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
            
            // Crear y mostrar la vista
            VistaVideojuegos vista = new VistaVideojuegos();
            vista.setVisible(true);
        });
    }
}

