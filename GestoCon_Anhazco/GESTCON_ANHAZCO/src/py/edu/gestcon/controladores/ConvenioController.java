package py.edu.gestcon.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import py.edu.gestcon.app.PantallaPrincipal;
import py.edu.gestcon.buscadores.BuscadorInstitucion;
import py.edu.gestcon.buscadores.BuscadorUnidadAcademica;
import py.edu.gestcon.dao.ConvenioDao;
import py.edu.gestcon.entidades.Convenio;
import py.edu.gestcon.entidades.Institucion;
import py.edu.gestcon.entidades.Unidad_academica;
import py.edu.gestcon.interfaces.InterfaceAcciones;
import py.edu.gestcon.interfaces.InterfaceInstitucion;
import py.edu.gestcon.interfaces.InterfaceUnidadAcademica;
import py.edu.gestcon.tablas.ModeloTablaConvenio;
import py.edu.gestcon.utilidades.EventosGenericos;
import py.edu.gestcon.utilidades.UtilidadesFecha;
import py.edu.gestcon.vista.ConvenioVentana;

public class ConvenioController implements InterfaceAcciones, InterfaceUnidadAcademica, InterfaceInstitucion {

	private ConvenioVentana ventana;
	private ModeloTablaConvenio modeloTablaConvenio;
	private Convenio convenio;
	private List<Convenio> convenios;
	private ConvenioDao dao;
	private Unidad_academica unidada_academica;
	private Institucion institucion;

	public ConvenioController(ConvenioVentana convenioVentana) {
		super();
		this.ventana = convenioVentana;
		this.ventana.setInterfaceAcciones(this);
		modeloTablaConvenio = new ModeloTablaConvenio();
		this.ventana.getTable().setModel(modeloTablaConvenio);
		dao = new ConvenioDao();
		consultarConvenio();
		estadoInicial();
		serUpEvents();
	}

	public ConvenioController(PantallaPrincipal pantallaPrincipal) {
	}

