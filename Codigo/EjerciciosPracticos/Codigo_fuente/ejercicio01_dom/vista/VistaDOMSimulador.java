package ejercicio01_dom.vista;

import ejercicio01_dom.modelo.NodoDOM;
import ejercicio01_dom.modelo.ArbolDOM;
import ejercicio01_dom.controlador.ControladorDOM;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

/**
 * Vista principal del Simulador DOM.
 * Muestra el árbol a la izquierda y el HTML generado a la derecha.
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class VistaDOMSimulador extends JFrame {
    
    // Componentes del árbol
    private JTree arbolVisual;
    private DefaultTreeModel modeloArbol;
    private DefaultMutableTreeNode nodoRaizVisual;
    
    // Componentes de la vista HTML
    private JTextArea areaHTML;
    
    // Controles de entrada
    private JComboBox<String> comboEtiquetas;
    private JTextField campoTexto;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnEditar;
    private JButton btnExportar;
    private JButton btnLimpiar;
    
    // Referencia al controlador
    private ControladorDOM controlador;
    
    // Etiquetas HTML disponibles
    private final String[] ETIQUETAS_HTML = {
        "head", "body", "header", "footer", "nav", "main", "section", "article",
        "div", "p", "h1", "h2", "h3", "h4", "span", "a", "ul", "ol", "li",
        "table", "tr", "td", "th", "form", "input", "button", "img"
    };
    
    /**
     * Constructor de la vista.
     */
    public VistaDOMSimulador() {
        configurarVentana();
        inicializarComponentes();
        construirInterfaz();
    }
    
    /**
     * Configura las propiedades básicas de la ventana.
     */
    private void configurarVentana() {
        setTitle("Simulación de DOM – Creación de Página Web");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * Inicializa todos los componentes de la interfaz.
     */
    private void inicializarComponentes() {
        // Crear nodo raíz visual del JTree
        nodoRaizVisual = new DefaultMutableTreeNode("html");
        modeloArbol = new DefaultTreeModel(nodoRaizVisual);
        arbolVisual = new JTree(modeloArbol);
        arbolVisual.setShowsRootHandles(true);
        arbolVisual.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        // Área de texto para mostrar HTML
        areaHTML = new JTextArea();
        areaHTML.setEditable(false);
        areaHTML.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaHTML.setBackground(new Color(250, 250, 250));
        
        // Controles
        comboEtiquetas = new JComboBox<>(ETIQUETAS_HTML);
        campoTexto = new JTextField(15);
        btnAgregar = new JButton("Agregar Nodo");
        btnEliminar = new JButton("Eliminar Nodo");
        btnEditar = new JButton("Editar Nodo");
        btnExportar = new JButton("Exportar HTML");
        btnLimpiar = new JButton("Limpiar Árbol");
        
        // Colores para los botones
        btnAgregar.setOpaque(true);
        btnAgregar.setBackground(new Color(76, 175, 80));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setBorderPainted(false);
    }
    
    /**
     * Construye y organiza la interfaz gráfica.
     */
    private void construirInterfaz() {
        setLayout(new BorderLayout(5, 5));
        
        // Panel izquierdo: Árbol DOM
        JPanel panelArbol = new JPanel(new BorderLayout());
        panelArbol.setBorder(BorderFactory.createTitledBorder("Árbol DOM"));
        panelArbol.add(new JScrollPane(arbolVisual), BorderLayout.CENTER);
        panelArbol.setPreferredSize(new Dimension(300, 0));
        
        // Panel derecho: Vista HTML
        JPanel panelHTML = new JPanel(new BorderLayout());
        panelHTML.setBorder(BorderFactory.createTitledBorder("Vista HTML"));
        panelHTML.add(new JScrollPane(areaHTML), BorderLayout.CENTER);
        
        // Panel central con división
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelArbol, panelHTML);
        splitPane.setDividerLocation(300);
        
        // Panel inferior: Controles
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelControles.setBorder(BorderFactory.createEtchedBorder());
        
        panelControles.add(new JLabel("Etiqueta:"));
        panelControles.add(comboEtiquetas);
        panelControles.add(new JLabel("Texto:"));
        panelControles.add(campoTexto);
        panelControles.add(btnAgregar);
        panelControles.add(btnEliminar);
        panelControles.add(btnEditar);
        panelControles.add(btnExportar);
        panelControles.add(btnLimpiar);
        
        // Agregar paneles al frame
        add(splitPane, BorderLayout.CENTER);
        add(panelControles, BorderLayout.SOUTH);
    }
    
    /**
     * Establece el controlador y configura los listeners.
     */
    public void setControlador(ControladorDOM controlador) {
        this.controlador = controlador;
        
        // Configurar acciones de los botones
        btnAgregar.addActionListener(e -> controlador.agregarNodo());
        btnEliminar.addActionListener(e -> controlador.eliminarNodo());
        btnEditar.addActionListener(e -> controlador.editarNodo());
        btnExportar.addActionListener(e -> controlador.exportarHTML());
        btnLimpiar.addActionListener(e -> controlador.limpiarArbol());
    }
    
    // === Métodos para el controlador ===
    
    /**
     * Obtiene el nodo visual actualmente seleccionado en el JTree.
     */
    public DefaultMutableTreeNode getNodoSeleccionado() {
        TreePath path = arbolVisual.getSelectionPath();
        if (path != null) {
            return (DefaultMutableTreeNode) path.getLastPathComponent();
        }
        return null;
    }
    
    /**
     * Obtiene la etiqueta seleccionada del ComboBox.
     */
    public String getEtiquetaSeleccionada() {
        return (String) comboEtiquetas.getSelectedItem();
    }
    
    /**
     * Obtiene el texto ingresado en el campo de texto.
     */
    public String getTextoIngresado() {
        return campoTexto.getText().trim();
    }
    
    /**
     * Limpia el campo de texto.
     */
    public void limpiarCampoTexto() {
        campoTexto.setText("");
    }
    
    /**
     * Actualiza el área de HTML con el código generado.
     */
    public void actualizarVistaHTML(String html) {
        areaHTML.setText(html);
    }
    
    /**
     * Agrega un nodo visual al JTree.
     */
    public DefaultMutableTreeNode agregarNodoVisual(DefaultMutableTreeNode padre, String texto) {
        DefaultMutableTreeNode nuevoNodo = new DefaultMutableTreeNode(texto);
        modeloArbol.insertNodeInto(nuevoNodo, padre, padre.getChildCount());
        arbolVisual.expandPath(new TreePath(padre.getPath()));
        return nuevoNodo;
    }
    
    /**
     * Elimina un nodo visual del JTree.
     */
    public void eliminarNodoVisual(DefaultMutableTreeNode nodo) {
        modeloArbol.removeNodeFromParent(nodo);
    }
    
    /**
     * Actualiza el texto de un nodo visual.
     */
    public void actualizarNodoVisual(DefaultMutableTreeNode nodo, String nuevoTexto) {
        nodo.setUserObject(nuevoTexto);
        modeloArbol.nodeChanged(nodo);
    }
    
    /**
     * Obtiene el nodo raíz visual.
     */
    public DefaultMutableTreeNode getNodoRaizVisual() {
        return nodoRaizVisual;
    }
    
    /**
     * Limpia todos los hijos del nodo raíz visual.
     */
    public void limpiarArbolVisual() {
        nodoRaizVisual.removeAllChildren();
        modeloArbol.reload();
    }
    
    /**
     * Muestra un mensaje de información.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de error.
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Muestra un diálogo para editar un nodo.
     * @return Array con [etiqueta, texto] o null si se cancela
     */
    public String[] mostrarDialogoEdicion(String etiquetaActual, String textoActual) {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JComboBox<String> comboEdit = new JComboBox<>(ETIQUETAS_HTML);
        comboEdit.setSelectedItem(etiquetaActual);
        JTextField campoEdit = new JTextField(textoActual);
        
        panel.add(new JLabel("Etiqueta:"));
        panel.add(comboEdit);
        panel.add(new JLabel("Texto:"));
        panel.add(campoEdit);
        
        int resultado = JOptionPane.showConfirmDialog(this, panel, "Editar Nodo", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (resultado == JOptionPane.OK_OPTION) {
            return new String[] { (String) comboEdit.getSelectedItem(), campoEdit.getText().trim() };
        }
        return null;
    }
}
