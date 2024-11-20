package py.edu.gestcon.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import py.edu.gestcon.dao.Unidad_AcademicaDao;
import py.edu.gestcon.entidades.Unidad_academica;
import py.edu.gestcon.tablas.ModeloTablaListadoUnidadAcademica;
import py.edu.gestcon.utilidades.ConexionReportes;
import py.edu.gestcon.utilidades.UtilidadesFecha;
import py.edu.gestcon.utilidades.UtilidadesNumeros;
import py.edu.gestcon.vista.ListadoUnidadAcademicaVentana;

public class ListadoUnidadAcademicaController {
	private List<Unidad_academica> unidad_academicas;
	private ModeloTablaListadoUnidadAcademica modeloTablaListadoUnidadAcademica;
	private Unidad_AcademicaDao academicaDao;
	private ListadoUnidadAcademicaVentana ventana;
	private int dId, hId;
	private String dDescripcion, hDescripcion;


	public ListadoUnidadAcademicaController(ListadoUnidadAcademicaVentana listadoUnidadAcademicaVentana) {
		super();
		this.ventana = listadoUnidadAcademicaVentana;
		academicaDao = new Unidad_AcademicaDao();
		modeloTablaListadoUnidadAcademica = new ModeloTablaListadoUnidadAcademica();
		this.ventana.getTable().setModel(modeloTablaListadoUnidadAcademica);
		setUpEvents();
	}


	private void setUpEvents() {
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
		unidad_academicas = academicaDao.filtroListadoUnidad_academicas (dId, hId, dDescripcion,hDescripcion, this.ventana.getCbComIdDescrip().getSelectedItem().toString());
		modeloTablaListadoUnidadAcademica.setLista(unidad_academicas);
		if (unidad_academicas.size()<=0) {
			this.ventana.getBtnImprimir().setEnabled(false);
		}else {
			this.ventana.getBtnImprimir().setEnabled(true);
		}
	}

	private void imprimir() {
		//Pasando parametros para imprimir
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("desdeId", dId);
		parametros.put("hastaId", hId);
		parametros.put("desdeDescripcion", dDescripcion);
		parametros.put("hastaDescripcion", hDescripcion);
		parametros.put("fecha", UtilidadesFecha.fechaAString(new Date()));
		parametros.put("order", this.ventana.getCbComIdDescrip().getSelectedItem().toString());

		ConexionReportes<Unidad_academica> conexionReportes = new ConexionReportes<Unidad_academica>();
		try {
			conexionReportes.generarReporte(unidad_academicas, parametros, "ListadoUnidadAcademica");
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}


	private void cargarFiltros() {
		dId = 0;
		hId = 99999;
		dDescripcion = "A";
		hDescripcion = "ZZZZ";
		if(!this.ventana.getTfDesdeId().getText().isEmpty()) dId = UtilidadesNumeros.stringAInteger(this.ventana.getTfDesdeId().getText());
		if(!this.ventana.getTfHastaId().getText().isEmpty()) hId = UtilidadesNumeros.stringAInteger(this.ventana.getTfHastaId().getText());
		if(!this.ventana.getTfDesdeNombre().getText().isEmpty()) dDescripcion = this.ventana.getTfDesdeNombre().getText();
		if(!this.ventana.getTfHastaNombre().getText().isEmpty()) hDescripcion = this.ventana.getTfHastaNombre().getText();

	}	

}
