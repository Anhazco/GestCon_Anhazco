package py.edu.gestcon.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import py.edu.gestcon.dao.UniversidadDao;
import py.edu.gestcon.entidades.Universidad;
import py.edu.gestcon.tablas.ModeloTablaListadoUniversidad;
import py.edu.gestcon.utilidades.ConexionReportes;
import py.edu.gestcon.utilidades.UtilidadesFecha;
import py.edu.gestcon.utilidades.UtilidadesNumeros;
import py.edu.gestcon.vista.ListadoUniversidadVentana;

public class ListadoUniversidadController {
	private List<Universidad> universidad;
	private ModeloTablaListadoUniversidad modeloTablaListadoUniversidad;
	private UniversidadDao universidadDAO;
	private ListadoUniversidadVentana ventana;
	private int dId, hId;
	private String dDescripcion, hDescripcion;

	public ListadoUniversidadController(ListadoUniversidadVentana listadoUniversidadVentana) {
		super();
		this.ventana = listadoUniversidadVentana;
		universidadDAO = new UniversidadDao();
		modeloTablaListadoUniversidad = new ModeloTablaListadoUniversidad();
		this.ventana.getTable().setModel(modeloTablaListadoUniversidad);
		setUpEvents();
		
	}
 
 
	private void setUpEvents() {
		ventana.getBtnFiltarSer().addActionListener(new ActionListener() {
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
		universidad = universidadDAO.filtroListadoUniversidad(dId, hId, dDescripcion,hDescripcion, this.ventana.getCbComIdDescrip().getSelectedItem().toString());
		modeloTablaListadoUniversidad.setLista(universidad);
		if (universidad.size()<=0) {
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
		if(!this.ventana.getTfDesdeDescripcion().getText().isEmpty()) dDescripcion = this.ventana.getTfHastaDescripcion().getText();
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
		parametros.put("order", this.ventana.getCbComIdDescrip().getSelectedItem().toString());
		
		//Creando el reporte
		ConexionReportes<Universidad> conexionReportes = new ConexionReportes<Universidad>();
		try {
			conexionReportes.generarReporte(universidad, parametros, "ListadoUniversidad");
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
}
