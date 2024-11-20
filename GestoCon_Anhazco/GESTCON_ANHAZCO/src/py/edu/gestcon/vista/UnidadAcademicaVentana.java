package py.edu.gestcon.vista;

import java.awt.EventQueue;

import javax.swing.JDialog;

import py.edu.gestcon.componentes.JDialogGenerico;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import py.edu.gestcon.componentes.JtextFieldPersonalizado;
import py.edu.gestcon.controladores.UnidadAcademicaController;

import javax.swing.JCheckBox;
import javax.swing.JButton;

public class UnidadAcademicaVentana extends JDialogGenerico {
	private JtextFieldPersonalizado tfNombre;
	private JtextFieldPersonalizado tfAbreviatura;
	private JtextFieldPersonalizado tfDireccion;
	private JtextFieldPersonalizado tfEmail;
	private JtextFieldPersonalizado tfTelefono;
	private JtextFieldPersonalizado tfCelular;
	private JtextFieldPersonalizado tfFirmante;
	private JtextFieldPersonalizado tfUniversidad;
	private JButton btnFirmante;
	private JButton btnUniversidad;
	private JCheckBox chEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnidadAcademicaVentana dialog = new UnidadAcademicaVentana();
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
		new UnidadAcademicaController(this);
	}

	/**
	 * Create the dialog.
	 */
	public UnidadAcademicaVentana() {
		getScrollPane().setBounds(20, 347, 749, 273);
		getjPanelFormulario().setLocation(20, 76);
		getLblNewLabel().setBounds(10, 11, 774, 54);
		getLblNewLabel().setText("Unidad Acad\u00E9mica");
		getjPanelFormulario().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 11, 71, 14);
		getjPanelFormulario().add(lblNewLabel);
		
		JLabel lblAbreviatura = new JLabel("Abreviatura:");
		lblAbreviatura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAbreviatura.setBounds(20, 47, 61, 14);
		getjPanelFormulario().add(lblAbreviatura);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccin.setBounds(20, 79, 61, 14);
		getjPanelFormulario().add(lblDireccin);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(10, 117, 71, 14);
		getjPanelFormulario().add(lblEmail);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(361, 11, 52, 14);
		getjPanelFormulario().add(lblTelefono);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCelular.setBounds(361, 54, 52, 14);
		getjPanelFormulario().add(lblCelular);
		
		JLabel lblFirmante = new JLabel("Firmante:");
		lblFirmante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirmante.setBounds(330, 112, 52, 14);
		getjPanelFormulario().add(lblFirmante);
		
		JLabel lblUniversidad = new JLabel("Universidad: ");
		lblUniversidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUniversidad.setBounds(314, 160, 71, 14);
		getjPanelFormulario().add(lblUniversidad);
		
		JLabel lblNewLabel_1 =new JLabel("Estado:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(37, 179, 52, 14);
		getjPanelFormulario().add(lblNewLabel_1);
		
		tfNombre = new JtextFieldPersonalizado();
		tfNombre.setBounds(91, 8, 152, 20);
		getjPanelFormulario().add(tfNombre);
		
		tfAbreviatura = new JtextFieldPersonalizado();
		tfAbreviatura.setBounds(91, 44, 152, 20);
		getjPanelFormulario().add(tfAbreviatura);
		
		tfDireccion = new JtextFieldPersonalizado();
		tfDireccion.setBounds(91, 76, 152, 20);
		getjPanelFormulario().add(tfDireccion);
		
		tfEmail = new JtextFieldPersonalizado();
		tfEmail.setBounds(91, 113, 152, 20);
		getjPanelFormulario().add(tfEmail);
		
		chEstado = new JCheckBox("Activo");
		chEstado.setBounds(95, 175, 97, 23);
		getjPanelFormulario().add(chEstado);
		
		tfTelefono = new JtextFieldPersonalizado();
		tfTelefono.setBounds(423, 8, 122, 20);
		getjPanelFormulario().add(tfTelefono);
		
		tfCelular = new JtextFieldPersonalizado();
		tfCelular.setBounds(423, 51, 122, 21);
		getjPanelFormulario().add(tfCelular);
		
		tfFirmante = new JtextFieldPersonalizado();
		tfFirmante.setBounds(388, 109, 146, 20);
		getjPanelFormulario().add(tfFirmante);
		
		tfUniversidad = new JtextFieldPersonalizado();
		tfUniversidad.setBounds(392, 157, 142, 20);
		getjPanelFormulario().add(tfUniversidad);
		
		btnFirmante = new JButton("....");
		btnFirmante.setBounds(544, 108, 61, 23);
		getjPanelFormulario().add(btnFirmante);
		
		btnUniversidad = new JButton("....");
		btnUniversidad.setBounds(544, 156, 61, 23);
		getjPanelFormulario().add(btnUniversidad);

	}
	public JCheckBox getChEstado() {
		return chEstado;
	}

	public void setChEstado(JCheckBox chEstado) {
		this.chEstado = chEstado;
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

	public JtextFieldPersonalizado getTfDireccion() {
		return tfDireccion;
	}

	public void setTfDireccion(JtextFieldPersonalizado tfDireccion) {
		this.tfDireccion = tfDireccion;
	}

	public JtextFieldPersonalizado getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(JtextFieldPersonalizado tfEmail) {
		this.tfEmail = tfEmail;
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

	public JtextFieldPersonalizado getTfUniversidad() {
		return tfUniversidad;
	}

	public void setTfUniversidad(JtextFieldPersonalizado tfUniversidad) {
		this.tfUniversidad = tfUniversidad;
	}

	public JButton getBtnFirmante() {
		return btnFirmante;
	}

	public void setBtnFirmante(JButton btnFirmante) {
		this.btnFirmante = btnFirmante;
	}

	public JButton getBtnUniversidad() {
		return btnUniversidad;
	}

	public void setBtnUniversidad(JButton btnUniversidad) {
		this.btnUniversidad = btnUniversidad;
	}
}
