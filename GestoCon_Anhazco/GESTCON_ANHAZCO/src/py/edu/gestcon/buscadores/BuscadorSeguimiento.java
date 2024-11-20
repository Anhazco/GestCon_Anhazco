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

import py.edu.gestcon.dao.SeguimientoDao;
import py.edu.gestcon.entidades.Seguimiento;
import py.edu.gestcon.interfaces.InterfaceSeguimento;
import py.edu.gestcon.tablas.ModeloTablaSeguimiento;

public class BuscadorSeguimiento extends JDialog {

	private JTextField tfBuscador;
	private List<Seguimiento> seguimientos;
	private SeguimientoDao dao;
	private ModeloTablaSeguimiento modeloTablaSeguimiento;
	private InterfaceSeguimento interfaceSeguimento;
	private JTable table;

	public void setInterface(InterfaceSeguimento interfaceSeguimento) {
		this.interfaceSeguimento = interfaceSeguimento;
	}


	/**
	 * Create the dialog.
	 */
	public BuscadorSeguimiento() {

		setTitle("Buscador Seguimiento");
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

		modeloTablaSeguimiento = new ModeloTablaSeguimiento();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(table.getSelectedRow());
			}
		});
		table.setModel(modeloTablaSeguimiento);
		scrollPane.setViewportView(table);

		dao = new SeguimientoDao();
		filtrarRegistros();
	}

	private void filtrarRegistros() {
		seguimientos = dao.recuperarPorFiltro(tfBuscador.getText());
		modeloTablaSeguimiento.setLista(seguimientos);
	}

	private void seleccionarRegistro(int index) {
		if(index<0) return;
		interfaceSeguimento.seleccionarSeguimiento(seguimientos.get(index));
		dispose();
	}


}

