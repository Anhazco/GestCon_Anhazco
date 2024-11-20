package py.edu.gestcon.componentes;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import py.edu.gestcon.interfaces.InterfaceAcciones;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class JDialogGenerico extends JDialog implements ActionListener {
	private JPanel jPanelFormulario;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private BotonesPrincipales btnNuevo;
	private BotonesPrincipales btnGuardar;
	private BotonesPrincipales btnModificar;
	private BotonesPrincipales btnActualizar;
	private BotonesPrincipales btnCancelar;
	private BotonesPrincipales btnEliminar;
	private BotonesPrincipales btnSalir;
	private InterfaceAcciones interfaceAcciones;
	private JtextFieldPersonalizado tfBuscador;

	public void setInterfaceAcciones(InterfaceAcciones interfaceAcciones) {
		this.interfaceAcciones=interfaceAcciones;
	}

	/**
	 * Create the dialog.
	 */
	public JDialogGenerico() {
		setBounds(100, 100, 802, 765);
		setResizable(false);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);

		lblNewLabel = new JLabel("Titulo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 56));
		lblNewLabel.setBounds(10, 11, 774, 67);
		getContentPane().add(lblNewLabel);

		jPanelFormulario = new JPanel();
		jPanelFormulario.setBounds(20, 76, 615, 235);
		getContentPane().add(jPanelFormulario);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 347, 615, 273);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblNewLabel_1 = new JLabel("Buscar:");
		lblNewLabel_1.setBounds(20, 322, 44, 14);
		getContentPane().add(lblNewLabel_1);

		tfBuscador = new JtextFieldPersonalizado();
		tfBuscador.setBounds(62, 319, 268, 20);
		getContentPane().add(tfBuscador);

		btnNuevo = new BotonesPrincipales();
		btnNuevo.setText("Nuevo");
		btnNuevo.setBounds(678, 76, 93, 95);
		getContentPane().add(btnNuevo);

		btnGuardar = new BotonesPrincipales();
		btnGuardar.setText("Guardar");
		btnGuardar.setBounds(678, 213, 93, 95);
		getContentPane().add(btnGuardar);

		btnModificar = new BotonesPrincipales();
		btnModificar.setText("Modificar");
		btnModificar.setBounds(20, 631, 93, 79);
		getContentPane().add(btnModificar);

		btnActualizar = new BotonesPrincipales();
		btnActualizar.setText("Actualizar");
		btnActualizar.setBounds(154, 631, 93, 79);
		getContentPane().add(btnActualizar);

		btnCancelar = new BotonesPrincipales();
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(297, 631, 93, 79);
		getContentPane().add(btnCancelar);

		btnEliminar = new BotonesPrincipales();
		btnEliminar.setText("Eliminar");
		btnEliminar.setBounds(542, 631, 93, 79);
		getContentPane().add(btnEliminar);

		btnSalir = new BotonesPrincipales();
		btnSalir.setText("Salir");
		btnSalir.setBounds(678, 631, 93, 79);
		getContentPane().add(btnSalir);
		
		setearEventos();
	}
	
	public JtextFieldPersonalizado getTfBuscador() {
		return tfBuscador;
	}

	public void setTfBuscador(JtextFieldPersonalizado tfBuscador) {
		this.tfBuscador = tfBuscador;
	}

	private void setearEventos() {
		btnNuevo.addActionListener(this);
		btnModificar.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnSalir.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);
		btnActualizar.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Nuevo":
			interfaceAcciones.nuevo();
			break;
		case "Modificar":
			interfaceAcciones.modificar();
			break;
		case "Eliminar":
			interfaceAcciones.eliminar();
			break;
		case "Salir":
			interfaceAcciones.salir();
			break;
		case "Cancelar":
			interfaceAcciones.cancelar();
			break;
		case "Guardar":
			interfaceAcciones.guardar();
			break;
		case "Actualizar":
			interfaceAcciones.actualizar();
			break;
		}
	}

	public JPanel getjPanelFormulario() {
		return jPanelFormulario;
	}

	public void setjPanelFormulario(JPanel jPanelFormulario) {
		this.jPanelFormulario = jPanelFormulario;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public void setLblNewLabel_1(JLabel lblNewLabel_1) {
		this.lblNewLabel_1 = lblNewLabel_1;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public BotonesPrincipales getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(BotonesPrincipales btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public BotonesPrincipales getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(BotonesPrincipales btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public BotonesPrincipales getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(BotonesPrincipales btnModificar) {
		this.btnModificar = btnModificar;
	}

	public BotonesPrincipales getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(BotonesPrincipales btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	public BotonesPrincipales getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(BotonesPrincipales btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public BotonesPrincipales getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(BotonesPrincipales btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public BotonesPrincipales getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(BotonesPrincipales btnSalir) {
		this.btnSalir = btnSalir;
	}

	public InterfaceAcciones getInterfaceAcciones() {
		return interfaceAcciones;
	}
}
