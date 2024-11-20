package py.edu.gestcon.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import py.edu.gestcon.dao.ConvenioDao;
import py.edu.gestcon.entidades.Convenio;
import py.edu.gestcon.tablas.ModeloTablaInformeConvenio;
import py.edu.gestcon.utilidades.ConexionReportes;
import py.edu.gestcon.utilidades.UtilidadesFecha;
import py.edu.gestcon.utilidades.UtilidadesNumeros;
import py.edu.gestcon.vista.InformeConvenioVentana;

public class InformeConvenioController {
	private List<Convenio> convenios;
	private ModeloTablaInformeConvenio modeloTablaInformeConvenio;
	private ConvenioDao convenioDao;
	private InformeConvenioVentana ventana;
	private int dId, hId;
	private String dNombre, hNombre;

	public InformeConvenioController(InformeConvenioVentana informeConvenioVentana) {
		super();
		this.ventana = informeConvenioVentana;
		convenioDao = new ConvenioDao();
		modeloTablaInformeConvenio = new ModeloTablaInformeConvenio();
		this.ventana.getTable().setModel(modeloTablaInformeConvenio);
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
		convenios = convenioDao.filtroListadoConvenio(dId, hId, dNombre,hNombre, this.ventana.getCbOrder().toString().toString());
		modeloTablaInformeConvenio.setLista(convenios);
		if (convenios.size()<=0) {
			this.ventana.getBtnImprimir().setEnabled(false);
		}else {
			this.ventana.getBtnImprimir().setEnabled(true);
		}

	}


	private void cargarFiltros() {
		dId = 0;
		hId = 99999;
		dNombre = "A";
		hNombre = "ZZZZ";
		if(!this.ventana.getTfDesdeId().getText().isEmpty()) dId = UtilidadesNumeros.stringAInteger(this.ventana.getTfDesdeId().getText());
		if(!this.ventana.getTfHastaId().getText().isEmpty()) hId = UtilidadesNumeros.stringAInteger(this.ventana.getTfHastaId().getText());
		if(!this.ventana.getTfDesdeNombre().getText().isEmpty()) dNombre = this.ventana.getTfHastaNombre().getText();
		if(!this.ventana.getTfHastaNombre().getText().isEmpty()) hNombre = this.ventana.getTfHastaNombre().getText();
	}

	private void imprimir() {
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("desdeId", dId);
		parametros.put("hastaId", hId);
		parametros.put("desdeNombre", dNombre);
		parametros.put("hastaNombre", hNombre);
		parametros.put("fecha", UtilidadesFecha.fechaAString(new Date()));

		ConexionReportes<Convenio> conexionReportes = new ConexionReportes<Convenio>();
		try {
			if (ventana.getCbOrder().getSelectedIndex()==0) {	
				conexionReportes.generarReporte(convenios, parametros, "InformeConvenioSimple");
			}else {
				conexionReportes.generarReporte(convenios, parametros, "InformeConvenioDetallado");
			}
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}


}






