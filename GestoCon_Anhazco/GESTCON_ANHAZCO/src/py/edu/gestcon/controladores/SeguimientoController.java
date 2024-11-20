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

import py.edu.gestcon.buscadores.BuscadorConvenio;
import py.edu.gestcon.dao.SeguimientoDao;
import py.edu.gestcon.entidades.Convenio;
import py.edu.gestcon.entidades.Seguimiento;
import py.edu.gestcon.interfaces.InterfaceAcciones;
import py.edu.gestcon.interfaces.InterfaceConvenio;
import py.edu.gestcon.tablas.ModeloTablaSeguimiento;
import py.edu.gestcon.utilidades.EventosGenericos;
import py.edu.gestcon.utilidades.UtilidadesFecha;
import py.edu.gestcon.vista.SeguimientoVentana;

public class SeguimientoController implements InterfaceAcciones, InterfaceConvenio {

	private SeguimientoVentana ventana;
	private ModeloTablaSeguimiento modeloTablaSeguimiento;
	private Seguimiento seguimiento;
	private List<Seguimiento> seguimientos;
	private SeguimientoDao dao;
	private Convenio convenio;

	public SeguimientoController(SeguimientoVentana seguimientoVentana) {
		super();
		this.ventana = seguimientoVentana;
		this.ventana.setInterfaceAcciones(this);
		modeloTablaSeguimiento = new ModeloTablaSeguimiento();
		this.ventana.getTable().setModel(modeloTablaSeguimiento);
		dao = new SeguimientoDao();
		consultarSeguimiento();
		estadoInicial();
		setUpEvents();
	}

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
				if (e.getKeyChar()==KeyEvent.VK_ENTER) consultarSeguimiento();
			}
		});
		ventana.getTfBuscador().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()==KeyEvent.VK_ENTER) consultarSeguimiento();
			}
		});
		ventana.getBtnConvenio().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorConvenio();
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
		ventana.getBtnConvenio().setEnabled(false);
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), false);
		ventana.getTfFecha().setEnabled(false);
		ventana.getTfFecha().setText(UtilidadesFecha.fechaAString(new  Date()));
		ventana.getTfActividad().setEnabled(false);
		ventana.getTable().setEnabled(true);
		limpiarCampos();

	}

	private void limpiarCampos() {
		ventana.getTfActividad().setText("");
		ventana.getTfFecha().setText("");
		ventana.getTfConvenio().setText("");
	}

	private void consultarSeguimiento() {
		seguimientos = dao.recuperarPorFiltro(this.ventana.getTfBuscador().getText());
		modeloTablaSeguimiento.setLista(seguimientos);
	}

	@Override
	public void seleccionarConvenio(Convenio convenio) {
		this.convenio = convenio;
		ventana.getTfConvenio().setText(this.convenio.getNombre()+" "+convenio.getCategoria());
	}

	@Override
	public void nuevo() {
		ventana.getTfActividad().requestFocus();
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnActualizar().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnConvenio().setEnabled(true);
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), true);
		ventana.getTfFecha().setEnabled(true);
		ventana.getTfFecha().setText(UtilidadesFecha.fechaAString(new  Date()));
		ventana.getTable().setEnabled(false);
		ventana.getTfActividad().setEnabled(true);
	}

	@Override
	public void modificar() {
		EventosGenericos.estadosJTextField(ventana.getjPanelFormulario(), true);
		ventana.getTfFecha().setEnabled(true);
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnActualizar().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnConvenio().setEnabled(true);
	}


	@Override
	public void eliminar() {
		if(seguimiento==null){
			JOptionPane.showMessageDialog(null, "No encontrado");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro que desea eliminar? " +seguimiento.getActividad(),
				"Atención", JOptionPane.YES_NO_OPTION);
		if(respuesta==JOptionPane.YES_OPTION){
			try {
				dao.eliminar(seguimiento);
				estadoInicial();
				consultarSeguimiento();
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
		seguimiento = new Seguimiento();
		cargarEntidad();
		try {
			dao.guardar(seguimiento);
			consultarSeguimiento();
			limpiarCampos();
			estadoInicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	private void cargarEntidad() {
		seguimiento.setActividad(this.ventana.getTfActividad().getText());
		seguimiento.setFecha(UtilidadesFecha.stringAFecha(ventana.getTfFecha().getText()));
		seguimiento.setConvenio(convenio);
	}

	@Override
	public void actualizar() {
		if (!validarCampos()) return;
		cargarEntidad();
		try {
			dao.actualizar(seguimiento);
			consultarSeguimiento();
			limpiarCampos();
			estadoInicial();
		} catch (Exception e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	private boolean validarCampos() {
		if(ventana.getTfActividad().getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Este campo es obligatorio.");
			return false;
		}
		
		if (convenio == null) {
			JOptionPane.showMessageDialog(null, "Es necesario seleccionar un convenio.");
			
		}
		if(UtilidadesFecha.stringAFecha(ventana.getTfFecha().getText())==null){
			JOptionPane.showMessageDialog(null, "Ingrese una fecha válida.");
			return false;
		}
		return true;	
	}

	public void abrirBuscadorConvenio() {
		BuscadorConvenio buscadorConvenio = new BuscadorConvenio();
		buscadorConvenio.setInterface(this);
		buscadorConvenio.setVisible(true);
	}

	private void seleccionarRegistro(int index) {
		if(index<0)return;
		seguimiento = seguimientos.get(index);
		ventana.getTfActividad().setText(seguimiento.getActividad());
		ventana.getTfFecha().setText(UtilidadesFecha.fechaAString(seguimiento.getFecha()));
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnActualizar().setEnabled(false);
		convenio= seguimiento.getConvenio();
		ventana.getTfConvenio().setText(convenio.getNombre());
	}

}



