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
import py.edu.gestcon.dao.InstitucionDao;
import py.edu.gestcon.entidades.Firmantes;
import py.edu.gestcon.entidades.Institucion;
import py.edu.gestcon.interfaces.InterfaceAcciones;
import py.edu.gestcon.interfaces.InterfaceFirmante;
import py.edu.gestcon.tablas.ModeloTablaInstitucion;
import py.edu.gestcon.utilidades.EventosGenericos;
import py.edu.gestcon.vista.InstitucionVentana;

public class InstitucionController implements InterfaceAcciones, InterfaceFirmante {

	private InstitucionVentana ventana;
	private ModeloTablaInstitucion modeloTablaInstitucion;
	private Institucion institucion;
	private List<Institucion> instituciones;
	private InstitucionDao dao;
	private Firmantes firmantes;


	public InstitucionController(InstitucionVentana institucionVentana) {
		super();
		this.ventana = institucionVentana;
		this.ventana.setInterfaceAcciones(this);
		modeloTablaInstitucion = new ModeloTablaInstitucion();
		this.ventana.getTable().setModel(modeloTablaInstitucion);
		dao = new InstitucionDao();
		consultarInstitucion();
		estadoInicial();
		setUpEvents();
	}

	public InstitucionController(PantallaPrincipal pantallaPrincipal) {};


	private void setUpEvents() {
		ventana.getTable().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) seleccionarRegistro(ventana.getTable().getSelectedRow());
			}
		});
		ventana.getTfBuscador().addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) consultarInstitucion();
			}

		});
		ventana.getBtnFirmante().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorFirmante();
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
		ventana.getBtnFirmante().setEnabled(false);
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), false);
		ventana.getTable().setEnabled(true);
		limpiarCampos();
	}

	private void limpiarCampos() {
		ventana.getTfNombre().setText("");
		ventana.getTfAbreviatura().setText("");
		ventana.getTfRubro().setText("");
		ventana.getTfTipo().setText("");
		ventana.getTfTelefono().setText("");
		ventana.getTfCelular().setText("");
		ventana.getTfDireccion().setText("");
		ventana.getTfFirmante().setText("");
	}

	private void consultarInstitucion() {
		instituciones = dao.recuperarPorFiltro(this.ventana.getTfBuscador().getText());
		modeloTablaInstitucion.setLista(instituciones);
	}

	@Override
	public void seleccionarFirmante(Firmantes firmantes) {
		this.firmantes = firmantes;
		ventana.getTfFirmante().setText(this.firmantes.getNombre()+" "+firmantes.getApellido());
	}

	@Override
	public void nuevo() {
		ventana.getTfNombre().requestFocus();
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnActualizar().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnFirmante().setEnabled(true);
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), true);
		ventana.getTfFirmante().setEnabled(false);
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
	}

	@Override
	public void eliminar() {
		if(institucion==null){
			JOptionPane.showMessageDialog(null, "No encontrado");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro que desea eliminar? " +institucion.getNombre(),
				"Atención", JOptionPane.YES_NO_OPTION);
		if(respuesta==JOptionPane.YES_OPTION){
			try {
				dao.eliminar(institucion);
				estadoInicial();
				consultarInstitucion();
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
		institucion = new Institucion();
		cargarEntidad();
		try {
			dao.guardar(institucion);
			consultarInstitucion();
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
			dao.actualizar(institucion);
			consultarInstitucion();
			limpiarCampos();
			estadoInicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}

	}

	public void Salir() {
		ventana.dispose();

	}

	private void cargarEntidad() {
		institucion.setNombre(this.ventana.getTfNombre().getText());
		institucion.setAbreviatura(this.ventana.getTfAbreviatura().getText());
		institucion.setRubro(this.ventana.getTfRubro().getText());
		institucion.setTipo(this.ventana.getTfTipo().getText());
		institucion.setTelefono(this.ventana.getTfTelefono().getText());
		institucion.setCelular(this.ventana.getTfCelular().getText());
		institucion.setDireccion(this.ventana.getTfDireccion().getText());

		institucion.setFirmantes(firmantes);
	}

	private void seleccionarRegistro(int index) {
		if(index<0)return;
		institucion = instituciones.get(index);
		ventana.getTfNombre().setText(institucion.getNombre());
		ventana.getTfAbreviatura().setText(institucion.getAbreviatura());
		ventana.getTfRubro().setText(institucion.getRubro());
		ventana.getTfTipo().setText(institucion.getTipo());
		ventana.getTfTelefono().setText(institucion.getTelefono());
		ventana.getTfCelular().setText(institucion.getCelular());
		ventana.getTfDireccion().setText(institucion.getDireccion());

		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnActualizar().setEnabled(false);

		firmantes = institucion.getFirmantes();
		ventana.getTfFirmante().setText(firmantes.getNombre());
	}

	private void abrirBuscadorFirmante() {
		BuscadorFirmantes buscadorFirmantes = new BuscadorFirmantes();
		buscadorFirmantes.setInterface(this);
		buscadorFirmantes.setVisible(true);
	}

	private boolean validarCampos() {
	    if (ventana.getTfNombre().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Nombre es obligatorio.");
	        ventana.getTfNombre().requestFocus();
	        return false;
	    }

	    if (ventana.getTfAbreviatura().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Abreviatura es obligatorio.");
	        ventana.getTfAbreviatura().requestFocus();
	        return false;
	    }

	    if (ventana.getTfRubro().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Rubro es obligatorio.");
	        ventana.getTfRubro().requestFocus();
	        return false;
	    }

	    if (ventana.getTfTipo().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Tipo es obligatorio.");
	        ventana.getTfTipo().requestFocus();
	        return false;
	    }

	    if (ventana.getTfDireccion().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Dirección es obligatorio.");
	        ventana.getTfDireccion().requestFocus();
	        return false;
	    }

	    if (ventana.getTfTelefono().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Teléfono es obligatorio.");
	        ventana.getTfTelefono().requestFocus();
	        return false;
	    }

	    if (ventana.getTfCelular().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Celular es obligatorio.");
	        ventana.getTfCelular().requestFocus();
	        return false;
	    }

	    if (ventana.getTfFirmante().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Firmante es obligatorio.");
	        ventana.getTfFirmante().requestFocus();
	        return false;
	    }

	    return true;
	}

}
