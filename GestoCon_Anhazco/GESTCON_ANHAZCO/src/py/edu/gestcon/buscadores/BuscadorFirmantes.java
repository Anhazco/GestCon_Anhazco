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

import py.edu.gestcon.dao.FirmantesDao;
import py.edu.gestcon.entidades.Firmantes;
import py.edu.gestcon.interfaces.InterfaceFirmante;
import py.edu.gestcon.tablas.ModeloTablaFirmante;

public class BuscadorFirmantes extends JDialog {

	private JTextField tfBuscador;
	private List<Firmantes> firmantes;
	private FirmantesDao dao;
	private ModeloTablaFirmante modeloTablaFirmante;
	private InterfaceFirmante interfaceFirmante;
	private JTable table;

	public void setInterface(InterfaceFirmante interfaceFirmante) {
		this.interfaceFirmante = interfaceFirmante;
	}
	/**
	 * Create the dialog.
	 */
	public BuscadorFirmantes() {
		setTitle("Buscador Firmantes");
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

		modeloTablaFirmante = new ModeloTablaFirmante();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(table.getSelectedRow());
			}
		});
		table.setModel(modeloTablaFirmante);
		scrollPane.setViewportView(table);

		dao = new FirmantesDao();
		filtrarRegistros();
	}

	private void filtrarRegistros() {
		firmantes = dao.recuperarPorFiltro(tfBuscador.getText());
		modeloTablaFirmante.setLista(firmantes);
	}

	private void seleccionarRegistro(int index) {
		if(index<0) return;
		interfaceFirmante.seleccionarFirmante(firmantes.get(index));
		dispose();
	}

}

