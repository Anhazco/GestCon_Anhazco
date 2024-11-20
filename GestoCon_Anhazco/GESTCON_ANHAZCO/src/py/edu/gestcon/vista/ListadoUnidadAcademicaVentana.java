package py.edu.gestcon.vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import py.edu.gestcon.componentes.JtextFieldPersonalizado;
import py.edu.gestcon.controladores.ListadoUnidadAcademicaController;
import py.edu.gestcon.componentes.BotonesJtollsbarPersonalizados;

public class ListadoUnidadAcademicaVentana extends JDialog {

	private JTextField tfDesdeNombre;
	private JComboBox<String> cbComIdDescrip;
	private JTable table;
	private JScrollPane scrollPane;
	private JtextFieldPersonalizado tfDesdeId;
	private JtextFieldPersonalizado tfHastaId;
	private JTextField tfHastaNombre;
	private BotonesJtollsbarPersonalizados btnFiltrar;
	private BotonesJtollsbarPersonalizados btnImprimir;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ListadoUnidadAcademicaVentana dialog = new ListadoUnidadAcademicaVentana();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setUpController();
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void setUpController() {
		new ListadoUnidadAcademicaController(this);
	}
	
	public ListadoUnidadAcademicaVentana() {
		setTitle("Listado de Unidades Académicas");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JLabel lblTitulo = new JLabel("Listado de Unidades Académicas");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 10, 764, 30);
		getContentPane().add(lblTitulo);

		JLabel lblDesdeId = new JLabel("Desde Código:");
		lblDesdeId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDesdeId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesdeId.setBounds(20, 60, 100, 25);
		getContentPane().add(lblDesdeId);

		tfDesdeId = new JtextFieldPersonalizado();
		tfDesdeId.setBounds(130, 60, 150, 25);
		getContentPane().add(tfDesdeId);

		JLabel lblHastaId = new JLabel("Hasta Código:");
		lblHastaId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHastaId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHastaId.setBounds(300, 60, 100, 25);
		getContentPane().add(lblHastaId);

		tfHastaId = new JtextFieldPersonalizado();
		tfHastaId.setBounds(410, 60, 150, 25);
		getContentPane().add(tfHastaId);

		JLabel lblDesdeDescripcion = new JLabel("Desde Nombre:");
		lblDesdeDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDesdeDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesdeDescripcion.setBounds(20, 100, 100, 25);
		getContentPane().add(lblDesdeDescripcion);

		tfDesdeNombre = new JTextField();
		tfDesdeNombre.setBounds(130, 100, 150, 25);
		tfDesdeNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (Character.isDigit(e.getKeyChar())) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se permiten letras en el nombre.");
				}
			}
		});
		getContentPane().add(tfDesdeNombre);

		JLabel lblHastaDescripcion = new JLabel("Hasta Nombre:");
		lblHastaDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHastaDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHastaDescripcion.setBounds(300, 100, 100, 25);
		getContentPane().add(lblHastaDescripcion);

		tfHastaNombre = new JTextField();
		tfHastaNombre.setBounds(410, 100, 150, 25);
		getContentPane().add(tfHastaNombre);

		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblOrdenarPor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrdenarPor.setBounds(580, 60, 80, 25);
		getContentPane().add(lblOrdenarPor);

		cbComIdDescrip = new JComboBox<>();
		cbComIdDescrip.setModel(new DefaultComboBoxModel<>(new String[] { "ID", "Nombre" }));
		cbComIdDescrip.setBounds(670, 60, 100, 25);
		getContentPane().add(cbComIdDescrip);

		btnFiltrar = new BotonesJtollsbarPersonalizados();
		btnFiltrar.setText("Filtrar");
		btnFiltrar.setBounds(670, 100, 100, 30);
		getContentPane().add(btnFiltrar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 150, 750, 360);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnImprimir = new BotonesJtollsbarPersonalizados();
		btnImprimir.setText("Imprimir");
		btnImprimir.setBounds(670, 520, 100, 30);
		getContentPane().add(btnImprimir);
	}

	public JTextField getTfDesdeNombre() {
		return tfDesdeNombre;
	}

	public void setTfDesdeNombre(JTextField tfDesdeNombre) {
		this.tfDesdeNombre = tfDesdeNombre;
	}

	public JComboBox<String> getCbComIdDescrip() {
		return cbComIdDescrip;
	}

	public void setCbComIdDescrip(JComboBox<String> cbComIdDescrip) {
		this.cbComIdDescrip = cbComIdDescrip;
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

	public JtextFieldPersonalizado getTfDesdeId() {
		return tfDesdeId;
	}

	public void setTfDesdeId(JtextFieldPersonalizado tfDesdeId) {
		this.tfDesdeId = tfDesdeId;
	}

	public JtextFieldPersonalizado getTfHastaId() {
		return tfHastaId;
	}

	public void setTfHastaId(JtextFieldPersonalizado tfHastaId) {
		this.tfHastaId = tfHastaId;
	}

	public JTextField getTfHastaNombre() {
		return tfHastaNombre;
	}

	public void setTfHastaNombre(JTextField tfHastaNombre) {
		this.tfHastaNombre = tfHastaNombre;
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
}
