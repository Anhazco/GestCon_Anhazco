package py.edu.gestcon.utilidades;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ConexionReportes <E> {
	public JDialog ventanaReporte = new JDialog(new JFrame(), "Visualizar Reporte", true);
	public void generarReporte(List<E> lista, Map<String, Object> parametros, String nombreReporte) throws JRException {
		ventanaReporte.setSize(1200,800);
		ventanaReporte.setLocationRelativeTo(null);
		ventanaReporte.setModal(true);
		InputStream stream = ConexionReportes.class.getResourceAsStream("/py/edu/gestcon/reportres/"+nombreReporte+".jrxml");
		JasperReport report = JasperCompileManager.compileReport(stream);
		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(lista));
		JasperViewer viewer = new JasperViewer(print, true);
		ventanaReporte.getContentPane().add(viewer.getContentPane());
	}
}
