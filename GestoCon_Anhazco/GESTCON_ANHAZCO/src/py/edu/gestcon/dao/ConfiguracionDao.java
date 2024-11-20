package py.edu.gestcon.dao;

import java.util.List;

import py.edu.gestcon.entidades.Configuracion;

public class ConfiguracionDao extends GenericDao<Configuracion> {
	
	public ConfiguracionDao() {
		super(Configuracion.class);
	}
	@Override
	public List<Configuracion> recuperarPorFiltro(String filtro) {
		// TODO Auto-generated method stub
		return null;
	}
}
