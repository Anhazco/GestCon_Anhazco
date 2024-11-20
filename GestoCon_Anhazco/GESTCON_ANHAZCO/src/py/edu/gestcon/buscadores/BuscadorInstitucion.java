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

import py.edu.gestcon.dao.InstitucionDao;
import py.edu.gestcon.entidades.Institucion;
import py.edu.gestcon.interfaces.InterfaceInstitucion;
import py.edu.gestcon.tablas.ModeloTablaInstitucion;

public class BuscadorInstitucion extends JDialog {

	private JTextField tfBuscador;
	private List<Institucion> instituciones;
	private InstitucionDao dao;
	private ModeloTablaInstitucion modeloTablaInstitucion;
	private InterfaceInstitucion interfaceInstitucion;
	private JTable table;

	public void setInterface(InterfaceInstitucion interfaceInstitucion) {
		this.interfaceInstitucion = interfaceInstitucion;
	}
	



	/**
	 * Create the dialog.
	 */
	public BuscadorInstitucion() {

		setTitle("Buscador Institución");
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

		modeloTablaInstitucion = new ModeloTablaInstitucion();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(table.getSelectedRow());
			}
		});
		table.setModel(modeloTablaInstitucion);
		scrollPane.setViewportView(table);

		dao = new InstitucionDao();
		filtrarRegistros();
	}

	private void filtrarRegistros() {
		instituciones = dao.recuperarPorFiltro(tfBuscador.getText());
		modeloTablaInstitucion.setLista(instituciones);
	}

	private void seleccionarRegistro(int index) {
		if(index<0) return;
		interfaceInstitucion.seleccionarInstitucion(instituciones.get(index));
		dispose();
	}

}

