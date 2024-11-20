package py.edu.gestcon.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.gestcon.entidades.Unidad_academica;

public class Unidad_AcademicaDao extends GenericDao<Unidad_academica> {

	public Unidad_AcademicaDao() {
		super(Unidad_academica.class);
	}

	@Override
	public List<Unidad_academica> recuperarPorFiltro(String filtro) {
		iniciarTransaccion();
		String hql = "from tb_unidad_academica where UPPER(nombre) like :filtro"
				+ " or UPPER(abreviatura) like :filtro "
				+ " or UPPER(telefono) like :filtro "
				+ " or UPPER(celular) like :filtro "
				+ " or UPPER(email) like :filtro "
				+ " or UPPER(direccion) like :filtro order by id";
		Query<Unidad_academica> query = getSession().createQuery(hql);
		query.setParameter("filtro", "%" +filtro.toUpperCase() + "%");
		List<Unidad_academica> lista =  query.getResultList();
		getSession().getTransaction().commit();
		return lista;
	}
	public List<Unidad_academica> filtroListadoUnidad_academicas(int dId, int hId, String dDescripcion, String hDescripcion, String order){
		iniciarTransaccion();
		String sql = "from tb_unidad_academica where id BETWEEN "+dId+" and "+hId+" "
				+ "and UPPER(nombre) BETWEEN :ddescripcion and :hdescripcion "
				+ "order by "+order.toLowerCase();

		Query<Unidad_academica> query = getSession().createQuery(sql);
		query.setParameter("ddescripcion", dDescripcion.toUpperCase());
		query.setParameter("hdescripcion", hDescripcion.toUpperCase()+"zzzzz");

		List<Unidad_academica> lista = query.getResultList();
		commit();
		return lista;
	}
}
