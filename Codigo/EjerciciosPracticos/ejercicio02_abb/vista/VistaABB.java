package ejercicio02_abb.vista;

import ejercicio02_abb.controlador.ControladorABB;

import javax.swing.*;
import java.awt.*;

/**
 * Vista principal del Visualizador de Árbol Binario de Búsqueda.
 * Contiene el panel de dibujo y los controles de operaciones.
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class VistaABB extends JFrame {
    
    // Panel de dibujo del árbol
    private PanelDibujoArbol panelDibujo;
    
    // Controles de entrada
    private JTextField campoValor;
    private JButton btnInsertar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    
    // Botones de recorridos
    private JButton btnInOrden;
    private JButton btnPreOrden;
    private JButton btnPostOrden;
    
    // Área de resultados
    private JLabel labelResultado;
    
    // Controlador
    private ControladorABB controlador;
    
    public VistaABB() {
        configurarVentana();
        inicializarComponentes();
        construirInterfaz();
    }
    
    private void configurarVentana() {
        setTitle("Visualizador de Árbol Binario de Búsqueda (ABB)");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void inicializarComponentes() {
        // Panel de dibujo
        panelDibujo = new PanelDibujoArbol();
        
        // Campo de texto para valores
        campoValor = new JTextField(8);
        campoValor.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Botones de operaciones principales
        btnInsertar = crearBoton("+ Insertar", new Color(76, 175, 80));
        btnEliminar = crearBoton("− Eliminar", new Color(244, 67, 54));
        btnBuscar = crearBoton("🔍 Buscar", new Color(33, 150, 243));
        btnLimpiar = crearBoton("🗑 Limpiar Árbol", new Color(158, 158, 158));
        
        // Botones de recorridos
        btnInOrden = crearBoton("Recorrido InOrden", new Color(255, 152, 0));
        btnPreOrden = crearBoton("Recorrido PreOrden", new Color(255, 152, 0));
        btnPostOrden = crearBoton("Recorrido PostOrden", new Color(255, 152, 0));
        
        // Label para mostrar resultados
        labelResultado = new JLabel(" ");
        labelResultado.setFont(new Font("Arial", Font.PLAIN, 13));
        labelResultado.setForeground(new Color(50, 50, 50));
    }
    
    /**
     * Crea un botón con estilo personalizado.
     */
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setBorderPainted(false);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }
    
    private void construirInterfaz() {
        setLayout(new BorderLayout(5, 5));
        
        // Panel superior: Controles de operaciones
        JPanel panelOperaciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelOperaciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panelOperaciones.setBackground(new Color(245, 245, 245));
        
        panelOperaciones.add(new JLabel("Valor:"));
        panelOperaciones.add(campoValor);
        panelOperaciones.add(btnInsertar);
        panelOperaciones.add(btnEliminar);
        panelOperaciones.add(btnBuscar);
        panelOperaciones.add(btnLimpiar);
        
        // Panel de recorridos
        JPanel panelRecorridos = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelRecorridos.setBackground(new Color(245, 245, 245));
        panelRecorridos.add(new JLabel("Recorridos:"));
        panelRecorridos.add(btnInOrden);
        panelRecorridos.add(btnPreOrden);
        panelRecorridos.add(btnPostOrden);
        
        // Panel superior combinado
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelOperaciones, BorderLayout.NORTH);
        panelSuperior.add(panelRecorridos, BorderLayout.SOUTH);
        
        // Panel central: Dibujo del árbol
        JScrollPane scrollDibujo = new JScrollPane(panelDibujo);
        scrollDibujo.setBorder(BorderFactory.createTitledBorder("Visualización del Árbol"));
        
        // Panel inferior: Resultados
        JPanel panelResultado = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelResultado.setBorder(BorderFactory.createEtchedBorder());
        panelResultado.add(labelResultado);
        
        // Agregar paneles al frame
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollDibujo, BorderLayout.CENTER);
        add(panelResultado, BorderLayout.SOUTH);
    }
    
    /**
     * Establece el controlador y configura los listeners.
     */
    public void setControlador(ControladorABB controlador) {
        this.controlador = controlador;
        
        // Configurar acciones
        btnInsertar.addActionListener(e -> controlador.insertar());
        btnEliminar.addActionListener(e -> controlador.eliminar());
        btnBuscar.addActionListener(e -> controlador.buscar());
        btnLimpiar.addActionListener(e -> controlador.limpiar());
        
        btnInOrden.addActionListener(e -> controlador.recorridoInOrden());
        btnPreOrden.addActionListener(e -> controlador.recorridoPreOrden());
        btnPostOrden.addActionListener(e -> controlador.recorridoPostOrden());
        
        // Permitir Enter en el campo de texto para insertar
        campoValor.addActionListener(e -> controlador.insertar());
    }
    
    // === Métodos para el controlador ===
    
    /**
     * Obtiene el valor ingresado en el campo de texto.
     * @return El valor como entero, o null si no es válido
     */
    public Integer getValorIngresado() {
        try {
            return Integer.parseInt(campoValor.getText().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    /**
     * Limpia el campo de texto.
     */
    public void limpiarCampo() {
        campoValor.setText("");
        campoValor.requestFocus();
    }
    
    /**
     * Obtiene el panel de dibujo.
     */
    public PanelDibujoArbol getPanelDibujo() {
        return panelDibujo;
    }
    
    /**
     * Actualiza el panel de dibujo.
     */
    public void actualizarDibujo() {
        panelDibujo.repaint();
    }
    
    /**
     * Muestra un mensaje de resultado en el label inferior.
     */
    public void mostrarResultado(String mensaje) {
        labelResultado.setText(mensaje);
    }
    
    /**
     * Muestra un mensaje de error.
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de información.
     */
    public void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}

