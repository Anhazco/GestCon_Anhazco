package py.edu.gestcon.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import py.edu.gestcon.dao.FirmantesDao;
import py.edu.gestcon.entidades.Firmantes;
import py.edu.gestcon.tablas.ModeloTablaFirmante;
import py.edu.gestcon.tablas.ModeloTablaListadoFirmante;
import py.edu.gestcon.utilidades.ConexionReportes;
import py.edu.gestcon.utilidades.UtilidadesFecha;
import py.edu.gestcon.utilidades.UtilidadesNumeros;
import py.edu.gestcon.vista.ListadoFirmanteVentana;

public class ListadoFirmanteController {

	private List<Firmantes> firmantes;
	private ModeloTablaListadoFirmante modeloTablaListadoFirmante;
	private FirmantesDao firmantesDao;
	private ListadoFirmanteVentana ventana;
	private int dId, hId;
	private String dNombre, hNombre;


	public ListadoFirmanteController(ListadoFirmanteVentana listadoFirmanteVentana) {
		super();
		this.ventana = listadoFirmanteVentana;
		firmantesDao = new FirmantesDao();
		modeloTablaListadoFirmante = new ModeloTablaListadoFirmante();
		this.ventana.getTable().setModel(modeloTablaListadoFirmante);
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
		firmantes = firmantesDao.filtroListadoFirmantes(dId, hId, dNombre,hNombre, this.ventana.getCbOrder().getSelectedItem().toString());
		modeloTablaListadoFirmante.setLista(firmantes);
		if (firmantes.size()<=0) {
			this.ventana.getBtnImprimir().setEnabled(false);
		}else {
			this.ventana.getBtnImprimir().setEnabled(true);
		}
	}




	private void cargarFiltros() {
		dId = 0;
		hId = 99999;
		dNombre = "A";
		hNombre = "ZZZZZZ";
		if(!this.ventana.getTfDesdeID().getText().isEmpty()) dId = UtilidadesNumeros.stringAInteger(this.ventana.getTfDesdeID().getText());
		if(!this.ventana.getTfHastaID().getText().isEmpty()) hId = UtilidadesNumeros.stringAInteger(this.ventana.getTfHastaID().getText());
		if(!this.ventana.getTfDesdeNombre().getText().isEmpty()) dNombre = this.ventana.getTfHastaNombre().getText();
		if(!this.ventana.getTfHastaNombre().getText().isEmpty()) hNombre = this.ventana.getTfHastaNombre().getText();
	}

	private void imprimir() {
		//pasando parametros para imprimir
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("desdeId", dId);
		parametros.put("hastaId", hId);
		parametros.put("desdeNombre", dNombre);
		parametros.put("hastaNombre", hNombre);
		parametros.put("fecha", UtilidadesFecha.fechaAString(new Date()));
		parametros.put("order", this.ventana.getCbOrder().getSelectedItem().toString());

		//Creando Reporte
		ConexionReportes<Firmantes> conexionReportes = new ConexionReportes<Firmantes>();
		try {
			conexionReportes.generarReporte(firmantes, parametros, "ListadoFirmantes");
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}






}




