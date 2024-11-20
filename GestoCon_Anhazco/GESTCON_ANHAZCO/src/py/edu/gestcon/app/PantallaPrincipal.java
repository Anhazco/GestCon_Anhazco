package py.edu.gestcon.app;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

import py.edu.gestcon.componentes.BotonesPrincipales;
import py.edu.gestcon.componentes.JpanelPantallaPrincipal;
import py.edu.gestcon.dao.ConfiguracionDao;
import py.edu.gestcon.dao.ConvenioDao;
import py.edu.gestcon.dao.FirmantesDao;
import py.edu.gestcon.dao.InstitucionDao;
import py.edu.gestcon.dao.SeguimientoDao;
import py.edu.gestcon.dao.Unidad_AcademicaDao;
import py.edu.gestcon.dao.UniversidadDao;
import py.edu.gestcon.entidades.Configuracion;
import py.edu.gestcon.vista.*;

public class PantallaPrincipal extends JFrame {
	private ConfiguracionDao configuracionDao;
	private static JLabel lblNombreEmpresa;
	private static JLabel lblTelefonoEmpresa;

	private JPanel contentPane;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				PantallaPrincipal frame = new PantallaPrincipal();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public PantallaPrincipal() {
		configurarVentana();
		crearMenu();
		agregarComponentes();
	}

