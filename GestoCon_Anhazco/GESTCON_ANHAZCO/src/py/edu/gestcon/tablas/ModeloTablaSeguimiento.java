package py.edu.gestcon.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import py.edu.gestcon.entidades.Seguimiento;
import py.edu.gestcon.utilidades.UtilidadesFecha;

public class ModeloTablaSeguimiento extends AbstractTableModel {

	private String[] columnas = {"ID", "Nombre de convenio", "Fecha", "Actividad"};
	private List<Seguimiento> lista = new ArrayList<>();

	public void setLista(List<Seguimiento> lista) {
		this.lista = lista;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public String getColumnName(int i) {
		return columnas[i];
	}

	@Override
	public Object getValueAt(int r, int c) {
		Seguimiento seguimiento = lista.get(r);

		switch (c) {
		case 0:
			// Si getId() es un tipo primitivo int, no necesita comprobación de null
			return seguimiento.getId();
		case 1:
			// Verifica que getConvenio() y getNombre() no sean nulos
			return seguimiento.getConvenio() != null && seguimiento.getConvenio().getNombre() != null 
			? seguimiento.getConvenio().getNombre() 
					: "";
		case 2:
			// Convierte la fecha a cadena, maneja el valor nulo si es necesario
			return seguimiento.getFecha() != null 
			? UtilidadesFecha.fechaAString(seguimiento.getFecha()) 
					: "";
		case 3:
			// Verifica que getActividad() no sea nulo
			return seguimiento.getActividad() != null ? seguimiento.getActividad() : "";
		default:
			return null;
		}
	}

}
