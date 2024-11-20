package py.edu.gestcon.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.gestcon.entidades.Convenio;
import py.edu.gestcon.utilidades.UtilidadesFecha;

public class ModeloTablaInformeConvenio extends AbstractTableModel {

	private String clumnas[] = {"Código", "Nombre", "Categoria","Fecha Ini.","Fecha Cad.","Duración","Objetivos",};
	private List<Convenio> lista = new ArrayList<Convenio>();

	public void setLista(List<Convenio> lista) {
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
			return lista.get(r).getNombre();
		case 2:
			return lista.get(r).getCategoria();
		case 3:
			return UtilidadesFecha.fechaAString(lista.get(r).getFecha_inicio());
		case 4 :
			return UtilidadesFecha.fechaAString(lista.get(r).getFecha_caducidad());
		case 5:
			return lista.get(r).getDuracion();
		case 6:
			return lista.get(r).getObjetivos();
		}
		return null;
	}
}
