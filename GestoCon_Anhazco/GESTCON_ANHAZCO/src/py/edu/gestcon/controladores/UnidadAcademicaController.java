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
import py.edu.gestcon.buscadores.BuscadorFirmantes;
import py.edu.gestcon.buscadores.BuscadorUniversidad;
import py.edu.gestcon.dao.Unidad_AcademicaDao;
import py.edu.gestcon.entidades.Firmantes;
import py.edu.gestcon.entidades.Unidad_academica;
import py.edu.gestcon.entidades.Universidad;
import py.edu.gestcon.interfaces.InterfaceAcciones;
import py.edu.gestcon.interfaces.InterfaceFirmante;
import py.edu.gestcon.interfaces.InterfaceUniversidad;
import py.edu.gestcon.tablas.ModeloTablaUnidadAcademica;
import py.edu.gestcon.utilidades.EventosGenericos;
import py.edu.gestcon.vista.UnidadAcademicaVentana;

public class UnidadAcademicaController implements InterfaceAcciones, InterfaceFirmante, InterfaceUniversidad{

	private UnidadAcademicaVentana ventana;
	private ModeloTablaUnidadAcademica modeloTablaUnidadAcademica;
	private Unidad_academica academica;
	private List<Unidad_academica> unidad_academica;
	private Unidad_AcademicaDao dao;
	private Firmantes firmante;
	private Universidad universidad;

	public UnidadAcademicaController(UnidadAcademicaVentana unidadAcademicaVentana) {
		super();
		this.ventana = unidadAcademicaVentana;
		this.ventana.setInterfaceAcciones(this);
		modeloTablaUnidadAcademica = new ModeloTablaUnidadAcademica();
		this.ventana.getTable().setModel(modeloTablaUnidadAcademica);
		dao = new Unidad_AcademicaDao();
		consultarUnidadAcademica();
		estadoInicial();
		serUpEvents();
	}


	public UnidadAcademicaController (PantallaPrincipal pantallaPrincipal) {

	}


