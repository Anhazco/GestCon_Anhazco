package py.edu.gestcon.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import py.edu.gestcon.app.PantallaPrincipal;
import py.edu.gestcon.dao.FirmantesDao;
import py.edu.gestcon.entidades.Firmantes;
import py.edu.gestcon.interfaces.InterfaceAcciones;
import py.edu.gestcon.tablas.ModeloTablaFirmante;
import py.edu.gestcon.utilidades.EventosGenericos;
import py.edu.gestcon.utilidades.UtilidadesFecha;
import py.edu.gestcon.vista.FirmanteVentana;

public class FirmanteController implements InterfaceAcciones{

	private FirmanteVentana ventana;
	private ModeloTablaFirmante modeloTablaFirmante;
	private Firmantes firmante;
	private List<Firmantes> firmantes;
	private FirmantesDao dao;

	public FirmanteController(FirmanteVentana firmantesVentana) {
		super();
		this.ventana = firmantesVentana;
		this.ventana.setInterfaceAcciones(this);
		modeloTablaFirmante = new ModeloTablaFirmante();
		this.ventana.getTable().setModel(modeloTablaFirmante);
		dao = new FirmantesDao();
		consultarFirmante();
		estadoinicial();
		setUpevents();
	}

	public FirmanteController(PantallaPrincipal pantallaPrincipal) {}


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
				if (e.getKeyChar()==KeyEvent.VK_ENTER) consultarFirmante();
			}
		});
		ventana.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
	}


	private void estadoinicial() {
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnModificar().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnSalir().setEnabled(true);
		ventana.getBtnNuevo().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(false);
		ventana.getBtnActualizar().setEnabled(false);
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), false);
		ventana.getTfFdeResolucion().setEnabled(false);
		ventana.getTable().setEnabled(true);
		firmante = new Firmantes();
		limpiarCampos();
	}

	private void limpiarCampos() {
		ventana.getTfNombre().setText("");
		ventana.getTfApellido().setText("");
		ventana.getTfCedula().setText("");
		ventana.getTfCargo().setText("");
		ventana.getTfRdeRenombramiento().setText("");
		ventana.getTfDireccion().setText("");
		ventana.getTfBarrio().setText("");
		ventana.getTfCiudad().setText("");
	}



	private void consultarFirmante() {
		firmantes = dao.recuperarPorFiltro(this.ventana.getTfBuscador().getText());
		modeloTablaFirmante.setLista(firmantes);
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
		ventana.getTfFdeResolucion().setEnabled(false);
		ventana.getTfFdeResolucion().setText(UtilidadesFecha.fechaAString(new Date()));
		ventana.getTable().setEnabled(false);
	}

	@Override
	public void modificar() {
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(),true);
		ventana.getTfFdeResolucion().setEnabled(true);
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnActualizar().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
	}

	@Override
	public void eliminar() {
		if (firmante==null) {
			JOptionPane.showMessageDialog(null, "No encontrado.");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro que desea eliminar? " +firmante.getNombre(),
				"Atención", JOptionPane.YES_NO_OPTION);
		if(respuesta==JOptionPane.YES_OPTION){
			try {
				dao.eliminar(firmante);
				dao.commit();
				estadoinicial();
				consultarFirmante();
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
		estadoinicial();
	}

	@Override
	public void guardar() {
		if (!validarCampos()) return;
		cargarEntidad();
		try {
			dao.iniciarTransaccion();
			dao.guardar(firmante);
			dao.commit();
			consultarFirmante();
			limpiarCampos();
			estadoinicial();
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
			dao.actualizar(firmante);
			dao.commit();
			consultarFirmante();
			limpiarCampos();
			estadoinicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	private boolean validarCampos() {
	    if (ventana.getTfNombre().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Nombre es obligatorio.");
	        ventana.getTfNombre().requestFocus();
	        return false;
	    }

	    if (ventana.getTfApellido().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Apellido es obligatorio.");
	        ventana.getTfApellido().requestFocus();
	        return false;
	    }

	    if (ventana.getTfCedula().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Cédula es obligatorio.");
	        ventana.getTfCedula().requestFocus();
	        return false;
	    }

	    if (ventana.getTfCargo().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Cargo es obligatorio.");
	        ventana.getTfCargo().requestFocus();
	        return false;
	    }

	    if (ventana.getTfFdeResolucion().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Fecha de Resolución es obligatorio.");
	        ventana.getTfFdeResolucion().requestFocus();
	        return false;
	    }

	    return true;
	}


	private void cargarEntidad() {
		firmante.setNombre(this.ventana.getTfNombre().getText());
		firmante.setApellido(this.ventana.getTfApellido().getText());
		firmante.setDocumento(this.ventana.getTfCedula().getText());
		firmante.setCargo(this.ventana.getTfCargo().getText());
		firmante.setResolucion_renombramiento(this.ventana.getTfRdeRenombramiento().getText());
		firmante.setDireccion(this.ventana.getTfDireccion().getText());
		firmante.setBarrio(this.ventana.getTfBarrio().getText());
		firmante.setCiudad(this.ventana.getTfCiudad().getText());
		firmante.setFecha_resolucion(UtilidadesFecha.stringAFecha(this.ventana.getTfFdeResolucion().getText()));
	}

	private void seleccionarRegistro(int index) {
		if(index<0)return;
		firmante = firmantes.get(index);
		ventana.getTfNombre().setText(firmante.getNombre());
		ventana.getTfApellido().setText(firmante.getApellido());
		ventana.getTfCedula().setText(firmante.getDocumento());
		ventana.getTfCargo().setText(firmante.getCargo());
		ventana.getTfRdeRenombramiento().setText(firmante.getResolucion_renombramiento());
		ventana.getTfFdeResolucion().setText(UtilidadesFecha.fechaAString(firmante.getFecha_resolucion()));
		ventana.getTfDireccion().setText(firmante.getDireccion());
		ventana.getTfBarrio().setText(firmante.getBarrio());
		ventana.getTfCiudad().setText(firmante.getCiudad());

		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnActualizar().setEnabled(true);
		ventana .getBtnCancelar().setEnabled(true);
	}
}
