package py.edu.gestcon.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.gestcon.entidades.Universidad;

public class UniversidadDao extends GenericDao<Universidad> {

	public UniversidadDao() {
		super(Universidad.class);
	}

	@Override
	public List<Universidad> recuperarPorFiltro(String filtro) {
		iniciarTransaccion();
		String hql = "from tb_universidad where UPPER(descripcion) like :filtro"
				+ " or UPPER(abreviatura) like :filtro "
				+ " or UPPER(telefono) like :filtro order by id";
		Query<Universidad> query = getSession().createQuery(hql);
		query.setParameter("filtro", "%" +filtro.toUpperCase() + "%");
		List<Universidad> lista =  query.getResultList();
		commit();
		return lista;
	}
	public List<Universidad> filtroListadoUniversidad(int dId, int hId, String dDescripcion, String hDescripcion, String order){
		iniciarTransaccion();
		String sql = "from tb_universidad where id BETWEEN "+dId+" and "+hId+" "
				+ "and UPPER(descripcion) BETWEEN :ddescripcion and :hdescripcion "
				+ "order by "+order.toLowerCase();

		Query<Universidad> query = getSession().createQuery(sql);
		query.setParameter("ddescripcion", dDescripcion.toUpperCase());
		query.setParameter("hdescripcion", hDescripcion.toUpperCase()+"zzzzz");

		List<Universidad> lista = query.getResultList();
		return lista;
	}
	public List<Universidad> recuperarPorEstado(boolean estado) {
		iniciarTransaccion();
		String hql = "from tb_universidad where estado = :estado";
		Query<Universidad> query = getSession().createQuery(hql);
		query.setParameter("estado", estado);
		List<Universidad> lista = query.getResultList();
		commit();
		return lista;
	}


}
