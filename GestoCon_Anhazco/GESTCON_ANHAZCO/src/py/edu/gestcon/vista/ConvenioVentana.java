package py.edu.gestcon.vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

import py.edu.gestcon.componentes.JDialogGenerico;
import javax.swing.JLabel;
import py.edu.gestcon.componentes.JtextFieldPersonalizado;
import py.edu.gestcon.controladores.ConvenioController;
import py.edu.gestcon.utilidades.UtilidadesFecha;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class ConvenioVentana extends JDialogGenerico {
	private JLabel fechaCaducidad;
	private JtextFieldPersonalizado tfNombre;
	private JtextFieldPersonalizado tfCategoria;
	private JtextFieldPersonalizado tfDuracion;
	private JtextFieldPersonalizado tfObjetivos;
	private JCheckBox chEstado;
	private JFormattedTextField tfInicio;
	private JFormattedTextField tfCaducidad;
	private JtextFieldPersonalizado tfUnidadAcademica;
	private JtextFieldPersonalizado tfInstitucion;
	private JButton btnUnidadAcademica;
	private JButton btnInstitucion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConvenioVentana dialog = new ConvenioVentana();
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
		new ConvenioController(this);
	}

	/**
	 * Create the dialog.
	 */
	public ConvenioVentana() {
		getScrollPane().setBounds(20, 347, 751, 273);
		getLblNewLabel_1().setLocation(30, 319);
		getTfBuscador().setLocation(83, 316);
		getLblNewLabel_1().setHorizontalAlignment(SwingConstants.RIGHT);
		getLblNewLabel().setBounds(10, 11, 774, 51);
		getLblNewLabel().setText("Convenio");
		getjPanelFormulario().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(0, 11, 60, 14);
		getjPanelFormulario().add(lblNewLabel);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategoria.setBounds(-12, 50, 70, 14);
		getjPanelFormulario().add(lblCategoria);

		JLabel lblDuraci = new JLabel("Duraci\u00F3n:");
		lblDuraci.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuraci.setBounds(-10, 90, 70, 14);
		getjPanelFormulario().add(lblDuraci);

		JLabel lblObjetivos = new JLabel("Objetivos:");
		lblObjetivos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObjetivos.setBounds(-12, 131, 70, 14);
		getjPanelFormulario().add(lblObjetivos);

		JLabel fechaInicio = new JLabel("Fecha de inicio:");
		fechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaInicio.setBounds(365, 11, 81, 14);
		getjPanelFormulario().add(fechaInicio);

		fechaCaducidad = new JLabel("Fecha de caducidad:");
		fechaCaducidad.setHorizontalAlignment(SwingConstants.RIGHT);
		fechaCaducidad.setBounds(339, 39, 107, 14);
		getjPanelFormulario().add(fechaCaducidad);

		JLabel lblNewLabel_1 = new JLabel("Unidad Academica:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(294, 109, 96, 14);
		getjPanelFormulario().add(lblNewLabel_1);

		JLabel lblInstitucin = new JLabel("Instituci\u00F3n:");
		lblInstitucin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInstitucin.setBounds(320, 146, 70, 14);
		getjPanelFormulario().add(lblInstitucin);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 182, 70, 14);
		getjPanelFormulario().add(lblEstado);

		tfNombre = new JtextFieldPersonalizado();
		tfNombre.setBounds(68, 8, 160, 20);
		getjPanelFormulario().add(tfNombre);

		tfCategoria = new JtextFieldPersonalizado();
		tfCategoria.setBounds(68, 47, 160, 20);
		getjPanelFormulario().add(tfCategoria);

		tfDuracion = new JtextFieldPersonalizado();
		tfDuracion.setBounds(68, 87, 160, 20);
		getjPanelFormulario().add(tfDuracion);

		tfObjetivos = new JtextFieldPersonalizado();
		tfObjetivos.setBounds(68, 128, 160, 20);
		getjPanelFormulario().add(tfObjetivos);

		chEstado = new JCheckBox("Activo");
		chEstado.setBounds(68, 178, 97, 23);
		getjPanelFormulario().add(chEstado);

		tfInicio = new JFormattedTextField(UtilidadesFecha.getFormato());
		tfInicio.setBounds(456, 8, 88, 20);
		getjPanelFormulario().add(tfInicio);

		tfCaducidad = new JFormattedTextField(UtilidadesFecha.getFormato());
		tfCaducidad.setBounds(456, 36, 88, 20);
		getjPanelFormulario().add(tfCaducidad);

		tfUnidadAcademica = new JtextFieldPersonalizado();
		tfUnidadAcademica.setBounds(395, 106, 140, 20);
		getjPanelFormulario().add(tfUnidadAcademica);

		tfInstitucion = new JtextFieldPersonalizado();
		tfInstitucion.setBounds(395, 143, 140, 20);
		getjPanelFormulario().add(tfInstitucion);

		btnUnidadAcademica = new JButton(".....");
		btnUnidadAcademica.setBounds(545, 105, 60, 23);
		getjPanelFormulario().add(btnUnidadAcademica);

		btnInstitucion = new JButton(".....");
		btnInstitucion.setBounds(545, 142, 60, 23);
		getjPanelFormulario().add(btnInstitucion);
	}

	public JtextFieldPersonalizado getTfCategoria() {
		return tfCategoria;
	}

	public void setTfCategoria(JtextFieldPersonalizado tfCategoria) {
		this.tfCategoria = tfCategoria;
	}

	public JLabel getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(JLabel fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public JtextFieldPersonalizado getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JtextFieldPersonalizado tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JtextFieldPersonalizado getTfConvenio() {
		return tfCategoria;
	}

	public void setTfConvenio(JtextFieldPersonalizado tfConvenio) {
		this.tfCategoria = tfConvenio;
	}

	public JtextFieldPersonalizado getTfDuracion() {
		return tfDuracion;
	}

	public void setTfDuracion(JtextFieldPersonalizado tfDuracion) {
		this.tfDuracion = tfDuracion;
	}

	public JtextFieldPersonalizado getTfObjetivos() {
		return tfObjetivos;
	}

	public void setTfObjetivos(JtextFieldPersonalizado tfObjetivos) {
		this.tfObjetivos = tfObjetivos;
	}

	public JCheckBox getChEstado() {
		return chEstado;
	}

	public void setChEstado(JCheckBox chEstado) {
		this.chEstado = chEstado;
	}

	public JFormattedTextField getTfInicio() {
		return tfInicio;
	}

	public void setTfInicio(JFormattedTextField tfInicio) {
		this.tfInicio = tfInicio;
	}

	public JFormattedTextField getTfCaducidad() {
		return tfCaducidad;
	}

	public void setTfCaducidad(JFormattedTextField tfCaducidad) {
		this.tfCaducidad = tfCaducidad;
	}

	public JtextFieldPersonalizado getTfUnidadAcademica() {
		return tfUnidadAcademica;
	}

	public void setTfUnidadAcademica(JtextFieldPersonalizado tfUnidadAcademica) {
		this.tfUnidadAcademica = tfUnidadAcademica;
	}

	public JtextFieldPersonalizado getTfInstitucion() {
		return tfInstitucion;
	}

	public void setTfInstitucion(JtextFieldPersonalizado tfInstitucion) {
		this.tfInstitucion = tfInstitucion;
	}

	public JButton getBtnUnidadAcademica() {
		return btnUnidadAcademica;
	}

	public void setBtnUnidadAcademica(JButton btnUnidadAcademica) {
		this.btnUnidadAcademica = btnUnidadAcademica;
	}

	public JButton getBtnInstitucion() {
		return btnInstitucion;
	}

	public void setBtnInstitucion(JButton btnInstitucion) {
		this.btnInstitucion = btnInstitucion;
	}	
}