	private void serUpEvents() {
		ventana.getTable().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(ventana.getTable().getSelectedRow());
			}
		});
		ventana.getTfBuscador().addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) consultarUnidadAcademica();
			}
		});
		ventana.getTfBuscador().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) consultarUnidadAcademica();
			}
		});
		ventana.getBtnFirmante().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorFirmante();
			}
		});
		ventana.getBtnUniversidad().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorUniversidad();
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
		ventana.getBtnFirmante().setEnabled(false);
		ventana.getBtnUniversidad().setEnabled(false);
		ventana.getChEstado().setEnabled(false);
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), false);
		ventana.getChEstado().setEnabled(false);

		ventana.getTable().setEnabled(true);
		limpiarCampos();

	}

	private void limpiarCampos() {
		ventana.getTfNombre().setText("");
		ventana.getTfAbreviatura().setText("");
		ventana.getTfTelefono().setText("");
		ventana.getTfCelular().setText("");
		ventana.getTfEmail().setText("");
		ventana.getTfDireccion().setText("");
		ventana.getTfFirmante().setText("");
		ventana.getTfUniversidad().setText("");
		ventana.getChEstado().setSelected(false);
	}


	private void consultarUnidadAcademica() {
		unidad_academica = dao.recuperarPorFiltro(this.ventana.getTfBuscador().getText());
		modeloTablaUnidadAcademica.setLista(unidad_academica);
	}

	@Override
	public void seleccionarUniversidad(Universidad universidad) {
		this.universidad = universidad;
		ventana.getTfUniversidad().setText(this.universidad.getDescripcion()+" "+universidad.getAbreviatura());

	}

	@Override
	public void seleccionarFirmante(Firmantes fir) {
		this.firmante = fir;
		ventana.getTfFirmante().setText(this.firmante.getNombre()+" "+firmante.getApellido());
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
		ventana.getBtnFirmante().setEnabled(true);
		ventana.getBtnUniversidad().setEnabled(true);
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), true);
		ventana.getChEstado().setSelected(true);
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
		ventana.getBtnFirmante().setEnabled(true);
		ventana.getBtnUniversidad().setEnabled(true);
		ventana.getChEstado().setEnabled(true);
		ventana.getChEstado().setEnabled(true);
	}

	@Override
	public void eliminar() {
		if(academica==null){
			JOptionPane.showMessageDialog(null, "No encontrado");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro que desea eliminar? " +academica.getNombre(),
				"Atención", JOptionPane.YES_NO_OPTION);
		if(respuesta==JOptionPane.YES_OPTION){
			try {
				dao.eliminar(academica);
				estadoInicial();
				consultarUnidadAcademica();
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
		academica = new Unidad_academica();
		cargarEntidad();
		try {
			dao.guardar(academica);
			consultarUnidadAcademica();
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
			dao.actualizar(academica);
			consultarUnidadAcademica();
			limpiarCampos();
			estadoInicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	private void cargarEntidad() {
		academica.setNombre(this.ventana.getTfNombre().getText());
		academica.setAbreviatura(this.ventana.getTfAbreviatura().getText());
		academica.setTelefono(this.ventana.getTfTelefono().getText());
		academica.setCelular(this.ventana.getTfCelular().getText());
		academica.setEmail(this.ventana.getTfEmail().getText());
		academica.setDireccion(this.ventana.getTfDireccion().getText());
		academica.setEstado(ventana.getChEstado().isSelected());
		academica.setEstado(ventana.getChEstado().isSelected());

		academica.setFirmantes(firmante);
		academica.setUniversidad(universidad);

	}

	private void seleccionarRegistro(int index) {
		if(index<0)return;
		academica = unidad_academica.get(index);
		ventana.getTfNombre().setText(academica.getNombre());
		ventana.getTfAbreviatura().setText(academica.getAbreviatura());
		ventana.getTfTelefono().setText(academica.getTelefono());
		ventana.getTfCelular().setText(academica.getCelular());
		ventana.getTfEmail().setText(academica.getEmail());
		ventana.getTfDireccion().setText(academica.getDireccion());
		ventana.getChEstado().setSelected(academica.isEstado());

		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnActualizar().setEnabled(false);

		firmante= academica.getFirmantes();
		ventana.getTfFirmante().setText(firmante.getNombre());
		universidad= academica.getUniversidad();
		ventana.getTfUniversidad().setText(universidad.getDescripcion());	
	}

	private void abrirBuscadorFirmante() {
		BuscadorFirmantes buscadorFirmantes = new BuscadorFirmantes();
		buscadorFirmantes.setInterface(this);
		buscadorFirmantes.setVisible(true);
	}

	private void abrirBuscadorUniversidad() {
		BuscadorUniversidad buscadorUniversidad = new BuscadorUniversidad();
		buscadorUniversidad.setInterface(this);
		buscadorUniversidad.setVisible(true);
	}

	public boolean validarCampos() {
	    if (ventana.getTfNombre().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo 'Nombre' es obligatorio");
	        ventana.getTfNombre().requestFocus();
	        return false;
	    }
	    if (ventana.getTfAbreviatura().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo 'Abreviatura' es obligatorio");
	        ventana.getTfAbreviatura().requestFocus();
	        return false;
	    }
	    if (ventana.getTfDireccion().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo 'Dirección' es obligatorio");
	        ventana.getTfDireccion().requestFocus();
	        return false;
	    }
	    if (ventana.getTfEmail().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo 'Email' es obligatorio");
	        ventana.getTfEmail().requestFocus();
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
	    if (ventana.getTfFirmante().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo 'Firmante' es obligatorio");
	        ventana.getTfFirmante().requestFocus();
	        return false;
	    }
	    if (ventana.getTfUniversidad().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo 'Universidad' es obligatorio");
	        ventana.getTfUniversidad().requestFocus();
	        return false;
	    }
	    return true;
	}

}
