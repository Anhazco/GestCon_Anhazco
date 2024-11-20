package py.edu.gestcon.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.gestcon.entidades.Seguimiento;
import py.edu.gestcon.utilidades.UtilidadesFecha;

public class ModeloTablaInformeSeguimiento extends AbstractTableModel {
	
	private String clumnas[] = {"CÓDIGO", "FECHA", "ACTIVIDAD", "CATEGORIA"};
	private List<Seguimiento> lista = new ArrayList<Seguimiento>();

	public void setLista(List<Seguimiento> lista) {
		this.lista=lista;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return clumnas.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	public String getColumnName(int i) {
		return clumnas[i];
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0:
			return lista.get(r).getId();
		case 1:
			return UtilidadesFecha.fechaAString(lista.get(r).getFecha());
		case 2:
			return lista.get(r).getActividad();
		case 3:
			return lista.get(r).getConvenio().getCategoria();
		}
		return null;
	}

}