	private void configurarVentana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1080, 720));
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(this);
		setTitle("Gestión de Convenio");
		getContentPane().setLayout(new BorderLayout());
	}

	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(20, 20, 20, 20));
		menuBar.setBackground(new Color(70, 130, 180));
		setJMenuBar(menuBar);

		Font menuFont = new Font("Segoe UI", Font.BOLD, 14);
		Color textColor = Color.WHITE;

		JMenu mnRegistros = crearMenu("Registros", textColor, menuFont);
		agregarItemMenu(mnRegistros, "Universidad", e -> abrirUniversidadVentana());
		agregarItemMenu(mnRegistros, "Firmantes", e -> abrirFirmantesVentana());
		agregarItemMenu(mnRegistros, "Unidad Académica", e -> abrirUnidadAcademicaVentana());
		agregarItemMenu(mnRegistros, "Instituciones", e -> abrirInstitucionVentana());
		menuBar.add(mnRegistros);

		JMenu mnTransacciones = crearMenu("Transacciones", textColor, menuFont);
		agregarItemMenu(mnTransacciones, "Convenios", e -> abrirConvenioventana());
		agregarItemMenu(mnTransacciones, "Seguimiento", e -> abrirSeguimiento());
		menuBar.add(mnTransacciones);

		JMenu mnListados = crearMenu("Listados", textColor, menuFont);
		agregarItemMenu(mnListados, "Unidad Académica", e -> abrirListadoUnidadAcademica());
		agregarItemMenu(mnListados, "Instituciones", e -> abrirListadoInstitucion());
		agregarItemMenu(mnListados, "Universidad", e -> abrirListadoUniversidad());
		agregarItemMenu(mnListados, "Firmantes", e -> abrirListadoFirmante());
		menuBar.add(mnListados);

		JMenu mnInformes = crearMenu("Informes", textColor, menuFont);
		agregarItemMenu(mnInformes, "Convenios", e -> abrirListadoinformeConvenio());
		menuBar.add(mnInformes);

		JMenu mnUtilidades = crearMenu("Utilidades", textColor, menuFont);
		agregarItemMenu(mnUtilidades, "Inicialización de Datos", e -> inicializarBasedeDatos());
		agregarItemMenu(mnUtilidades, "Configuración", e -> abrirVentanaConfiguracion());
		menuBar.add(mnUtilidades);
	}

	private JMenu crearMenu(String nombre, Color color, Font font) {
		JMenu menu = new JMenu(nombre);
		menu.setFont(font);
		menu.setForeground(color);
		return menu;
	}

	private void agregarItemMenu(JMenu menu, String nombre, ActionListener listener) {
		JMenuItem item = new JMenuItem(nombre);
		item.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		if (listener != null) {
			item.addActionListener(listener);
		}
		menu.add(item);
	}

	private void agregarComponentes() {
		contentPane = new JpanelPantallaPrincipal();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		agregarPanelIzquierdo();
		agregarPanelCentral();
		agregarPanelInferior();
	}

	private void agregarPanelIzquierdo() {
		JPanel panelIzquierdo = new JPanel(new GridLayout(4, 1, 10, 10));
		panelIzquierdo.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelIzquierdo.setBackground(new Color(245, 245, 245));

		BotonesPrincipales btnConvenios = new BotonesPrincipales();
		btnConvenios.setText("Convenios");
		btnConvenios.addActionListener(e -> abrirConvenio());

		BotonesPrincipales btnFirmantes = new BotonesPrincipales();
		btnFirmantes.setText("Firmantes");
		btnFirmantes.addActionListener(e -> abrirFirmantes());

		BotonesPrincipales btnSeguimiento = new BotonesPrincipales();
		btnSeguimiento.setText("Seguimiento");
		btnSeguimiento.addActionListener(e -> abrirSeguimiento());

		BotonesPrincipales btnSalir = new BotonesPrincipales();
		btnSalir.setText("Salir");
		btnSalir.addActionListener(e -> Salir());

		panelIzquierdo.add(btnConvenios);
		panelIzquierdo.add(btnFirmantes);
		panelIzquierdo.add(btnSeguimiento);
		panelIzquierdo.add(btnSalir);

		contentPane.add(panelIzquierdo, BorderLayout.WEST);
	}

	private void agregarPanelCentral() {
		JPanel panelCentral = new JPanel(new BorderLayout());
		panelCentral.setOpaque(false);

		JLabel lblBienvenida = new JLabel("¡Bienvenido a la Gestión de Convenio!", SwingConstants.CENTER);
		lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblBienvenida.setBorder(new EmptyBorder(30, 0, 20, 0));

		JTextArea txtInfo = new JTextArea("");
		txtInfo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		txtInfo.setEditable(false);
		txtInfo.setOpaque(false);
		txtInfo.setBorder(new EmptyBorder(20, 20, 20, 20));

		panelCentral.add(lblBienvenida, BorderLayout.NORTH);
		panelCentral.add(txtInfo, BorderLayout.CENTER);

		contentPane.add(panelCentral, BorderLayout.CENTER);
	}

	private void agregarPanelInferior() {
		JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelInferior.setBackground(new Color(230, 230, 230));

		lblNombreEmpresa = new JLabel("Empresa: No configurado");
		lblTelefonoEmpresa = new JLabel("Teléfono: No configurado");

		lblNombreEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTelefonoEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		lblNombreEmpresa.setForeground(Color.DARK_GRAY);
		lblTelefonoEmpresa.setForeground(Color.DARK_GRAY);

		panelInferior.add(lblNombreEmpresa);
		panelInferior.add(lblTelefonoEmpresa);

		JLabel lblVersion = new JLabel("Versión 1.0 - Cristian Añazco");
		lblVersion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblVersion.setForeground(Color.DARK_GRAY);

		panelInferior.add(lblVersion);
		contentPane.add(panelInferior, BorderLayout.SOUTH);
	}

	private void abrirVentanaConfiguracion() {
		VentanaConfiguracion ventanaConfiguracion = new VentanaConfiguracion();
		ventanaConfiguracion.setVisible(true);
		cargarConfiguracion();
	}

	private void cargarConfiguracion() {
		configuracionDao = new ConfiguracionDao();
		Configuracion configuracion = configuracionDao.recuperarPorId(1);
		if (configuracion != null) {
			lblNombreEmpresa.setText("Empresa: " + configuracion.getOrg());
			lblTelefonoEmpresa.setText("Teléfono: " + configuracion.getTelefono());
		} else {
			lblNombreEmpresa.setText("Empresa: No configurado");
			lblTelefonoEmpresa.setText("Teléfono: No configurado");
		}
	}

	private void abrirFirmantesVentana() {
		FirmanteVentana ventana = new FirmanteVentana();
		ventana.setUpControlador();
		ventana.setVisible(true);
	}

	private void abrirUniversidadVentana() {
		UniversidadVentana ventana = new UniversidadVentana();
		ventana.setUpControlador();
		ventana.setVisible(true);
	}

	private void abrirConvenio() {
		ConvenioVentana ventana = new ConvenioVentana();
		ventana.setUpControlador();
		ventana.setVisible(true);
	}

	private void abrirFirmantes() {
		FirmanteVentana ventana = new FirmanteVentana();
		ventana.setUpControlador();
		ventana.setVisible(true);
	}

	private void abrirSeguimiento() {
		SeguimientoVentana ventana = new SeguimientoVentana();
		ventana.setUpControlador();
		ventana.setVisible(true);
	}

	private void Salir() {
		dispose();
	}

	private void abrirUnidadAcademicaVentana() {
		UnidadAcademicaVentana ventana = new UnidadAcademicaVentana();
		ventana.setUpControlador();
		ventana.setVisible(true);
	}

	private void abrirInstitucionVentana() {
		InstitucionVentana ventana = new InstitucionVentana();
		ventana.setUpControlador();
		ventana.setVisible(true);
	}

	private void abrirConvenioventana() {
		ConvenioVentana ventana = new ConvenioVentana();
		ventana.setUpControlador();
		ventana.setVisible(true);
	}

	private void abrirListadoUnidadAcademica() {
		ListadoUnidadAcademicaVentana ventana = new ListadoUnidadAcademicaVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	private void abrirListadoInstitucion() {
		ListadoInstitucionVentana ventana = new ListadoInstitucionVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	private void abrirListadoUniversidad() {
		ListadoUniversidadVentana ventana = new ListadoUniversidadVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	private void abrirListadoFirmante() {
		ListadoFirmanteVentana ventana = new ListadoFirmanteVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	private void abrirListadoinformeConvenio() {
		InformeConvenioVentana ventana = new InformeConvenioVentana();
		ventana.setUpController();
		ventana.setVisible(true);
	}

	
	private void inicializarBasedeDatos() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Deseas inicializar datos?");
		if (respuesta==JOptionPane.YES_OPTION) {
			ConvenioDao convenioDAO =new ConvenioDao();
			convenioDAO.inizializarTabla("tb_convenio");
			convenioDAO.commit();
			FirmantesDao firmantesDAO =new FirmantesDao();
			firmantesDAO.inizializarTabla("tb_firmantes");
			firmantesDAO.commit(); 
			InstitucionDao institucionDAO =new InstitucionDao();
			institucionDAO.inizializarTabla("tb_institucion");
			institucionDAO.commit(); 
			SeguimientoDao seguimientoDao =new SeguimientoDao();
			seguimientoDao.inizializarTabla("tb_seguimiento");
			seguimientoDao.commit(); 
			Unidad_AcademicaDao unidadAcademicaDao =new Unidad_AcademicaDao();
			unidadAcademicaDao.inizializarTabla("tb_unidad_academica");
			unidadAcademicaDao.commit(); 
			UniversidadDao universidadDAO =new UniversidadDao();
			universidadDAO.inizializarTabla("tb_uiversidad");
			universidadDAO.commit(); 
		}
		JOptionPane.showMessageDialog(null, "Datos inicializados correctamente.");
	}
}
