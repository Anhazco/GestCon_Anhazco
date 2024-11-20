package py.edu.gestcon.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.gestcon.entidades.Universidad;

public class ModeloTablaListadoUniversidad extends AbstractTableModel {

	private String clumnas[] = {"Código", "Descripción-Nombre", "Abreviatura","Celular"};
	private List<Universidad> lista = new ArrayList<Universidad>();

	public void setLista(List<Universidad> lista) {
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
			return lista.get(r).getDescripcion();
		case 2:
			return lista.get(r).getAbreviatura();
		case 3:
			return lista.get(r).getCelular();
		}
		return null;
	}
}
