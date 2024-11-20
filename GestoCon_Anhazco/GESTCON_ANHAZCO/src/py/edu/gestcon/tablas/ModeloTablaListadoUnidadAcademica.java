package py.edu.gestcon.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.gestcon.entidades.Unidad_academica;

public class ModeloTablaListadoUnidadAcademica extends AbstractTableModel {

	private String[] columnas = {"Id","Nombre", "Abreviatura", "Teléfono","Celular","Email", "Dirección"};
	private List<Unidad_academica> lista = new ArrayList<Unidad_academica>();

	public void setLista(List<Unidad_academica> lista) {
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
		switch (c) {
		case 0:
			return lista.get(r).getId();
		case 1:
			return lista.get(r).getNombre();
		case 2:
			return lista.get(r).getAbreviatura();
		case 3:
			return lista.get(r).getTelefono();
		case 4:
			return lista.get(r).getCelular();
		case 5:
			return lista.get(r).getEmail();
		case 6:
			return lista.get(r).getDireccion();
		}
		return null;
	}

}
