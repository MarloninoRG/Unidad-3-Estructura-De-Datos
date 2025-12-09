package ejercicio03_conjuntos.vista;

import ejercicio03_conjuntos.modelo.Videojuego;
import ejercicio03_conjuntos.modelo.GestorConjuntos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;

/**
 * Vista principal de la aplicación de gestión de videojuegos con conjuntos.
 * Permite gestionar dos colecciones y realizar operaciones de conjuntos.
 * 
 * @author Marlon Rojas Galindo
 * @author marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class VistaVideojuegos extends JFrame {
    
    // Modelo de datos
    private GestorConjuntos gestor;
    
    // Tablas para mostrar las colecciones
    private JTable tablaColeccionA;
    private JTable tablaColeccionB;
    private JTable tablaResultado;
    private DefaultTableModel modeloTablaA;
    private DefaultTableModel modeloTablaB;
    private DefaultTableModel modeloTablaResultado;
    
    // Campos de entrada
    private JTextField txtNombre;
    private JComboBox<String> cmbGenero;
    private JComboBox<String> cmbPlataforma;
    private JTextField txtAnio;
    private JTextField txtDesarrollador;
    private JRadioButton rbColeccionA;
    private JRadioButton rbColeccionB;
    
    // Botones de operaciones
    private JButton btnAgregar, btnEliminar, btnBuscar, btnLimpiar;
    private JButton btnUnion, btnInterseccion, btnDiferenciaAB, btnDiferenciaBA, btnDifSimetrica;
    
    // Labels informativos
    private JLabel lblInfoA, lblInfoB, lblResultado;
    
    // Datos predefinidos
    private final String[] GENEROS = {"Survival Horror", "Action", "RPG", "Adventure", "Shooter", "Puzzle"};
    private final String[] PLATAFORMAS = {"PlayStation", "Xbox", "PC", "Nintendo Switch", "Multi-plataforma"};
    
    public VistaVideojuegos() {
        this.gestor = new GestorConjuntos();
        configurarVentana();
        inicializarComponentes();
        construirInterfaz();
        cargarDatosEjemplo();
        actualizarVista();
    }
    
    private void configurarVentana() {
        setTitle("🎮 Gestión de Videojuegos - Operaciones con Conjuntos");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void inicializarComponentes() {
        // Campos de entrada
        txtNombre = new JTextField(15);
        cmbGenero = new JComboBox<>(GENEROS);
        cmbPlataforma = new JComboBox<>(PLATAFORMAS);
        txtAnio = new JTextField(6);
        txtDesarrollador = new JTextField(12);
        
        // Radio buttons para seleccionar colección
        rbColeccionA = new JRadioButton("Colección A (Favoritos)", true);
        rbColeccionB = new JRadioButton("Colección B (Wishlist)");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbColeccionA);
        grupo.add(rbColeccionB);
        
        // Botones CRUD
        btnAgregar = crearBoton("➕ Agregar", new Color(76, 175, 80));
        btnEliminar = crearBoton("➖ Eliminar", new Color(244, 67, 54));
        btnBuscar = crearBoton("🔍 Buscar", new Color(33, 150, 243));
        btnLimpiar = crearBoton("🗑 Limpiar", new Color(158, 158, 158));
        
        // Botones de operaciones de conjuntos
        btnUnion = crearBoton("A ∪ B (Unión)", new Color(156, 39, 176));
        btnInterseccion = crearBoton("A ∩ B (Intersección)", new Color(0, 150, 136));
        btnDiferenciaAB = crearBoton("A - B (Diferencia)", new Color(255, 152, 0));
        btnDiferenciaBA = crearBoton("B - A (Diferencia)", new Color(255, 152, 0));
        btnDifSimetrica = crearBoton("A △ B (Simétrica)", new Color(121, 85, 72));
        
        // Tablas
        String[] columnas = {"Nombre", "Género", "Plataforma", "Año", "Desarrollador"};
        modeloTablaA = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        modeloTablaB = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        modeloTablaResultado = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        
        tablaColeccionA = new JTable(modeloTablaA);
        tablaColeccionB = new JTable(modeloTablaB);
        tablaResultado = new JTable(modeloTablaResultado);
        
        // Labels
        lblInfoA = new JLabel("Colección A: 0 juegos");
        lblInfoB = new JLabel("Colección B: 0 juegos");
        lblResultado = new JLabel("Resultado: Selecciona una operación");
        
        // Configurar acciones
        configurarAcciones();
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 11));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    private void construirInterfaz() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior: Entrada de datos
        JPanel panelEntrada = crearPanelEntrada();
        
        // Panel central: Las tres tablas
        JPanel panelTablas = crearPanelTablas();
        
        // Panel inferior: Operaciones de conjuntos
        JPanel panelOperaciones = crearPanelOperaciones();
        
        add(panelEntrada, BorderLayout.NORTH);
        add(panelTablas, BorderLayout.CENTER);
        add(panelOperaciones, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelEntrada() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Agregar/Buscar Videojuego"));
        panel.setBackground(new Color(245, 245, 245));
        
        // Campos de entrada
        JPanel campos = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        campos.setOpaque(false);
        campos.add(new JLabel("Nombre:"));
        campos.add(txtNombre);
        campos.add(new JLabel("Género:"));
        campos.add(cmbGenero);
        campos.add(new JLabel("Plataforma:"));
        campos.add(cmbPlataforma);
        campos.add(new JLabel("Año:"));
        campos.add(txtAnio);
        campos.add(new JLabel("Desarrollador:"));
        campos.add(txtDesarrollador);
        
        // Selección de colección y botones
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        acciones.setOpaque(false);
        acciones.add(rbColeccionA);
        acciones.add(rbColeccionB);
        acciones.add(Box.createHorizontalStrut(20));
        acciones.add(btnAgregar);
        acciones.add(btnEliminar);
        acciones.add(btnBuscar);
        acciones.add(btnLimpiar);
        
        panel.add(campos, BorderLayout.NORTH);
        panel.add(acciones, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel crearPanelTablas() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Panel Colección A
        JPanel panelA = new JPanel(new BorderLayout(0, 5));
        panelA.setBorder(BorderFactory.createTitledBorder("📁 Colección A - Mis Favoritos"));
        panelA.add(new JScrollPane(tablaColeccionA), BorderLayout.CENTER);
        panelA.add(lblInfoA, BorderLayout.SOUTH);
        
        // Panel Colección B
        JPanel panelB = new JPanel(new BorderLayout(0, 5));
        panelB.setBorder(BorderFactory.createTitledBorder("📁 Colección B - Wishlist"));
        panelB.add(new JScrollPane(tablaColeccionB), BorderLayout.CENTER);
        panelB.add(lblInfoB, BorderLayout.SOUTH);
        
        // Panel Resultado
        JPanel panelRes = new JPanel(new BorderLayout(0, 5));
        panelRes.setBorder(BorderFactory.createTitledBorder("📊 Resultado de Operación"));
        panelRes.add(new JScrollPane(tablaResultado), BorderLayout.CENTER);
        panelRes.add(lblResultado, BorderLayout.SOUTH);
        
        panel.add(panelA);
        panel.add(panelB);
        panel.add(panelRes);
        
        return panel;
    }
    
    private JPanel crearPanelOperaciones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Operaciones de Conjuntos"));
        panel.setBackground(new Color(240, 240, 240));
        
        panel.add(btnUnion);
        panel.add(btnInterseccion);
        panel.add(btnDiferenciaAB);
        panel.add(btnDiferenciaBA);
        panel.add(btnDifSimetrica);
        
        return panel;
    }
    
    private void configurarAcciones() {
        // Agregar videojuego
        btnAgregar.addActionListener(e -> agregarVideojuego());
        
        // Eliminar videojuego
        btnEliminar.addActionListener(e -> eliminarVideojuego());
        
        // Buscar videojuego
        btnBuscar.addActionListener(e -> buscarVideojuego());
        
        // Limpiar colección
        btnLimpiar.addActionListener(e -> limpiarColeccion());
        
        // Operaciones de conjuntos
        btnUnion.addActionListener(e -> mostrarResultado(gestor.union(), "Unión (A ∪ B)"));
        btnInterseccion.addActionListener(e -> mostrarResultado(gestor.interseccion(), "Intersección (A ∩ B)"));
        btnDiferenciaAB.addActionListener(e -> mostrarResultado(gestor.diferenciaAmenosB(), "Diferencia (A - B)"));
        btnDiferenciaBA.addActionListener(e -> mostrarResultado(gestor.diferenciaBmenosA(), "Diferencia (B - A)"));
        btnDifSimetrica.addActionListener(e -> mostrarResultado(gestor.diferenciaSimetrica(), "Diferencia Simétrica (A △ B)"));
    }
    
    private void agregarVideojuego() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el nombre del videojuego.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int anio;
        try {
            anio = Integer.parseInt(txtAnio.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingresa un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Videojuego juego = new Videojuego(
            nombre,
            (String) cmbGenero.getSelectedItem(),
            (String) cmbPlataforma.getSelectedItem(),
            anio,
            txtDesarrollador.getText().trim()
        );
        
        boolean esColeccionA = rbColeccionA.isSelected();
        boolean agregado = gestor.agregar(juego, esColeccionA);
        
        if (agregado) {
            JOptionPane.showMessageDialog(this, "✓ Videojuego agregado a " + 
                (esColeccionA ? "Colección A" : "Colección B"), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "⚠ El videojuego ya existe en la colección.", 
                "Duplicado", JOptionPane.WARNING_MESSAGE);
        }
        
        actualizarVista();
    }
    
    private void eliminarVideojuego() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el nombre del videojuego a eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean esColeccionA = rbColeccionA.isSelected();
        boolean eliminado = gestor.eliminarPorNombre(nombre, esColeccionA);
        
        if (eliminado) {
            JOptionPane.showMessageDialog(this, "✓ Videojuego eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "✗ Videojuego no encontrado en la colección.", 
                "No encontrado", JOptionPane.WARNING_MESSAGE);
        }
        
        actualizarVista();
    }
    
    private void buscarVideojuego() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el nombre del videojuego a buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean esColeccionA = rbColeccionA.isSelected();
        boolean encontradoA = gestor.contiene(nombre, true);
        boolean encontradoB = gestor.contiene(nombre, false);
        
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Búsqueda de: \"").append(nombre).append("\"\n\n");
        mensaje.append("En Colección A: ").append(encontradoA ? "✓ Encontrado" : "✗ No encontrado").append("\n");
        mensaje.append("En Colección B: ").append(encontradoB ? "✓ Encontrado" : "✗ No encontrado");
        
        if (encontradoA || encontradoB) {
            Videojuego juego = encontradoA ? gestor.buscar(nombre, true) : gestor.buscar(nombre, false);
            mensaje.append("\n\nDetalles:\n").append(juego.getInfoCompleta());
        }
        
        JOptionPane.showMessageDialog(this, mensaje.toString(), "Resultado de Búsqueda", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void limpiarColeccion() {
        boolean esColeccionA = rbColeccionA.isSelected();
        String nombreColeccion = esColeccionA ? "Colección A" : "Colección B";
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de limpiar " + nombreColeccion + "?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            gestor.limpiar(esColeccionA);
            actualizarVista();
        }
    }
    
    private void mostrarResultado(Set<Videojuego> resultado, String operacion) {
        modeloTablaResultado.setRowCount(0);
        for (Videojuego j : resultado) {
            modeloTablaResultado.addRow(new Object[]{
                j.getNombre(), j.getGenero(), j.getPlataforma(), 
                j.getAnioLanzamiento(), j.getDesarrollador()
            });
        }
        lblResultado.setText(operacion + ": " + resultado.size() + " juego(s)");
    }
    
    private void actualizarVista() {
        // Actualizar tabla A
        modeloTablaA.setRowCount(0);
        for (Videojuego j : gestor.getColeccionA()) {
            modeloTablaA.addRow(new Object[]{
                j.getNombre(), j.getGenero(), j.getPlataforma(), 
                j.getAnioLanzamiento(), j.getDesarrollador()
            });
        }
        lblInfoA.setText("Colección A: " + gestor.tamanio(true) + " juego(s)");
        
        // Actualizar tabla B
        modeloTablaB.setRowCount(0);
        for (Videojuego j : gestor.getColeccionB()) {
            modeloTablaB.addRow(new Object[]{
                j.getNombre(), j.getGenero(), j.getPlataforma(), 
                j.getAnioLanzamiento(), j.getDesarrollador()
            });
        }
        lblInfoB.setText("Colección B: " + gestor.tamanio(false) + " juego(s)");
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtAnio.setText("");
        txtDesarrollador.setText("");
        txtNombre.requestFocus();
    }
    
    /**
     * Carga datos de ejemplo para demostrar la funcionalidad.
     */
    private void cargarDatosEjemplo() {
        // Colección A: Favoritos (juegos clásicos de terror)
        gestor.agregar(new Videojuego("Silent Hill 2", "Survival Horror", "PlayStation", 2001, "Konami"), true);
        gestor.agregar(new Videojuego("Resident Evil 4", "Survival Horror", "Multi-plataforma", 2005, "Capcom"), true);
        gestor.agregar(new Videojuego("Dead Space", "Survival Horror", "Multi-plataforma", 2008, "EA Redwood"), true);
        gestor.agregar(new Videojuego("Amnesia: The Dark Descent", "Survival Horror", "PC", 2010, "Frictional Games"), true);
        gestor.agregar(new Videojuego("Outlast", "Survival Horror", "Multi-plataforma", 2013, "Red Barrels"), true);
        
        // Colección B: Wishlist (juegos que quiero)
        gestor.agregar(new Videojuego("Silent Hill 2", "Survival Horror", "PlayStation", 2001, "Konami"), false); // Duplicado intencional
        gestor.agregar(new Videojuego("Resident Evil Village", "Survival Horror", "Multi-plataforma", 2021, "Capcom"), false);
        gestor.agregar(new Videojuego("The Evil Within 2", "Survival Horror", "Multi-plataforma", 2017, "Tango Gameworks"), false);
        gestor.agregar(new Videojuego("SOMA", "Survival Horror", "Multi-plataforma", 2015, "Frictional Games"), false);
        gestor.agregar(new Videojuego("Alien: Isolation", "Survival Horror", "Multi-plataforma", 2014, "Creative Assembly"), false);
    }
}