	private void serUpEvents() {
		ventana.getTable().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					seleccionarRegistro(ventana.getTable().getSelectedRow());
			}
		});
		ventana.getTfBuscador().addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					consultarConvenio();
			}
		});

		ventana.getBtnUnidadAcademica().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorUnidadAcademica();
			}
		});

		ventana.getBtnInstitucion().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorInstitucion();
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
		ventana.getBtnCancelar().setEnabled(false);
		ventana.getBtnActualizar().setEnabled(false);
		ventana.getChEstado().setEnabled(false);
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), false);
		ventana.getTfInicio().setEnabled(false);
		ventana.getTfCaducidad().setEnabled(false);
		ventana.getBtnUnidadAcademica().setEnabled(false);
		ventana.getBtnInstitucion().setEnabled(false);

		ventana.getTable().setEnabled(true);
		limpiarCampos();
	}

	private void consultarConvenio() {
		convenios = dao.recuperarPorFiltro(this.ventana.getTfBuscador().getText());
		modeloTablaConvenio.setLista(convenios);
	}

	@Override
	public void seleccionarInstitucion(Institucion institucion) {
		this.institucion = institucion;
		ventana.getTfInstitucion().setText(this.institucion.getNombre() + "" + institucion.getAbreviatura());
	}

	@Override
	public void seleccionarUnidadAcademica(Unidad_academica unidad_academica) {
		this.unidada_academica = unidad_academica;
		ventana.getTfUnidadAcademica()
		.setText(this.unidada_academica.getNombre() + " " + unidad_academica.getAbreviatura());
	}

	@Override
	public void nuevo() {
		ventana.getTfNombre().requestFocus();
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnActualizar().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(true);
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), true);
		ventana.getTfInicio().setEnabled(true);
		ventana.getTfInicio().setText(UtilidadesFecha.fechaAString(new Date()));
		ventana.getTfCaducidad().setEnabled(true);
		ventana.getTfCaducidad().setText(UtilidadesFecha.fechaAString(new Date()));
		ventana.getChEstado().setEnabled(true);
		ventana.getBtnUnidadAcademica().setEnabled(true);
		ventana.getBtnInstitucion().setEnabled(true);
		ventana.getTable().setEnabled(false);
	}

	@Override
	public void modificar() {
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), true);
		ventana.getTfInicio().setEnabled(true);
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnActualizar().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnInstitucion().setEnabled(true);
		ventana.getBtnUnidadAcademica().setEnabled(true);
		ventana.getChEstado().setEnabled(true);
	}

	@Override
	public void eliminar() {
		if (convenio == null) {
			JOptionPane.showMessageDialog(null, "No encontrado");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro que desea eliminar? " + convenio.getNombre(),
				"Atención", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(convenio);
				estadoInicial();
				consultarConvenio();
			} catch (Exception e) {
				dao.rollback();
				e.printStackTrace();
			}
		}

	}

	@Override
	public void salir() {
		System.out.println("Entro");
		ventana.dispose();
	}

	@Override
	public void cancelar() {
		estadoInicial();
	}

	@Override
	public void guardar() {
		if (!validarCampos())
			return;
		convenio = new Convenio();
		cargarEntidad();
		try {
			dao.guardar(convenio);
			consultarConvenio();
			;
			limpiarCampos();
			estadoInicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void actualizar() {
		if (!validarCampos())
			return;
		cargarEntidad();
		try {
			dao.actualizar(convenio);
			consultarConvenio();
			limpiarCampos();
			estadoInicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	private void limpiarCampos() {
		ventana.getTfNombre().setText("");
		ventana.getTfCategoria().setText("");
		ventana.getTfCategoria().setText("");
		ventana.getTfCaducidad().setText("");
		ventana.getTfDuracion().setText("");
		ventana.getTfObjetivos().setText("");
		ventana.getTfUnidadAcademica().setText("");
		ventana.getTfInstitucion().setText("");
		ventana.getChEstado().setSelected(false);
	}

	private void cargarEntidad() {
		convenio.setNombre(this.ventana.getTfNombre().getText());
		convenio.setCategoria(this.ventana.getTfCategoria().getText());
		convenio.setFecha_inicio(UtilidadesFecha.stringAFecha(ventana.getTfInicio().getText()));
		convenio.setFecha_caducidad(UtilidadesFecha.stringAFecha(ventana.getTfCaducidad().getText()));
		convenio.setDuracion(this.ventana.getTfDuracion().getText());
		convenio.setObjetivos(this.ventana.getTfObjetivos().getText());
		convenio.setEstado(ventana.getChEstado().isSelected());

		convenio.setUnidad_academica(unidada_academica);
		convenio.setInstitucion(institucion);
	}

	private boolean validarCampos() {
		// Validación de Nombre
		if (ventana.getTfNombre().getText().trim().isEmpty()) {
			mostrarMensaje("El campo 'Nombre' es obligatorio.", ventana.getTfNombre());
			return false;
		}

		// Validación de Categoría
		if (ventana.getTfCategoria().getText().trim().isEmpty()) {
			mostrarMensaje("El campo 'Categoría' es obligatorio.", ventana.getTfCategoria());
			return false;
		}

		// Validación de Fecha de Inicio
		if (!esFechaValida(ventana.getTfInicio().getText())) {
			mostrarMensaje("Ingrese una 'Fecha de Inicio' válida en formato dd/MM/yyyy.", ventana.getTfInicio());
			return false;
		}

		// Validación de Fecha de Caducidad
		if (!esFechaValida(ventana.getTfCaducidad().getText())) {
			mostrarMensaje("Ingrese una 'Fecha de Caducidad' válida en formato dd/MM/yyyy.", ventana.getTfCaducidad());
			return false;
		}

		// Validación de Duración
		if (ventana.getTfDuracion().getText().trim().isEmpty()) {
			mostrarMensaje("El campo 'Duración' es obligatorio.", ventana.getTfDuracion());
			return false;
		}

		// Validación de Objetivos
		if (ventana.getTfObjetivos().getText().trim().isEmpty()) {
			mostrarMensaje("El campo 'Objetivos' es obligatorio.", ventana.getTfObjetivos());
			return false;
		}

		// Validación de Unidad Académica
		if (ventana.getTfUnidadAcademica().getText().trim().isEmpty()) {
			mostrarMensaje("El campo 'Unidad Académica' es obligatorio.", ventana.getTfUnidadAcademica());
			return false;
		}

		// Validación de Institución
		if (ventana.getTfInstitucion().getText().trim().isEmpty()) {
			mostrarMensaje("El campo 'Institución' es obligatorio.", ventana.getTfInstitucion());
			return false;
		}
		return true; // Todas las validaciones han pasado
	}

	private void mostrarMensaje(String mensaje, JComponent componente) {
		JOptionPane.showMessageDialog(null, mensaje);
		componente.requestFocus();
	}

	private boolean esFechaValida(String fechaTexto) {
		return UtilidadesFecha.stringAFecha(fechaTexto) != null;
	}

	private void seleccionarRegistro(int index) {
		if (index < 0)
			return;
		convenio = convenios.get(index);
		ventana.getTfNombre().setText(convenio.getNombre());
		ventana.getTfCategoria().setText(convenio.getCategoria());
		ventana.getTfInicio().setText(UtilidadesFecha.fechaAString(convenio.getFecha_inicio()));
		ventana.getTfCaducidad().setText(UtilidadesFecha.fechaAString(convenio.getFecha_caducidad()));
		ventana.getTfDuracion().setText(convenio.getDuracion());
		ventana.getTfObjetivos().setText(convenio.getObjetivos());
		ventana.getChEstado().setSelected(convenio.isEstado());
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnActualizar().setEnabled(false);

		unidada_academica = convenio.getUnidad_academica();
		ventana.getTfUnidadAcademica().setText(unidada_academica.getNombre());
		institucion = convenio.getInstitucion();
		ventana.getTfInstitucion().setText(institucion.getNombre());
	}

	private void abrirBuscadorUnidadAcademica() {
		BuscadorUnidadAcademica buscadorUnidadAcademica = new BuscadorUnidadAcademica();
		buscadorUnidadAcademica.setInterface(this);
		buscadorUnidadAcademica.setVisible(true);
	}

	private void abrirBuscadorInstitucion() {
		BuscadorInstitucion buscadorInstitucion = new BuscadorInstitucion();
		buscadorInstitucion.setInterface(this);
		buscadorInstitucion.setVisible(true);
	}
}
