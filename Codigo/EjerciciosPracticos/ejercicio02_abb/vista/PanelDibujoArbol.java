package ejercicio02_abb.vista;

import ejercicio02_abb.modelo.NodoABB;
import ejercicio02_abb.modelo.ArbolBinarioBusqueda;

import javax.swing.*;
import java.awt.*;

/**
 * Panel personalizado para dibujar el Árbol Binario de Búsqueda.
 * Utiliza Graphics2D para renderizar los nodos y conexiones.
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class PanelDibujoArbol extends JPanel {
    
    private ArbolBinarioBusqueda arbol;
    private NodoABB nodoResaltado;  // Nodo encontrado en búsqueda
    
    // Configuración visual
    private static final int RADIO_NODO = 25;
    private static final int ESPACIO_VERTICAL = 70;
    private static final Color COLOR_NODO = new Color(66, 133, 244);      // Azul
    private static final Color COLOR_RESALTADO = new Color(234, 67, 53);  // Rojo
    private static final Color COLOR_LINEA = new Color(100, 100, 100);    // Gris
    private static final Color COLOR_TEXTO = Color.WHITE;
    
    public PanelDibujoArbol() {
        this.arbol = null;
        this.nodoResaltado = null;
        setBackground(Color.WHITE);
    }
    
    /**
     * Establece el árbol a dibujar.
     */
    public void setArbol(ArbolBinarioBusqueda arbol) {
        this.arbol = arbol;
    }
    
    /**
     * Establece el nodo a resaltar (resultado de búsqueda).
     */
    public void setNodoResaltado(NodoABB nodo) {
        this.nodoResaltado = nodo;
    }
    
    /**
     * Limpia el nodo resaltado.
     */
    public void limpiarResaltado() {
        this.nodoResaltado = null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (arbol == null || arbol.estaVacio()) {
            // Mostrar mensaje si el árbol está vacío
            g.setColor(Color.GRAY);
            g.setFont(new Font("Arial", Font.ITALIC, 16));
            String mensaje = "Árbol vacío - Inserta valores para comenzar";
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(mensaje)) / 2;
            int y = getHeight() / 2;
            g.drawString(mensaje, x, y);
            return;
        }
        
        Graphics2D g2d = (Graphics2D) g;
        
        // Activar antialiasing para mejor calidad visual
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Paso 1: Calcular TODAS las posiciones primero
        int anchoPanel = getWidth();
        calcularPosiciones(arbol.getRaiz(), anchoPanel / 2, 50, anchoPanel / 4);
        
        // Paso 2: Dibujar TODAS las líneas (conexiones)
        dibujarLineas(g2d, arbol.getRaiz());
        
        // Paso 3: Dibujar TODOS los nodos (encima de las líneas)
        dibujarNodos(g2d, arbol.getRaiz());
    }
    
    /**
     * Calcula las coordenadas (x, y) de cada nodo recursivamente.
     */
    private void calcularPosiciones(NodoABB nodo, int x, int y, int desplazamiento) {
        if (nodo == null) return;
        
        // Asignar posición al nodo actual
        nodo.setX(x);
        nodo.setY(y);
        
        // El desplazamiento se reduce a la mitad en cada nivel
        int nuevoDesplazamiento = Math.max(desplazamiento / 2, 30);
        
        // Calcular posiciones de los hijos
        if (nodo.getIzquierdo() != null) {
            calcularPosiciones(nodo.getIzquierdo(), x - desplazamiento, y + ESPACIO_VERTICAL, nuevoDesplazamiento);
        }
        
        if (nodo.getDerecho() != null) {
            calcularPosiciones(nodo.getDerecho(), x + desplazamiento, y + ESPACIO_VERTICAL, nuevoDesplazamiento);
        }
    }
    
    /**
     * Dibuja todas las líneas (conexiones) del árbol recursivamente.
     */
    private void dibujarLineas(Graphics2D g2d, NodoABB nodo) {
        if (nodo == null) return;
        
        g2d.setColor(COLOR_LINEA);
        g2d.setStroke(new BasicStroke(2));
        
        // Dibujar línea al hijo izquierdo
        if (nodo.getIzquierdo() != null) {
            g2d.drawLine(nodo.getX(), nodo.getY(), 
                        nodo.getIzquierdo().getX(), nodo.getIzquierdo().getY());
            // Recursión para dibujar líneas de los descendientes
            dibujarLineas(g2d, nodo.getIzquierdo());
        }
        
        // Dibujar línea al hijo derecho
        if (nodo.getDerecho() != null) {
            g2d.drawLine(nodo.getX(), nodo.getY(), 
                        nodo.getDerecho().getX(), nodo.getDerecho().getY());
            // Recursión para dibujar líneas de los descendientes
            dibujarLineas(g2d, nodo.getDerecho());
        }
    }
    
    /**
     * Dibuja todos los nodos del árbol recursivamente.
     */
    private void dibujarNodos(Graphics2D g2d, NodoABB nodo) {
        if (nodo == null) return;
        
        // Primero dibujar los hijos (para que el padre quede "encima" visualmente si se superponen)
        dibujarNodos(g2d, nodo.getIzquierdo());
        dibujarNodos(g2d, nodo.getDerecho());
        
        // Luego dibujar este nodo
        dibujarNodo(g2d, nodo);
    }
    
    /**
     * Dibuja un nodo individual (círculo con valor).
     */
    private void dibujarNodo(Graphics2D g2d, NodoABB nodo) {
        int x = nodo.getX();
        int y = nodo.getY();
        
        // Determinar color del nodo
        Color colorNodo = (nodo == nodoResaltado) ? COLOR_RESALTADO : COLOR_NODO;
        
        // Dibujar sombra
        g2d.setColor(new Color(0, 0, 0, 50));
        g2d.fillOval(x - RADIO_NODO + 3, y - RADIO_NODO + 3, RADIO_NODO * 2, RADIO_NODO * 2);
        
        // Dibujar círculo del nodo
        g2d.setColor(colorNodo);
        g2d.fillOval(x - RADIO_NODO, y - RADIO_NODO, RADIO_NODO * 2, RADIO_NODO * 2);
        
        // Dibujar borde
        g2d.setColor(colorNodo.darker());
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x - RADIO_NODO, y - RADIO_NODO, RADIO_NODO * 2, RADIO_NODO * 2);
        
        // Dibujar valor
        g2d.setColor(COLOR_TEXTO);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        String valor = String.valueOf(nodo.getValor());
        FontMetrics fm = g2d.getFontMetrics();
        int anchoTexto = fm.stringWidth(valor);
        int altoTexto = fm.getAscent();
        g2d.drawString(valor, x - anchoTexto / 2, y + altoTexto / 3);
    }
    
    /**
     * Retorna el tamaño preferido del panel.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }
}