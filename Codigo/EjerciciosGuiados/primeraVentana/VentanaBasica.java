package primeraVentana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Ventana básica para aprender
 * @author Marlon Rojas Galindo
 * @contact marlonrojasuniversity@gmail.com
 * @version 1.0
 * @since 2025
 */
public class VentanaBasica extends JFrame {

    public VentanaBasica() {
        setTitle("Formulario Registro");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBackground(Color.CYAN);

        // -------------------- CAMPOS --------------------

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        // Apellido
        JLabel lblApellido = new JLabel("Apellido:");
        JTextField txtApellido = new JTextField();

        // Municipio (Guanajuato)
        JLabel lblMunicipio = new JLabel("Municipio:");
        String[] municipios = {
            "Abasolo", "Acámbaro", "Apaseo el Alto", "Apaseo el Grande", "Atarjea",
            "Celaya", "Comonfort", "Coroneo", "Cortazar", "Cuerámaro",
            "Doctor Mora", "Dolores Hidalgo", "Guanajuato", "Huanímaro", "Irapuato",
            "Jaral del Progreso", "Jerécuaro", "León", "Manuel Doblado", "Moroleón",
            "Ocampo", "Pénjamo", "Pueblo Nuevo", "Purísima del Rincón", "Romita",
            "Salamanca", "Salvatierra", "San Diego de la Unión", "San Felipe",
            "San Francisco del Rincón", "San José Iturbide", "San Luis de la Paz",
            "Santa Catarina", "Santa Cruz de Juventino Rosas", "Santiago Maravatío",
            "Silao", "Tarandacuao", "Tarimoro", "Tierra Blanca", "Uriangato",
            "Valle de Santiago", "Victoria", "Villagrán", "Xichú", "Yuriria"
        };
        JComboBox<String> comboMunicipio = new JComboBox<>(municipios);

        // Fecha nacimiento
        JLabel lblFecha = new JLabel("Fecha de nacimiento:");

        // Día
        DefaultComboBoxModel<Integer> modeloDia = new DefaultComboBoxModel<>();
        JComboBox<Integer> comboDia = new JComboBox<>(modeloDia);
        JLabel lblDia = new JLabel("Día");

        // Mes
        JComboBox<String> comboMes = new JComboBox<>(
                new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                             "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
        JLabel lblMes = new JLabel("Mes");

        // Año
        DefaultComboBoxModel<Integer> modeloAnio = new DefaultComboBoxModel<>();
        JComboBox<Integer> comboAnio = new JComboBox<>(modeloAnio);
        JLabel lblAnio = new JLabel("Año");

        // Rango de años (1920–2025)
        for (int i = 2025; i >= 1920; i--) modeloAnio.addElement(i);

        // Lógica para actualizar días según mes y año
        ActionListener actualizarDias = e -> {
            int mes = comboMes.getSelectedIndex() + 1;
            int anio = (int) comboAnio.getSelectedItem();

            int dias;
            switch (mes) {
                case 2:
                    dias = (esBisiesto(anio)) ? 29 : 28;
                    break;
                case 4: case 6: case 9: case 11:
                    dias = 30;
                    break;
                default:
                    dias = 31;
            }

            modeloDia.removeAllElements();
            for (int d = 1; d <= dias; d++) modeloDia.addElement(d);
        };

        comboMes.addActionListener(actualizarDias);
        comboAnio.addActionListener(actualizarDias);

        actualizarDias.actionPerformed(null);

        // Género
        JLabel lblGenero = new JLabel("Género:");

        JRadioButton radioHombre = new JRadioButton("Hombre");
        JRadioButton radioMujer = new JRadioButton("Mujer");
        JRadioButton radioOtro = new JRadioButton("Otro");

        ButtonGroup grupoGenero = new ButtonGroup();
        grupoGenero.add(radioHombre);
        grupoGenero.add(radioMujer);
        grupoGenero.add(radioOtro);

        JPanel panelGenero = new JPanel();
        panelGenero.add(radioHombre);
        panelGenero.add(radioMujer);
        panelGenero.add(radioOtro);

        // Botón
        JButton btnRegistrar = new JButton("Registrar");

        // -------------------- ACCIÓN DEL BOTÓN --------------------
        btnRegistrar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();

                if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))
                    throw new Exception("El nombre solo puede contener letras.");

                if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))
                    throw new Exception("El apellido solo puede contener letras.");

                String municipio = (String) comboMunicipio.getSelectedItem();

                int dia = (int) comboDia.getSelectedItem();
                String mes = (String) comboMes.getSelectedItem();
                int anio = (int) comboAnio.getSelectedItem();

                String genero = "";
                if (radioHombre.isSelected()) genero = "Hombre";
                else if (radioMujer.isSelected()) genero = "Mujer";
                else if (radioOtro.isSelected()) genero = "Otro";
                else throw new Exception("Debes seleccionar un género.");

                String mensaje = String.format(
                        "Hola %s %s, eres de %s, naciste el %d de %s de %d, eres del género %s. Saludos.",
                        nombre, apellido, municipio, dia, mes, anio, genero
                );

                JOptionPane.showMessageDialog(null, mensaje);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // -------------------- AGREGAR AL PANEL --------------------

        panel.add(lblNombre);      panel.add(txtNombre);
        panel.add(lblApellido);    panel.add(txtApellido);
        panel.add(lblMunicipio);   panel.add(comboMunicipio);

        panel.add(lblFecha);

        // panel para los 3 comboBox
        JPanel panelFecha = new JPanel();
        panelFecha.add(comboDia); panelFecha.add(lblDia);
        panelFecha.add(comboMes); panelFecha.add(lblMes);
        panelFecha.add(comboAnio); panelFecha.add(lblAnio);

        panel.add(panelFecha);

        panel.add(lblGenero);      panel.add(panelGenero);
        panel.add(new JLabel("")); panel.add(btnRegistrar);

        add(panel);
        setVisible(true);
    }

    private boolean esBisiesto(int anio) {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }
}