package py.edu.gestcon.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.gestcon.entidades.Firmantes;
import py.edu.gestcon.utilidades.UtilidadesFecha;

public class ModeloTablaFirmante extends AbstractTableModel {

	private String[] columnas = {"Nombre y Apellido", "Cargo", "CI-Documento","N° Resolución","Fec. Res", "Dirección","Ciudad"};
	private List<Firmantes> lista = new ArrayList<Firmantes>();

	public void setLista(List<Firmantes> lista) {
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
			return lista.get(r).getNombre()+" "+lista.get(r).getApellido();
		case 1:
			return lista.get(r).getCargo();
		case 2:
			return lista.get(r).getDocumento();
		case 3:
			return lista.get(r).getResolucion_renombramiento();
		case 4:
			return UtilidadesFecha.fechaAString(lista.get(r).getFecha_resolucion());
		case 5:
			return lista.get(r).getDireccion();
		case 6:
			return lista.get(r).getCiudad();
		}
		return null;
	}

}
