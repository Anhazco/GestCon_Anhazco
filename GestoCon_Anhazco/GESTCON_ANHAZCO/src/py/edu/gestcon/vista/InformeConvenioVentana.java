package py.edu.gestcon.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import py.edu.gestcon.componentes.JtextFieldPersonalizado;
import py.edu.gestcon.controladores.InformeConvenioController;

import javax.swing.JComboBox;
import py.edu.gestcon.componentes.BotonesJtollsbarPersonalizados;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

public class InformeConvenioVentana extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JtextFieldPersonalizado tfDesdeId;
	private JtextFieldPersonalizado tfDesdeNombre;
	private JtextFieldPersonalizado tfHastaId;
	private JtextFieldPersonalizado tfHastaNombre;
	private JComboBox cbOrder;
	private BotonesJtollsbarPersonalizados btnFiltrar;
	private BotonesJtollsbarPersonalizados btnImprimir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InformeConvenioVentana dialog = new InformeConvenioVentana();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUpController();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setUpController() {
		new InformeConvenioController(this);
	}

	/**
	 * Create the dialog.
	 */
	public InformeConvenioVentana() {
		setTitle("Listado de Convenio");
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Desde C\u00F3digo: ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 14, 96, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblHastacdigo = new JLabel("Hasta C\u00F3digo: ");
		lblHastacdigo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblHastacdigo.setBounds(314, 13, 96, 14);
		getContentPane().add(lblHastacdigo);
		
		JLabel lblDesdeNombre = new JLabel("Desde Nombre:");
		lblDesdeNombre.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDesdeNombre.setBounds(10, 58, 96, 14);
		getContentPane().add(lblDesdeNombre);
		
		JLabel lblHastaNombre = new JLabel("Hasta Nombre:");
		lblHastaNombre.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblHastaNombre.setBounds(314, 58, 96, 14);
		getContentPane().add(lblHastaNombre);
		
		JLabel lblTipoDeInforme = new JLabel("Tipo de Informe: ");
		lblTipoDeInforme.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTipoDeInforme.setBounds(590, 13, 108, 14);
		getContentPane().add(lblTipoDeInforme);
		
		tfDesdeId = new JtextFieldPersonalizado();
		tfDesdeId.setBounds(106, 11, 119, 20);
		getContentPane().add(tfDesdeId);
		
		tfHastaId = new JtextFieldPersonalizado();
		tfHastaId.setBounds(403, 11, 119, 20);
		getContentPane().add(tfHastaId);
		
		tfDesdeNombre = new JtextFieldPersonalizado();
		tfDesdeNombre.setBounds(106, 56, 144, 20);
		getContentPane().add(tfDesdeNombre);
		
		tfHastaNombre = new JtextFieldPersonalizado();
		tfHastaNombre.setBounds(409, 56, 144, 20);
		getContentPane().add(tfHastaNombre);
		
		cbOrder = new JComboBox();
		cbOrder.setModel(new DefaultComboBoxModel(new String[] {"Simple", "Detallado"}));
		cbOrder.setBounds(702, 11, 72, 20);
		getContentPane().add(cbOrder);
		
		btnFiltrar = new BotonesJtollsbarPersonalizados();
		btnFiltrar.setText("Filtrar");
		btnFiltrar.setBounds(678, 46, 96, 38);
		getContentPane().add(btnFiltrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 764, 410);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnImprimir = new BotonesJtollsbarPersonalizados();
		btnImprimir.setText("Imprimir");
		btnImprimir.setBounds(667, 517, 107, 33);
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

	public JtextFieldPersonalizado getTfDesdeNombre() {
		return tfDesdeNombre;
	}

	public void setTfDesdeNombre(JtextFieldPersonalizado tfDesdeNombre) {
		this.tfDesdeNombre = tfDesdeNombre;
	}

	public JtextFieldPersonalizado getTfHastaId() {
		return tfHastaId;
	}

	public void setTfHastaId(JtextFieldPersonalizado tfHastaId) {
		this.tfHastaId = tfHastaId;
	}

	public JtextFieldPersonalizado getTfHastaNombre() {
		return tfHastaNombre;
	}

	public void setTfHastaNombre(JtextFieldPersonalizado tfHastaNombre) {
		this.tfHastaNombre = tfHastaNombre;
	}

	public JComboBox getCbOrder() {
		return cbOrder;
	}

	public void setCbOrder(JComboBox cbOrder) {
		this.cbOrder = cbOrder;
	}

	public BotonesJtollsbarPersonalizados getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(BotonesJtollsbarPersonalizados btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	public BotonesJtollsbarPersonalizados getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(BotonesJtollsbarPersonalizados btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
}
