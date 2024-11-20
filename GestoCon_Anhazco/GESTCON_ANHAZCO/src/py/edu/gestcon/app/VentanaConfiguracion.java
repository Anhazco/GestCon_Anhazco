package py.edu.gestcon.app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import py.edu.gestcon.componentes.BotonesPrincipales;
import py.edu.gestcon.dao.ConfiguracionDao;
import py.edu.gestcon.entidades.Configuracion;

public class VentanaConfiguracion extends JDialog {
    private JTextField tfNombreEmpresa;
    private JTextField tfTelefonoEmpresa;
    private BotonesPrincipales btnGuardar;
    private BotonesPrincipales btnSalir;
    private ConfiguracionDao configuracionDao;
    private Configuracion configuracion;

    public VentanaConfiguracion() {
        setTitle("Configuración de la Empresa");
        setBounds(100, 100, 500, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        setBackground(new Color(240, 248, 255));
        getContentPane().setLayout(null);

        configuracionDao = new ConfiguracionDao();

        JLabel lblTitulo = new JLabel("Configuración de la Empresa");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(50, 15, 400, 30);
        getContentPane().add(lblTitulo);

        JLabel lblNombreEmpresa = new JLabel("Nombre de la Empresa:");
        lblNombreEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNombreEmpresa.setBounds(30, 70, 200, 25);
        getContentPane().add(lblNombreEmpresa);

        tfNombreEmpresa = new JTextField();
        tfNombreEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfNombreEmpresa.setBounds(30, 100, 420, 30);
        tfNombreEmpresa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
                    tfTelefonoEmpresa.requestFocus();
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {
                if (tfNombreEmpresa.getText().length() >= 100) e.consume();
            }
        });
        getContentPane().add(tfNombreEmpresa);

        JLabel lblTelefonoEmpresa = new JLabel("Teléfono de la Empresa:");
        lblTelefonoEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblTelefonoEmpresa.setBounds(30, 150, 200, 25);
        getContentPane().add(lblTelefonoEmpresa);

        tfTelefonoEmpresa = new JTextField();
        tfTelefonoEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfTelefonoEmpresa.setBounds(30, 180, 420, 30);
        tfTelefonoEmpresa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
                    tfNombreEmpresa.requestFocus();
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {
                if (tfTelefonoEmpresa.getText().length() >= 50) e.consume();
            }
        });
        getContentPane().add(tfTelefonoEmpresa);

        btnGuardar = new BotonesPrincipales();
        btnGuardar.setText("Guardar");
        btnGuardar.setBounds(100, 230, 120, 62);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        getContentPane().add(btnGuardar);

        btnSalir = new BotonesPrincipales();
        btnSalir.setText("Salir");
        btnSalir.setBounds(270, 230, 120, 62);
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        getContentPane().add(btnSalir);

        cargarFormulario();
    }

    private void actualizar() {
        // Validación de campo de nombre de la empresa
        if (tfNombreEmpresa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la empresa no puede estar vacío.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            tfNombreEmpresa.requestFocus();
            return;
        }

        // Validación de campo de teléfono
        if (tfTelefonoEmpresa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El teléfono de la empresa no puede estar vacío.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            tfTelefonoEmpresa.requestFocus();
            return;
        }
        
        // Validación de formato numérico en el teléfono
        if (!tfTelefonoEmpresa.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El teléfono debe contener solo números.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            tfTelefonoEmpresa.requestFocus();
            return;
        }

        // Si todo es válido, procedemos con la actualización
        if (configuracion == null) {
            configuracion = new Configuracion();
            configuracion.setId(1);
        }
        configuracion.setOrg(tfNombreEmpresa.getText());
        configuracion.setTelefono(tfTelefonoEmpresa.getText());

        try {
            configuracionDao.guardarActualizar(configuracion);
            JOptionPane.showMessageDialog(this, "Configuración guardada con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            configuracionDao.rollback();
            JOptionPane.showMessageDialog(this, "Error al guardar la configuración.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void cargarFormulario() {
        configuracion = configuracionDao.recuperarPorId(1);
        if (configuracion != null) {
            tfNombreEmpresa.setText(configuracion.getOrg());
            tfTelefonoEmpresa.setText(configuracion.getTelefono());
        }
    }

    private void salir() {
        dispose();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaConfiguracion dialog = new VentanaConfiguracion();
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
