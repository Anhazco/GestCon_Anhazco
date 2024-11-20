package py.edu.gestcon.vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import py.edu.gestcon.componentes.JtextFieldPersonalizado;
import py.edu.gestcon.controladores.ListadoInstitucionController;
import py.edu.gestcon.componentes.BotonesJtollsbarPersonalizados;

public class ListadoInstitucionVentana extends JDialog {

	private JTable table;
	private JtextFieldPersonalizado tfDesdeId;
	private JtextFieldPersonalizado tfDesdeDescripcion;
	private JtextFieldPersonalizado tfHastaId;
	private JtextFieldPersonalizado tfHastaDescripcion;
	private JComboBox<String> cbOrder;
	private BotonesJtollsbarPersonalizados btnImprimir;
	private BotonesJtollsbarPersonalizados btnFiltrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoInstitucionVentana dialog = new ListadoInstitucionVentana();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUpController();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setUpController() {
		new ListadoInstitucionController(this);
	}

	/**
	 * Create the dialog.
	 */
	public ListadoInstitucionVentana() {
		setTitle("Listado de Instituciones");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		JLabel lblTitulo = new JLabel("Listado de Instituciones");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 10, 764, 30);
		getContentPane().add(lblTitulo);

		JLabel lblDesdeCodigo = new JLabel("Desde Código:");
		lblDesdeCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDesdeCodigo.setBounds(20, 60, 100, 25);
		getContentPane().add(lblDesdeCodigo);

		JLabel lblHastaCodigo = new JLabel("Hasta Código:");
		lblHastaCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHastaCodigo.setBounds(290, 60, 100, 25);
		getContentPane().add(lblHastaCodigo);

		JLabel lblDesdeDescripcion = new JLabel("Desde Descripción:");
		lblDesdeDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDesdeDescripcion.setBounds(20, 100, 130, 25);
		getContentPane().add(lblDesdeDescripcion);

		JLabel lblHastaDescripcion = new JLabel("Hasta Descripción:");
		lblHastaDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHastaDescripcion.setBounds(315, 100, 120, 25);
		getContentPane().add(lblHastaDescripcion);

		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblOrdenarPor.setBounds(560, 60, 80, 25);
		getContentPane().add(lblOrdenarPor);

		tfDesdeId = new JtextFieldPersonalizado();
		tfDesdeId.setBounds(120, 60, 150, 25);
		getContentPane().add(tfDesdeId);

		tfHastaId = new JtextFieldPersonalizado();
		tfHastaId.setBounds(390, 60, 150, 25);
		getContentPane().add(tfHastaId);

		tfDesdeDescripcion = new JtextFieldPersonalizado();
		tfDesdeDescripcion.setBounds(150, 100, 150, 25);
		getContentPane().add(tfDesdeDescripcion);

		tfHastaDescripcion = new JtextFieldPersonalizado();
		tfHastaDescripcion.setBounds(441, 102, 150, 25);
		getContentPane().add(tfHastaDescripcion);

		cbOrder = new JComboBox<>();
		cbOrder.setModel(new DefaultComboBoxModel<>(new String[] { "ID", "Nombre" }));
		cbOrder.setBounds(640, 60, 120, 25);
		getContentPane().add(cbOrder);

		btnFiltrar = new BotonesJtollsbarPersonalizados();
		btnFiltrar.setText("Filtrar");
		btnFiltrar.setBounds(640, 100, 120, 30);
		getContentPane().add(btnFiltrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 150, 740, 360);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnImprimir = new BotonesJtollsbarPersonalizados();
		btnImprimir.setText("Imprimir");
		btnImprimir.setBounds(640, 520, 120, 30);
		getContentPane().add(btnImprimir);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JtextFieldPersonalizado getTfDesdeId() {
		return tfDesdeId;
	}

	public void setTfDesdeId(JtextFieldPersonalizado tfDesdeId) {
		this.tfDesdeId = tfDesdeId;
	}

	public JtextFieldPersonalizado getTfDesdeDescripcion() {
		return tfDesdeDescripcion;
	}

	public void setTfDesdeDescripcion(JtextFieldPersonalizado tfDesdeDescripcion) {
		this.tfDesdeDescripcion = tfDesdeDescripcion;
	}

	public JtextFieldPersonalizado getTfHastaId() {
		return tfHastaId;
	}

	public void setTfHastaId(JtextFieldPersonalizado tfHastaId) {
		this.tfHastaId = tfHastaId;
	}

	public JtextFieldPersonalizado getTfHastaDescripcion() {
		return tfHastaDescripcion;
	}

	public void setTfHastaDescripcion(JtextFieldPersonalizado tfHastaDescripcion) {
		this.tfHastaDescripcion = tfHastaDescripcion;
	}

	public JComboBox<String> getCbOrder() {
		return cbOrder;
	}

	public void setCbOrder(JComboBox<String> cbOrder) {
		this.cbOrder = cbOrder;
	}

	public BotonesJtollsbarPersonalizados getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(BotonesJtollsbarPersonalizados btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	public BotonesJtollsbarPersonalizados getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(BotonesJtollsbarPersonalizados btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}
}
