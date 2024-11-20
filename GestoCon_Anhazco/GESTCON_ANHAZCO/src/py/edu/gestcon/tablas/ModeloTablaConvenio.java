package py.edu.gestcon.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.gestcon.entidades.Convenio;
import py.edu.gestcon.utilidades.UtilidadesFecha;

public class ModeloTablaConvenio extends AbstractTableModel {

	private String[] columnas = {"Nombre", "Categoria", "Fecha_inicio","Fecha_caducidad","Duración", "Objetivos"};
	private List<Convenio> lista = new ArrayList<Convenio>();

	public void setLista(List<Convenio> lista) {
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
			return lista.get(r).getCategoria();
		case 2:
			return UtilidadesFecha.fechaAString(lista.get(r).getFecha_inicio());
		case 3:
			return UtilidadesFecha.fechaAString(lista.get(r).getFecha_caducidad());
		case 4:
			return lista.get(r).getDuracion();
		case 5:
			return lista.get(r).getObjetivos();
		}
		return null;
	}
}
