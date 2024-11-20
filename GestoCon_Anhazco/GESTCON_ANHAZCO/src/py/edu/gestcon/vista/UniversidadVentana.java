package py.edu.gestcon.vista;

import java.awt.EventQueue;

import javax.swing.JDialog;

import py.edu.gestcon.componentes.JDialogGenerico;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import py.edu.gestcon.componentes.JtextFieldPersonalizado;
import py.edu.gestcon.controladores.UniversidadController;

public class UniversidadVentana extends JDialogGenerico {
	private JtextFieldPersonalizado tfDescripcion;
	private JtextFieldPersonalizado tfAbreviatura;
	private JtextFieldPersonalizado tfTelefono;
	private JtextFieldPersonalizado tfEmail;
	private JCheckBox chEstado;
	private JtextFieldPersonalizado tfCelular;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UniversidadVentana dialog = new UniversidadVentana();
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
		new UniversidadController(this);
	}

	/**
	 * Create the dialog.
	 */
	public UniversidadVentana() {
		getScrollPane().setBounds(20, 347, 751, 273);
		getLblNewLabel_1().setLocation(30, 319);
		getLblNewLabel_1().setHorizontalAlignment(SwingConstants.RIGHT);
		getTfBuscador().setLocation(84, 316);
		getLblNewLabel().setBounds(10, 11, 774, 54);
		getLblNewLabel().setText("Universidad");
		getjPanelFormulario().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descripci\u00F3n:");
		lblNewLabel.setBounds(1, 26, 79, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblNewLabel);
		
		JLabel lblAbreviatura = new JLabel("Abreviatura:");
		lblAbreviatura.setBounds(10, 64, 70, 14);
		lblAbreviatura.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblAbreviatura);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(10, 102, 70, 14);
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblTelefono);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 140, 70, 14);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblEmail);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 190, 70, 14);
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblEstado);
		
		chEstado = new JCheckBox("Activo");
		chEstado.setBounds(86, 186, 97, 23);
		getjPanelFormulario().add(chEstado);
		
		JLabel lblCelular = new JLabel("Celular: ");
		lblCelular.setBounds(354, 51, 70, 14);
		lblCelular.setHorizontalAlignment(SwingConstants.RIGHT);
		getjPanelFormulario().add(lblCelular);
		
		tfDescripcion = new JtextFieldPersonalizado();
		tfDescripcion.setBounds(90, 23, 171, 20);
		getjPanelFormulario().add(tfDescripcion);
		
		tfAbreviatura = new JtextFieldPersonalizado();
		tfAbreviatura.setBounds(90, 61, 171, 20);
		getjPanelFormulario().add(tfAbreviatura);
		
		tfTelefono = new JtextFieldPersonalizado();
		tfTelefono.setBounds(90, 99, 171, 20);
		getjPanelFormulario().add(tfTelefono);
		
		tfEmail = new JtextFieldPersonalizado();
		tfEmail.setBounds(91, 137, 171, 20);
		getjPanelFormulario().add(tfEmail);
		
		tfCelular = new JtextFieldPersonalizado();
		tfCelular.setBounds(426, 48, 163, 20);
		getjPanelFormulario().add(tfCelular);

	}

	public JtextFieldPersonalizado getTfDescripcion() {
		return tfDescripcion;
	}

	public void setTfDescripcion(JtextFieldPersonalizado tfDescripcion) {
		this.tfDescripcion = tfDescripcion;
	}

	public JtextFieldPersonalizado getTfAbreviatura() {
		return tfAbreviatura;
	}

	public void setTfAbreviatura(JtextFieldPersonalizado tfAbreviatura) {
		this.tfAbreviatura = tfAbreviatura;
	}

	public JtextFieldPersonalizado getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(JtextFieldPersonalizado tfTelefono) {
		this.tfTelefono = tfTelefono;
	}

	public JtextFieldPersonalizado getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(JtextFieldPersonalizado tfEmail) {
		this.tfEmail = tfEmail;
	}

	public JCheckBox getChEstado() {
		return chEstado;
	}

	public void setChEstado(JCheckBox chEstado) {
		this.chEstado = chEstado;
	}

	public JtextFieldPersonalizado getTfCelular() {
		return tfCelular;
	}

	public void setTfCelular(JtextFieldPersonalizado tfCelular) {
		this.tfCelular = tfCelular;
	}
}
