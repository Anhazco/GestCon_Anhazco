package py.edu.gestcon.buscadores;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import py.edu.gestcon.dao.Unidad_AcademicaDao;
import py.edu.gestcon.entidades.Unidad_academica;
import py.edu.gestcon.interfaces.InterfaceUnidadAcademica;
import py.edu.gestcon.tablas.ModeloTablaUnidadAcademica;

public class BuscadorUnidadAcademica extends JDialog {
	private JTextField tfBuscador;
	private List<Unidad_academica> unidad_academica;
	private Unidad_AcademicaDao dao;
	private ModeloTablaUnidadAcademica modeloTablaUnidadAcademica;
	private InterfaceUnidadAcademica interfaceUnidadAcademica;
	private JTable table;

	public void setInterface(InterfaceUnidadAcademica interfaceUnidadAcademica) {
		this.interfaceUnidadAcademica = interfaceUnidadAcademica;
	}

	/**
	 * Create the dialog.
	 */


	public BuscadorUnidadAcademica() {
		setTitle("Buscador Unidad Academica");
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

		modeloTablaUnidadAcademica = new ModeloTablaUnidadAcademica();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(table.getSelectedRow());
			}
		});
		table.setModel(modeloTablaUnidadAcademica);
		scrollPane.setViewportView(table);

		dao = new Unidad_AcademicaDao();
		filtrarRegistros();
	}

	private void filtrarRegistros() {
		unidad_academica = dao.recuperarPorFiltro(tfBuscador.getText());
		modeloTablaUnidadAcademica.setLista(unidad_academica);
	}

	private void seleccionarRegistro(int index) {
		if(index<0) return;
		interfaceUnidadAcademica.seleccionarUnidadAcademica(unidad_academica.get(index));
		dispose();
	}

}
