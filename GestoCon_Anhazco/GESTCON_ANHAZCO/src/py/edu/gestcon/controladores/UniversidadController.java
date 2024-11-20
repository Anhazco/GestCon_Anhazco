package py.edu.gestcon.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.gestcon.app.PantallaPrincipal;
import py.edu.gestcon.dao.UniversidadDao;
import py.edu.gestcon.entidades.Universidad;
import py.edu.gestcon.interfaces.InterfaceAcciones;
import py.edu.gestcon.tablas.ModeloTablaUniversidad;
import py.edu.gestcon.utilidades.EventosGenericos;
import py.edu.gestcon.vista.UniversidadVentana;

public class UniversidadController implements InterfaceAcciones{

	private UniversidadVentana ventana;
	private ModeloTablaUniversidad modeloTablaUniversidad;
	private Universidad universidad;
	private List<Universidad> universidades;
	private UniversidadDao dao;

	public UniversidadController(UniversidadVentana universidadVentana) {
		super();
		this.ventana = universidadVentana;
		this.ventana.setInterfaceAcciones(this);
		modeloTablaUniversidad = new ModeloTablaUniversidad();
		this.ventana.getTable().setModel(modeloTablaUniversidad);
		dao = new UniversidadDao();
		consultarUniversidad();
		estadoInicial();
		setUpevents();
	}

	public UniversidadController(PantallaPrincipal pantallaPrincipal) {
	}

	private void setUpevents() {
		ventana.getTable().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(ventana.getTable().getSelectedRow());
			}
		});
		ventana.getTfBuscador().addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) consultarUniversidad();
			}

		});

		ventana.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});

	}

	private void estadoInicial() {
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnModificar().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnSalir().setEnabled(true);
		ventana.getBtnNuevo().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnActualizar().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(false);
		ventana.getChEstado().setEnabled(false);
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), false);
		ventana.getTable().setEnabled(true);
	}

	private void limpiarCampos() {
		ventana.getTfDescripcion().setText("");
		ventana.getTfAbreviatura().setText("");
		ventana.getTfTelefono().setText("");
		ventana.getTfCelular().setText("");
		ventana.getTfEmail().setText("");
		ventana.getChEstado().setSelected(false);


	}

	private void consultarUniversidad() {
		universidades = dao.recuperarPorFiltro(this.ventana.getTfBuscador().getText());
		modeloTablaUniversidad.setLista(universidades);
		boolean estado = ventana.getChEstado().isSelected();
		universidades = dao.recuperarPorEstado(estado); 

		System.out.println("Número de universidades encontradas: " + universidades.size());

		modeloTablaUniversidad.setLista(universidades);
	}


	@Override
	public void nuevo() {
		ventana.getTfDescripcion().requestFocus();
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnActualizar().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(true);
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), true);
		ventana.getChEstado().setEnabled(true);
		ventana.getTable().setEnabled(false);
	}

	@Override
	public void modificar() {
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), true);
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnActualizar().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getChEstado().setEnabled(true);
	}

	@Override
	public void eliminar() {
		if(universidad==null){
			JOptionPane.showMessageDialog(null, "No encontrado");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, "¿Estas seguro que desea eliminar? " +universidad.getDescripcion(),
				"Atención", JOptionPane.YES_NO_OPTION);
		if(respuesta==JOptionPane.YES_OPTION){
			try {
				dao.eliminar(universidad);
				estadoInicial();
				consultarUniversidad();
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
		}
	}

	@Override
	public void salir() {
		ventana.dispose();

	}

	@Override
	public void cancelar() {
		estadoInicial();
	}

	@Override
	public void guardar() {
		if (!validarCampos()) return;
		universidad = new Universidad();
		cargarEntidad();
		try {
			dao.guardar(universidad);
			consultarUniversidad();
			limpiarCampos();
			estadoInicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void actualizar() {
		if (!validarCampos()) return;
		cargarEntidad();
		try {
			dao.actualizar(universidad);
			consultarUniversidad();
			limpiarCampos();
			estadoInicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	public void cargarEntidad() {
		universidad.setDescripcion(this.ventana.getTfDescripcion().getText());
		universidad.setAbreviatura(this.ventana.getTfAbreviatura().getText());
		universidad.setTelefono(this.ventana.getTfTelefono().getText());
		universidad.setCelular(this.ventana.getTfCelular().getText());
		universidad.setEmail(this.ventana.getTfEmail().getText());
		universidad.setEstado(ventana.getChEstado().isSelected());
	}

	private void seleccionarRegistro(int index) {
		if(index<0)return;
		universidad = universidades.get(index);
		ventana.getTfDescripcion().setText(universidad.getDescripcion());
		ventana.getTfAbreviatura().setText(universidad.getAbreviatura());
		ventana.getTfTelefono().setText(universidad.getTelefono());
		ventana.getTfCelular().setText(universidad.getCelular());
		ventana.getTfEmail().setText(universidad.getEmail());
		ventana.getChEstado().setSelected(universidad.isEstado());

		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnActualizar().setEnabled(false);
	}

	private boolean validarCampos(){
		if (ventana.getTfDescripcion().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo 'Descripción' es obligatorio");
			ventana.getTfDescripcion().requestFocus();
			return false;
		}
		if (ventana.getTfAbreviatura().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo 'Abreviatura' es obligatorio");
			ventana.getTfAbreviatura().requestFocus();
			return false;
		}
		if (ventana.getTfTelefono().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo 'Teléfono' es obligatorio");
			ventana.getTfTelefono().requestFocus();
			return false;
		}
		if (ventana.getTfCelular().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo 'Celular' es obligatorio");
			ventana.getTfCelular().requestFocus();
			return false;
		}
		if (ventana.getTfEmail().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo 'Email' es obligatorio");
			ventana.getTfEmail().requestFocus();
			return false;
		}
		return true;
	}

}

