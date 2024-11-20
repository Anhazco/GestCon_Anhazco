package py.edu.gestcon.vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import py.edu.gestcon.componentes.JDialogGenerico;
import py.edu.gestcon.componentes.JtextFieldPersonalizado;
import py.edu.gestcon.controladores.FirmanteController;
import py.edu.gestcon.utilidades.UtilidadesFecha;

public class FirmanteVentana extends JDialogGenerico {
	private JtextFieldPersonalizado tfDireccion;
	private JtextFieldPersonalizado tfBarrio;
	private JtextFieldPersonalizado tfCiudad;
	private JFormattedTextField tfFdeResolucion;
	private JtextFieldPersonalizado tfRdeRenombramiento;
	private JtextFieldPersonalizado tfCargo;
	private JtextFieldPersonalizado tfCedula;
	private JtextFieldPersonalizado tfApellido;
	private JtextFieldPersonalizado tfNombre;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirmanteVentana dialog = new FirmanteVentana();
					dialog.setUpControlador();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setUpControlador() {
		new FirmanteController(this);
	}

	/**
	 * Create the dialog.
	 */
	public FirmanteVentana() {
		getScrollPane().setSize(753, 273);
		getBtnSalir().setLocation(678, 641);
		getBtnEliminar().setLocation(542, 641);
		getBtnCancelar().setLocation(293, 641);
		getBtnActualizar().setLocation(154, 641);
		getBtnModificar().setLocation(20, 641);
		getLblNewLabel().setBounds(10, 11, 774, 47);
		getScrollPane().setLocation(20, 347);
		getjPanelFormulario().setLocation(20, 76);
		setTitle("Firmantes");
		getLblNewLabel().setText("Firmante");
		getjPanelFormulario().setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre.setBounds(10, 11, 46, 14);
		getjPanelFormulario().add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido.setBounds(10, 41, 46, 14);
		getjPanelFormulario().add(lblApellido);

		JLabel lblCi = new JLabel("C.I:");
		lblCi.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCi.setBounds(10, 71, 46, 14);
		getjPanelFormulario().add(lblCi);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCargo.setBounds(10, 96, 46, 14);
		getjPanelFormulario().add(lblCargo);

		JLabel lblResolucionDeNombramiento = new JLabel("Resolucion de nombramiento:");
		lblResolucionDeNombramiento.setHorizontalAlignment(SwingConstants.TRAILING);
		lblResolucionDeNombramiento.setBounds(10, 140, 152, 14);
		getjPanelFormulario().add(lblResolucionDeNombramiento);

		JLabel lblFechaDeResolucin = new JLabel("Fecha de resoluci\u00F3n:");
		lblFechaDeResolucin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFechaDeResolucin.setBounds(10, 189, 114, 14);
		getjPanelFormulario().add(lblFechaDeResolucin);

		tfNombre = new JtextFieldPersonalizado();
		tfNombre.setBounds(65, 8, 228, 20);
		getjPanelFormulario().add(tfNombre);

		tfApellido = new JtextFieldPersonalizado();
		tfApellido.setBounds(66, 38, 228, 20);
		getjPanelFormulario().add(tfApellido);

		tfCedula = new JtextFieldPersonalizado();
		tfCedula.setBounds(66, 68, 114, 20);
		getjPanelFormulario().add(tfCedula);

		tfCargo = new JtextFieldPersonalizado();
		tfCargo.setBounds(65, 93, 228, 20);
		getjPanelFormulario().add(tfCargo);

		tfRdeRenombramiento = new JtextFieldPersonalizado();
		tfRdeRenombramiento.setBounds(169, 137, 124, 20);
		getjPanelFormulario().add(tfRdeRenombramiento);

		tfFdeResolucion = new JFormattedTextField(UtilidadesFecha.getFormato());
		tfFdeResolucion.setBounds(133, 186, 73, 20);
		getjPanelFormulario().add(tfFdeResolucion);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDireccin.setBounds(370, 11, 59, 14);
		getjPanelFormulario().add(lblDireccin);

		JLabel lblBarrio = new JLabel("Barrio:");
		lblBarrio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBarrio.setBounds(384, 51, 46, 14);
		getjPanelFormulario().add(lblBarrio);

		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCiudad.setBounds(384, 96, 46, 14);
		getjPanelFormulario().add(lblCiudad);

		tfDireccion = new JtextFieldPersonalizado();
		tfDireccion.setBounds(436, 8, 152, 20);
		getjPanelFormulario().add(tfDireccion);

		tfBarrio = new JtextFieldPersonalizado();
		tfBarrio.setBounds(436, 48, 152, 20);
		getjPanelFormulario().add(tfBarrio);

		tfCiudad = new JtextFieldPersonalizado();
		tfCiudad.setBounds(436, 93, 152, 20);
		getjPanelFormulario().add(tfCiudad);

	}

	public JtextFieldPersonalizado getTfDireccion() {
		return tfDireccion;
	}

	public void setTfDireccion(JtextFieldPersonalizado tfDireccion) {
		this.tfDireccion = tfDireccion;
	}

	public JtextFieldPersonalizado getTfBarrio() {
		return tfBarrio;
	}

	public void setTfBarrio(JtextFieldPersonalizado tfBarrio) {
		this.tfBarrio = tfBarrio;
	}

	public JtextFieldPersonalizado getTfCiudad() {
		return tfCiudad;
	}

	public void setTfCiudad(JtextFieldPersonalizado tfCiudad) {
		this.tfCiudad = tfCiudad;
	}

	public JFormattedTextField getTfFdeResolucion() {
		return tfFdeResolucion;
	}

	public void setTfFdeResolucion(JFormattedTextField tfFdeResolucion) {
		this.tfFdeResolucion = tfFdeResolucion;
	}

	public JtextFieldPersonalizado getTfRdeRenombramiento() {
		return tfRdeRenombramiento;
	}

	public void setTfRdeRenombramiento(JtextFieldPersonalizado tfRdeRenombramiento) {
		this.tfRdeRenombramiento = tfRdeRenombramiento;
	}

	public JtextFieldPersonalizado getTfCargo() {
		return tfCargo;
	}

	public void setTfCargo(JtextFieldPersonalizado tfCargo) {
		this.tfCargo = tfCargo;
	}

	public JtextFieldPersonalizado getTfCedula() {
		return tfCedula;
	}

	public void setTfCedula(JtextFieldPersonalizado tfCedula) {
		this.tfCedula = tfCedula;
	}

	public JtextFieldPersonalizado getTfApellido() {
		return tfApellido;
	}

	public void setTfApellido(JtextFieldPersonalizado tfApellido) {
		this.tfApellido = tfApellido;
	}

	public JtextFieldPersonalizado getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JtextFieldPersonalizado tfNombre) {
		this.tfNombre = tfNombre;
	}
}
