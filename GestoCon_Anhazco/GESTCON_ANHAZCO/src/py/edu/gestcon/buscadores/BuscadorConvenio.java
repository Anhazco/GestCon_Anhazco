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

import py.edu.gestcon.dao.ConvenioDao;
import py.edu.gestcon.entidades.Convenio;
import py.edu.gestcon.interfaces.InterfaceConvenio;
import py.edu.gestcon.tablas.ModeloTablaConvenio;

public class BuscadorConvenio extends JDialog {
	private JTextField tfBuscador;
	private List<Convenio> convenios;
	private ConvenioDao dao;
	private ModeloTablaConvenio modeloTablaConvenio;
	private InterfaceConvenio interfaceConvenio;
	private JTable table;

	public void setInterface(InterfaceConvenio interfaceConvenio) {
		this.interfaceConvenio = interfaceConvenio;
	}

	public BuscadorConvenio() {
		setTitle("Buscador Convenio");
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

		modeloTablaConvenio = new ModeloTablaConvenio();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(table.getSelectedRow());
			}
		});
		table.setModel(modeloTablaConvenio);
		scrollPane.setViewportView(table);

		dao = new ConvenioDao();
		filtrarRegistros();
	}
	private void filtrarRegistros() {
		convenios = dao.recuperarPorFiltro(tfBuscador.getText());
		modeloTablaConvenio.setLista(convenios);
	}

	private void seleccionarRegistro(int index) {
		if(index<0) return;
		interfaceConvenio.seleccionarConvenio(convenios.get(index));
		dispose();
	}
}

