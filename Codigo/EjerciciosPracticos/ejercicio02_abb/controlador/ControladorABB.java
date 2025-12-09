package ejercicio02_abb.controlador;

import ejercicio02_abb.modelo.ArbolBinarioBusqueda;
import ejercicio02_abb.modelo.NodoABB;
import ejercicio02_abb.vista.VistaABB;

import javax.swing.*;
import java.util.List;

/**
 * Controlador del Visualizador de Árbol Binario de Búsqueda.
 * Gestiona las operaciones entre el modelo y la vista.
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class ControladorABB {
    
    private ArbolBinarioBusqueda modelo;
    private VistaABB vista;
    
    /**
     * Constructor del controlador.
     */
    public ControladorABB(ArbolBinarioBusqueda modelo, VistaABB vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        // Configurar el panel de dibujo con el modelo
        vista.getPanelDibujo().setArbol(modelo);
        
        // Conectar vista con controlador
        vista.setControlador(this);
    }
    
    /**
     * Inserta un nuevo valor en el árbol.
     */
    public void insertar() {
        Integer valor = vista.getValorIngresado();
        
        if (valor == null) {
            vista.mostrarError("Ingresa un número entero válido.");
            return;
        }
        
        boolean insertado = modelo.insertar(valor);
        
        if (insertado) {
            vista.mostrarResultado("✓ Valor " + valor + " insertado correctamente. " +
                                   "Total de nodos: " + modelo.contarNodos());
            vista.getPanelDibujo().limpiarResaltado();
        } else {
            vista.mostrarResultado("⚠ El valor " + valor + " ya existe en el árbol (no se permiten duplicados).");
        }
        
        vista.limpiarCampo();
        vista.actualizarDibujo();
    }
    
    /**
     * Elimina un valor del árbol.
     */
    public void eliminar() {
        Integer valor = vista.getValorIngresado();
        
        if (valor == null) {
            vista.mostrarError("Ingresa un número entero válido.");
            return;
        }
        
        // Determinar el tipo de eliminación para mostrar información educativa
        NodoABB nodo = modelo.buscar(valor);
        String tipoEliminacion = "";
        
        if (nodo != null) {
            int numHijos = nodo.contarHijos();
            if (numHijos == 0) {
                tipoEliminacion = " (Caso 1: Nodo hoja)";
            } else if (numHijos == 1) {
                tipoEliminacion = " (Caso 2: Nodo con 1 hijo)";
            } else {
                tipoEliminacion = " (Caso 3: Nodo con 2 hijos - Sucesor InOrden)";
            }
        }
        
        boolean eliminado = modelo.eliminar(valor);
        
        if (eliminado) {
            vista.mostrarResultado("✓ Valor " + valor + " eliminado" + tipoEliminacion + 
                                   ". Nodos restantes: " + modelo.contarNodos());
            vista.getPanelDibujo().limpiarResaltado();
        } else {
            vista.mostrarResultado("⚠ El valor " + valor + " no existe en el árbol.");
        }
        
        vista.limpiarCampo();
        vista.actualizarDibujo();
    }
    
    /**
     * Busca un valor en el árbol y lo resalta.
     */
    public void buscar() {
        Integer valor = vista.getValorIngresado();
        
        if (valor == null) {
            vista.mostrarError("Ingresa un número entero válido.");
            return;
        }
        
        NodoABB nodoEncontrado = modelo.buscar(valor);
        
        if (nodoEncontrado != null) {
            vista.getPanelDibujo().setNodoResaltado(nodoEncontrado);
            
            String tipo = "";
            if (nodoEncontrado == modelo.getRaiz()) {
                tipo = " (Nodo raíz)";
            } else if (nodoEncontrado.esHoja()) {
                tipo = " (Nodo hoja)";
            } else {
                tipo = " (Nodo interno con " + nodoEncontrado.contarHijos() + " hijo(s))";
            }
            
            vista.mostrarResultado("✓ Valor " + valor + " ENCONTRADO" + tipo + " - Resaltado en rojo.");
        } else {
            vista.getPanelDibujo().limpiarResaltado();
            vista.mostrarResultado("✗ Valor " + valor + " NO encontrado en el árbol.");
        }
        
        vista.limpiarCampo();
        vista.actualizarDibujo();
    }
    
    /**
     * Limpia todo el árbol.
     */
    public void limpiar() {
        if (modelo.estaVacio()) {
            vista.mostrarResultado("El árbol ya está vacío.");
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "¿Estás seguro de limpiar todo el árbol?",
            "Confirmar limpieza",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            modelo.limpiar();
            vista.getPanelDibujo().limpiarResaltado();
            vista.mostrarResultado("✓ Árbol limpiado completamente.");
            vista.actualizarDibujo();
        }
    }
    
    /**
     * Realiza y muestra el recorrido InOrden.
     */
    public void recorridoInOrden() {
        if (modelo.estaVacio()) {
            vista.mostrarResultado("El árbol está vacío. No hay nada que recorrer.");
            return;
        }
        
        List<Integer> recorrido = modelo.recorridoInOrden();
        String resultado = formatearRecorrido(recorrido);
        
        vista.mostrarResultado("Recorrido InOrden (Izq → Raíz → Der): " + resultado);
        vista.getPanelDibujo().limpiarResaltado();
        vista.actualizarDibujo();
    }
    
    /**
     * Realiza y muestra el recorrido PreOrden.
     */
    public void recorridoPreOrden() {
        if (modelo.estaVacio()) {
            vista.mostrarResultado("El árbol está vacío. No hay nada que recorrer.");
            return;
        }
        
        List<Integer> recorrido = modelo.recorridoPreOrden();
        String resultado = formatearRecorrido(recorrido);
        
        vista.mostrarResultado("Recorrido PreOrden (Raíz → Izq → Der): " + resultado);
        vista.getPanelDibujo().limpiarResaltado();
        vista.actualizarDibujo();
    }
    
    /**
     * Realiza y muestra el recorrido PostOrden.
     */
    public void recorridoPostOrden() {
        if (modelo.estaVacio()) {
            vista.mostrarResultado("El árbol está vacío. No hay nada que recorrer.");
            return;
        }
        
        List<Integer> recorrido = modelo.recorridoPostOrden();
        String resultado = formatearRecorrido(recorrido);
        
        vista.mostrarResultado("Recorrido PostOrden (Izq → Der → Raíz): " + resultado);
        vista.getPanelDibujo().limpiarResaltado();
        vista.actualizarDibujo();
    }
    
    /**
     * Formatea una lista de enteros para mostrar.
     */
    private String formatearRecorrido(List<Integer> lista) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            sb.append(lista.get(i));
            if (i < lista.size() - 1) {
                sb.append(" → ");
            }
        }
        return sb.toString();
    }
}
