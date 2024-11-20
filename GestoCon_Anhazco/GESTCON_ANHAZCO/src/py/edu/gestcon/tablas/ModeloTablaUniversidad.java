package py.edu.gestcon.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.gestcon.entidades.Universidad;

public class ModeloTablaUniversidad extends AbstractTableModel {

	private String[] columnas = {"Descripción", "Abreviatura", "Teléfono", "Estado "};
	private List<Universidad> lista = new ArrayList<Universidad>();

	public void setLista(List<Universidad> lista) {
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
	public String getColumnName(int column) {
		return columnas[column];
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0:
			return lista.get(r).getDescripcion();
		case 1:
			return lista.get(r).getAbreviatura();
		case 2:
			return lista.get(r).getTelefono();

		case 3:
			return lista.get(r).isEstado() ? "Activo" : "Inactivo";
		default:
			return null;
		}
	}
}

