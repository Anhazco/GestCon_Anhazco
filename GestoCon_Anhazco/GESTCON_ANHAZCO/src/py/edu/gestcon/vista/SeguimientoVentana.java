package py.edu.gestcon.vista;

import java.awt.EventQueue;

import javax.swing.JDialog;

import py.edu.gestcon.componentes.JDialogGenerico;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import py.edu.gestcon.componentes.JtextFieldPersonalizado;
import py.edu.gestcon.controladores.SeguimientoController;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class SeguimientoVentana extends JDialogGenerico {
	private JTextArea tfActividad;
	private JtextFieldPersonalizado tfFecha;
	private JtextFieldPersonalizado tfConvenio;
	private JButton btnConvenio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeguimientoVentana dialog = new SeguimientoVentana();
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
		new SeguimientoController(this);
	}

	/**
	 * Create the dialog.
	 */
	public SeguimientoVentana() {
		getScrollPane().setBounds(20, 347, 751, 273);
		getLblNewLabel_1().setSize(56, 14);
		getLblNewLabel_1().setLocation(20, 322);
		getTfBuscador().setLocation(86, 319);
		getLblNewLabel_1().setHorizontalAlignment(SwingConstants.RIGHT);
		getLblNewLabel().setBounds(10, 11, 774, 59);
		getLblNewLabel().setVerticalAlignment(SwingConstants.BOTTOM);
		getLblNewLabel().setText("Seguimiento");
		getjPanelFormulario().setLayout(null);

		JLabel lblNewLabel = new JLabel("Fecha de resoluci\u00F3n:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 27, 117, 14);
		getjPanelFormulario().add(lblNewLabel);

		tfFecha = new JtextFieldPersonalizado();
		tfFecha.setBounds(136, 24, 92, 20);
		getjPanelFormulario().add(tfFecha);

		JLabel lblConvenio = new JLabel("Convenio:");
		lblConvenio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConvenio.setBounds(238, 27, 117, 14);
		getjPanelFormulario().add(lblConvenio);

		tfConvenio = new JtextFieldPersonalizado();
		tfConvenio.setBounds(365, 24, 124, 20);
		getjPanelFormulario().add(tfConvenio);

		btnConvenio = new JButton(".....");
		btnConvenio.setBounds(499, 23, 89, 23);
		getjPanelFormulario().add(btnConvenio);

		JLabel lblActivivdad = new JLabel("Activivdad:");
		lblActivivdad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActivivdad.setBounds(10, 133, 76, 14);
		getjPanelFormulario().add(lblActivivdad);

		tfActividad = new JTextArea();
		tfActividad.setBounds(96, 74, 492, 138);
		getjPanelFormulario().add(tfActividad);

	}

	public JTextArea getTfActividad() {
		return tfActividad;
	}

	public void setTfActividad(JTextArea tfActividad) {
		this.tfActividad = tfActividad;
	}

	public JtextFieldPersonalizado getTfFecha() {
		return tfFecha;
	}

	public void setTfFecha(JtextFieldPersonalizado tfFecha) {
		this.tfFecha = tfFecha;
	}

	public JtextFieldPersonalizado getTfConvenio() {
		return tfConvenio;
	}

	public void setTfConvenio(JtextFieldPersonalizado tfConvenio) {
		this.tfConvenio = tfConvenio;
	}

	public JButton getBtnConvenio() {
		return btnConvenio;
	}

	public void setBtnConvenio(JButton btnConvenio) {
		this.btnConvenio = btnConvenio;
	}
}
