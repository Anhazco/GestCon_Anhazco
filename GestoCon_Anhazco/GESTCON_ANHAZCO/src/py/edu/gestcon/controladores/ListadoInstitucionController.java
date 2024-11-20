package py.edu.gestcon.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import py.edu.gestcon.dao.InstitucionDao;
import py.edu.gestcon.entidades.Institucion;
import py.edu.gestcon.tablas.ModeloTablaListadoInstitucion;
import py.edu.gestcon.utilidades.ConexionReportes;
import py.edu.gestcon.utilidades.UtilidadesFecha;
import py.edu.gestcon.utilidades.UtilidadesNumeros;
import py.edu.gestcon.vista.ListadoInstitucionVentana;

public class ListadoInstitucionController {
	private List<Institucion> Instituciones;
	private ModeloTablaListadoInstitucion  modeloTablaListadoInstitucion;
	private InstitucionDao institucionDao;
	private ListadoInstitucionVentana ventana;
	private int dId, hId;
	private String dDescripcion, hDescripcion;


	public ListadoInstitucionController(ListadoInstitucionVentana listadoInstitucionVentana) {
		super();
		this.ventana = listadoInstitucionVentana;
		institucionDao = new InstitucionDao();
		modeloTablaListadoInstitucion = new ModeloTablaListadoInstitucion();
		this.ventana.getTable().setModel(modeloTablaListadoInstitucion);
		seUpEvents();
	}

	private void seUpEvents() {
		ventana.getBtnFiltrar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrar();
			}
		});
		ventana.getBtnImprimir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				imprimir();
			}
		});
	}

	private void filtrar() {
		cargarFiltros();
		Instituciones = institucionDao.filtroListadoInstitucion(dId, hId, dDescripcion,hDescripcion, this.ventana.getCbOrder().getSelectedItem().toString());
		modeloTablaListadoInstitucion.setLista(Instituciones);
		if (Instituciones.size()<=0) {
			this.ventana.getBtnImprimir().setEnabled(false);
		}else {
			this.ventana.getBtnImprimir().setEnabled(true);
		}

	}

	private void cargarFiltros() {
		dId = 0;
		hId = 99999;
		dDescripcion = "A";
		hDescripcion = "ZZZZ";
		if(!this.ventana.getTfDesdeId().getText().isEmpty()) dId = UtilidadesNumeros.stringAInteger(this.ventana.getTfDesdeId().getText());
		if(!this.ventana.getTfHastaId().getText().isEmpty()) hId = UtilidadesNumeros.stringAInteger(this.ventana.getTfHastaId().getText());
		if (this.ventana.getTfDesdeDescripcion() != null && !this.ventana.getTfDesdeDescripcion().getText().isEmpty()) {
			dDescripcion = this.ventana.getTfHastaDescripcion().getText(); 
		}
		if(!this.ventana.getTfHastaDescripcion().getText().isEmpty()) hDescripcion = this.ventana.getTfHastaDescripcion().getText();
	}


	private void imprimir() {
		//Pasando parametros para imprimir
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("desdeId", dId);
		parametros.put("hastaId", hId);
		parametros.put("desdeDescripcion", dDescripcion);
		parametros.put("hastaDescripcion", hDescripcion);
		parametros.put("fecha", UtilidadesFecha.fechaAString(new Date()));
		parametros.put("order", this.ventana.getCbOrder().getSelectedItem().toString());

		ConexionReportes<Institucion> conexionReportes = new ConexionReportes<Institucion>();
		try {
			conexionReportes.generarReporte(Instituciones, parametros, "ListadoInstitucion");
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
























