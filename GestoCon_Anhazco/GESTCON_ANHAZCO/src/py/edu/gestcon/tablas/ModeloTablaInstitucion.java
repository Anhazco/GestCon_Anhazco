package py.edu.gestcon.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.gestcon.entidades.Institucion;

public class ModeloTablaInstitucion extends AbstractTableModel {

	private String[] columnas = {"Nombre", "Abreviatura", "Rubro","Tipo", "Teléfono", "Celular" ,"Dirección"};
	private List<Institucion> lista = new ArrayList<Institucion>();

	public void setLista(List<Institucion> lista) {
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
			return lista.get(r).getNombre();
		case 1:
			return lista.get(r).getAbreviatura();
		case 2:
			return lista.get(r).getRubro();
		case 3:
			return lista.get(r).getTipo();
		case 4:
			return lista.get(r).getTelefono();
		case 5:
			return lista.get(r).getCelular();
		case 6:
			return lista.get(r).getDireccion();
		}
		return null;
	}
}
