package py.edu.gestcon.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import py.edu.gestcon.componentes.JtextFieldPersonalizado;
import py.edu.gestcon.controladores.ListadoFirmanteController;
import py.edu.gestcon.componentes.BotonesJtollsbarPersonalizados;

public class ListadoFirmanteVentana extends JDialog {
	private JTable table;
	private JtextFieldPersonalizado tfDesdeID;
	private JtextFieldPersonalizado tfDesdeNombre;
	private JtextFieldPersonalizado tfHastaID;
	private JtextFieldPersonalizado tfHastaNombre;
	private JComboBox<String> cbOrder;
	private BotonesJtollsbarPersonalizados btnImprimir;
	private BotonesJtollsbarPersonalizados btnFiltrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoFirmanteVentana dialog = new ListadoFirmanteVentana();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setUpController();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setUpController() {
		new ListadoFirmanteController(this);
	}

	/**
	 * Create the dialog.
	 */
	public ListadoFirmanteVentana() {
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		JLabel lblTitulo = new JLabel("Listado de Firmantes");
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

		JLabel lblDesdeNombre = new JLabel("Desde Nombre:");
		lblDesdeNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDesdeNombre.setBounds(20, 100, 100, 25);
		getContentPane().add(lblDesdeNombre);

		JLabel lblHastaNombre = new JLabel("Hasta Nombre:");
		lblHastaNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHastaNombre.setBounds(290, 100, 100, 25);
		getContentPane().add(lblHastaNombre);

		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblOrdenarPor.setBounds(560, 60, 80, 25);
		getContentPane().add(lblOrdenarPor);

		cbOrder = new JComboBox<>();
		cbOrder.setModel(new DefaultComboBoxModel<>(new String[] { "ID", "Nombre" }));
		cbOrder.setBounds(640, 60, 120, 25);
		getContentPane().add(cbOrder);

		tfDesdeID = new JtextFieldPersonalizado();
		tfDesdeID.setBounds(120, 60, 150, 25);
		getContentPane().add(tfDesdeID);

		tfHastaID = new JtextFieldPersonalizado();
		tfHastaID.setBounds(390, 60, 150, 25);
		getContentPane().add(tfHastaID);

		tfDesdeNombre = new JtextFieldPersonalizado();
		tfDesdeNombre.setBounds(120, 100, 150, 25);
		getContentPane().add(tfDesdeNombre);

		tfHastaNombre = new JtextFieldPersonalizado();
		tfHastaNombre.setBounds(390, 100, 150, 25);
		getContentPane().add(tfHastaNombre);

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

	public JtextFieldPersonalizado getTfDesdeID() {
		return tfDesdeID;
	}

	public void setTfDesdeID(JtextFieldPersonalizado tfDesdeID) {
		this.tfDesdeID = tfDesdeID;
	}

	public JtextFieldPersonalizado getTfDesdeNombre() {
		return tfDesdeNombre;
	}

	public void setTfDesdeNombre(JtextFieldPersonalizado tfDesdeNombre) {
		this.tfDesdeNombre = tfDesdeNombre;
	}

	public JtextFieldPersonalizado getTfHastaID() {
		return tfHastaID;
	}

	public void setTfHastaID(JtextFieldPersonalizado tfHastaID) {
		this.tfHastaID = tfHastaID;
	}

	public JtextFieldPersonalizado getTfHastaNombre() {
		return tfHastaNombre;
	}

	public void setTfHastaNombre(JtextFieldPersonalizado tfHastaNombre) {
		this.tfHastaNombre = tfHastaNombre;
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
