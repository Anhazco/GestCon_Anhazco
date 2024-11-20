package py.edu.gestcon.vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import py.edu.gestcon.componentes.BotonesJtollsbarPersonalizados;
import py.edu.gestcon.controladores.ListadoUniversidadController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoUniversidadVentana extends JDialog {
	private JTextField tfDesdeId;
	private JTextField tfDesdeDescripcion;
	private JTextField tfHastaId;
	private JTextField tfHastaDescripcion;
	private JComboBox<String> cbComIdDescrip;
	private JButton btnFiltarSer;
	private JTable table;
	private JScrollPane scrollPane;
	private BotonesJtollsbarPersonalizados btnImprimir;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ListadoUniversidadVentana dialog = new ListadoUniversidadVentana();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setUpController();
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void setUpController() {
		new ListadoUniversidadController(this);
	}

	public ListadoUniversidadVentana() {
		setTitle("Listado de Universidades");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JLabel lblTitulo = new JLabel("Listado de Universidades");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 10, 764, 30);
		getContentPane().add(lblTitulo);

		JLabel lblDesdeId = new JLabel("Desde Código:");
		lblDesdeId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDesdeId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesdeId.setBounds(20, 60, 100, 25);
		getContentPane().add(lblDesdeId);

		tfDesdeId = new JTextField();
		tfDesdeId.setBounds(130, 60, 100, 25);
		tfDesdeId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
					JOptionPane.showMessageDialog(lblDesdeId, "Ingrese solo números.");
				}
			}
		});
		getContentPane().add(tfDesdeId);

		JLabel lblHastaId = new JLabel("Hasta Código:");
		lblHastaId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHastaId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHastaId.setBounds(260, 60, 100, 25);
		getContentPane().add(lblHastaId);

		tfHastaId = new JTextField();
		tfHastaId.setBounds(370, 60, 100, 25);
		tfHastaId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
					JOptionPane.showMessageDialog(lblHastaId, "Ingrese solo números.");
				}
			}
		});
		getContentPane().add(tfHastaId);

		JLabel lblDesdeDescripcion = new JLabel("Desde Descripción:");
		lblDesdeDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDesdeDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesdeDescripcion.setBounds(20, 100, 120, 25);
		getContentPane().add(lblDesdeDescripcion);

		tfDesdeDescripcion = new JTextField();
		tfDesdeDescripcion.setBounds(150, 100, 150, 25);
		tfDesdeDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (Character.isDigit(e.getKeyChar())) {
					e.consume();
					JOptionPane.showMessageDialog(lblDesdeDescripcion, "Ingrese solo letras.");
				}
			}
		});
		getContentPane().add(tfDesdeDescripcion);

		JLabel lblHastaDescripcion = new JLabel("Hasta Descripción:");
		lblHastaDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblHastaDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHastaDescripcion.setBounds(320, 100, 120, 25);
		getContentPane().add(lblHastaDescripcion);

		tfHastaDescripcion = new JTextField();
		tfHastaDescripcion.setBounds(450, 100, 150, 25);
		tfHastaDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (Character.isDigit(e.getKeyChar())) {
					e.consume();
					JOptionPane.showMessageDialog(lblHastaDescripcion, "Ingrese solo letras.");
				}
			}
		});
		getContentPane().add(tfHastaDescripcion);

		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblOrdenarPor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrdenarPor.setBounds(588, 60, 90, 25);
		getContentPane().add(lblOrdenarPor);

		cbComIdDescrip = new JComboBox<>();
		cbComIdDescrip.setModel(new DefaultComboBoxModel<>(new String[] { "ID", "Descripción" }));
		cbComIdDescrip.setBounds(688, 62, 79, 25);
		getContentPane().add(cbComIdDescrip);

		btnFiltarSer = new JButton("Filtrar");
		btnFiltarSer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFiltarSer.setBounds(681, 99, 90, 30);
		getContentPane().add(btnFiltarSer);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 150, 750, 350);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnImprimir = new BotonesJtollsbarPersonalizados();
		btnImprimir.setText("Imprimir");
		btnImprimir.setBounds(660, 520, 110, 30);
		getContentPane().add(btnImprimir);
	}

	public JTextField getTfDesdeId() {
		return tfDesdeId;
	}

	public void setTfDesdeId(JTextField tfDesdeId) {
		this.tfDesdeId = tfDesdeId;
	}

	public JTextField getTfDesdeDescripcion() {
		return tfDesdeDescripcion;
	}

	public void setTfDesdeDescripcion(JTextField tfDesdeDescripcion) {
		this.tfDesdeDescripcion = tfDesdeDescripcion;
	}

	public JTextField getTfHastaId() {
		return tfHastaId;
	}

	public void setTfHastaId(JTextField tfHastaId) {
		this.tfHastaId = tfHastaId;
	}

	public JTextField getTfHastaDescripcion() {
		return tfHastaDescripcion;
	}

	public void setTfHastaDescripcion(JTextField tfHastaDescripcion) {
		this.tfHastaDescripcion = tfHastaDescripcion;
	}

	public JComboBox<String> getCbComIdDescrip() {
		return cbComIdDescrip;
	}

	public void setCbComIdDescrip(JComboBox<String> cbComIdDescrip) {
		this.cbComIdDescrip = cbComIdDescrip;
	}

	public JButton getBtnFiltarSer() {
		return btnFiltarSer;
	}

	public void setBtnFiltarSer(JButton btnFiltarSer) {
		this.btnFiltarSer = btnFiltarSer;
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

	public BotonesJtollsbarPersonalizados getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(BotonesJtollsbarPersonalizados btnImprimir) {
		this.btnImprimir = btnImprimir;
	}
}
