package py.edu.gestcon.buscadores;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import py.edu.gestcon.dao.UniversidadDao;
import py.edu.gestcon.entidades.Universidad;
import py.edu.gestcon.interfaces.InterfaceUniversidad;
import py.edu.gestcon.tablas.ModeloTablaUniversidad;

public class BuscadorUniversidad extends JDialog {

	private JTextField tfBuscador;
	private List<Universidad> universidad;
	private UniversidadDao dao;
	private ModeloTablaUniversidad modeloTablaUniversidad;
	private InterfaceUniversidad interfaceUniversidad;
	private JTable table;

	public void setInterface(InterfaceUniversidad interfaceUniversidad) {
		this.interfaceUniversidad = interfaceUniversidad;
	}


	/**
	 * Create the dialog.
	 */
	public BuscadorUniversidad() {

		setTitle("Buscador Universidad");
		setBounds(100, 100, 500, 505);
		setModal(true);
		setLocationRelativeTo(this);
		getContentPane().setLayout(new BorderLayout());

		tfBuscador = new JTextField();
		tfBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) filtrarRegistros();
			}
		});
		getContentPane().add(tfBuscador, BorderLayout.NORTH);
		tfBuscador.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(424, 105, 358, 394);
		getContentPane().add(scrollPane);

		modeloTablaUniversidad = new ModeloTablaUniversidad();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(table.getSelectedRow());
			}
		});
		table.setModel(modeloTablaUniversidad);
		scrollPane.setViewportView(table);

		dao = new UniversidadDao();
		filtrarRegistros();
	}

	private void filtrarRegistros() {
		universidad = dao.recuperarPorFiltro(tfBuscador.getText());
		modeloTablaUniversidad.setLista(universidad);
	}

	private void seleccionarRegistro(int index) {
		if(index<0) return;
		interfaceUniversidad.seleccionarUniversidad(universidad.get(index));
		dispose();
	}
}


