package py.edu.gestcon.vista;

import java.awt.EventQueue;

import javax.swing.JDialog;

import py.edu.gestcon.componentes.JDialogGenerico;
import javax.swing.JLabel;
import py.edu.gestcon.componentes.JtextFieldPersonalizado;
import py.edu.gestcon.controladores.InstitucionController;
import py.edu.gestcon.componentes.BotonesPrincipales;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class InstitucionVentana extends JDialogGenerico {
	private JtextFieldPersonalizado tfNombre;
	private JtextFieldPersonalizado tfAbreviatura;
	private JtextFieldPersonalizado tfRubro;
	private JtextFieldPersonalizado tfTipo;
	private JtextFieldPersonalizado tfDireccion;
	private JtextFieldPersonalizado tfTelefono;
	private JtextFieldPersonalizado tfCelular;
	private JtextFieldPersonalizado tfFirmante;
	private JButton btnFirmante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstitucionVentana dialog = new InstitucionVentana();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setUpControlador();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setUpControlador() {
		new InstitucionController(this);
	}

	/**
	 * Create the dialog.
	 */
	public InstitucionVentana() {
		getBtnSalir().setBounds(678, 631, 93, 79);
		getBtnEliminar().setBounds(542, 631, 93, 79);
		getBtnCancelar().setBounds(297, 631, 93, 79);
		getBtnActualizar().setBounds(154, 631, 93, 79);
		getBtnModificar().setBounds(20, 631, 93, 79);
		getBtnGuardar().setBounds(678, 213, 93, 95);
		getBtnNuevo().setBounds(678, 68, 93, 95);
		getTfBuscador().setBounds(88, 319, 268, 20);
		getLblNewLabel_1().setBounds(30, 322, 54, 14);
		getScrollPane().setBounds(20, 347, 751, 273);
		getjPanelFormulario().setBounds(20, 68, 648, 235);
		getLblNewLabel().setBounds(10, 11, 774, 46);
		getLblNewLabel_1().setHorizontalAlignment(SwingConstants.RIGHT);
		getLblNewLabel().setText("Instituci\u00F3n");
		getjPanelFormulario().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setBounds(-2, 11, 58, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblNewLabel);
		
		JLabel lblAbreviatura = new JLabel("Abreviatura:");
		lblAbreviatura.setBounds(-2, 58, 79, 14);
		lblAbreviatura.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblAbreviatura);
		
		JLabel lblRubro = new JLabel("Rubro:");
		lblRubro.setBounds(10, 103, 46, 14);
		lblRubro.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblRubro);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 141, 46, 14);
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblTipo);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(11, 185, 66, 14);
		lblDireccin.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblDireccin);
		
		tfNombre = new JtextFieldPersonalizado();
		tfNombre.setBounds(66, 8, 244, 20);
		getjPanelFormulario().add(tfNombre);
		
		tfAbreviatura = new JtextFieldPersonalizado();
		tfAbreviatura.setBounds(87, 55, 139, 20);
		getjPanelFormulario().add(tfAbreviatura);
		
		tfRubro = new JtextFieldPersonalizado();
		tfRubro.setBounds(66, 100, 160, 20);
		getjPanelFormulario().add(tfRubro);
		
		tfTipo = new JtextFieldPersonalizado();
		tfTipo.setBounds(64, 139, 160, 20);
		getjPanelFormulario().add(tfTipo);
		
		tfDireccion = new JtextFieldPersonalizado();
		tfDireccion.setBounds(87, 183, 139, 20);
		getjPanelFormulario().add(tfDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(372, 25, 58, 14);
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblTelefono);
		
		JLabel lblCelular = new JLabel("Celular:  ");
		lblCelular.setBounds(372, 71, 58, 14);
		lblCelular.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblCelular);
		
		JLabel lblFirmante = new JLabel("Firmante:");
		lblFirmante.setBounds(317, 156, 58, 14);
		lblFirmante.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblFirmante);
		
		tfTelefono = new JtextFieldPersonalizado();
		tfTelefono.setBounds(440, 23, 148, 20);
		getjPanelFormulario().add(tfTelefono);
		
		tfCelular = new JtextFieldPersonalizado();
		tfCelular.setBounds(440, 68, 148, 20);
		getjPanelFormulario().add(tfCelular);
		
		tfFirmante = new JtextFieldPersonalizado();
		tfFirmante.setBounds(384, 153, 128, 20);
		getjPanelFormulario().add(tfFirmante);
		
		btnFirmante = new JButton(".....");
		btnFirmante.setBounds(522, 151, 66, 25);
		getjPanelFormulario().add(btnFirmante);
		getContentPane().setLayout(null);

	}

	public JtextFieldPersonalizado getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JtextFieldPersonalizado tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JtextFieldPersonalizado getTfAbreviatura() {
		return tfAbreviatura;
	}

	public void setTfAbreviatura(JtextFieldPersonalizado tfAbreviatura) {
		this.tfAbreviatura = tfAbreviatura;
	}

	public JtextFieldPersonalizado getTfRubro() {
		return tfRubro;
	}

	public void setTfRubro(JtextFieldPersonalizado tfRubro) {
		this.tfRubro = tfRubro;
	}

	public JtextFieldPersonalizado getTfTipo() {
		return tfTipo;
	}

	public void setTfTipo(JtextFieldPersonalizado tfTipo) {
		this.tfTipo = tfTipo;
	}

	public JtextFieldPersonalizado getTfDireccion() {
		return tfDireccion;
	}

	public void setTfDireccion(JtextFieldPersonalizado tfDireccion) {
		this.tfDireccion = tfDireccion;
	}

	public JtextFieldPersonalizado getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(JtextFieldPersonalizado tfTelefono) {
		this.tfTelefono = tfTelefono;
	}

	public JtextFieldPersonalizado getTfCelular() {
		return tfCelular;
	}

	public void setTfCelular(JtextFieldPersonalizado tfCelular) {
		this.tfCelular = tfCelular;
	}

	public JtextFieldPersonalizado getTfFirmante() {
		return tfFirmante;
	}

	public void setTfFirmante(JtextFieldPersonalizado tfFirmante) {
		this.tfFirmante = tfFirmante;
	}

	public JButton getBtnFirmante() {
		return btnFirmante;
	}

	public void setBtnFirmante(JButton btnFirmante) {
		this.btnFirmante = btnFirmante;
	}
}
