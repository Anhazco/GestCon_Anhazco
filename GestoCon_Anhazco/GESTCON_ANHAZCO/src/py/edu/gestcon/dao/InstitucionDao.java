package py.edu.gestcon.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.gestcon.entidades.Institucion;

public class InstitucionDao extends GenericDao<Institucion> {

	public InstitucionDao( ) {
		super(Institucion.class);
	}

	@Override
	public List<Institucion> recuperarPorFiltro(String filtro) {
		iniciarTransaccion();
		String hql = "from tb_institucion where UPPER(nombre) like :filtro"
				+ " or UPPER(abreviatura) like :filtro "
				+ " or UPPER(tipo) like :filtro "
				+ "or UPPER(telefono) like :filtro"
				+ " or UPPER(celular) like :filtro "
				+ "or UPPER(direccion) like :filtro order by id";
		Query<Institucion> query = getSession().createQuery(hql);
		query.setParameter("filtro", "%" +filtro.toUpperCase() + "%");
		List<Institucion> lista =  query.getResultList();
		getSession().getTransaction().commit();
		return lista;
	}

	public List<Institucion> filtroListadoInstitucion(int dId, int hId, String dDescripcion, String hDescripcion, String order){
		iniciarTransaccion();
		String sql = "from tb_institucion where id BETWEEN "+dId+" and "+hId+" "
				+ "and UPPER(nombre) BETWEEN :ddescripcion and :hdescripcion "
				+ "order by "+order.toLowerCase();

		Query<Institucion> query = getSession().createQuery(sql);
		query.setParameter("ddescripcion", dDescripcion.toUpperCase());
		query.setParameter("hdescripcion", hDescripcion.toUpperCase()+"zzzzz");

		List<Institucion> lista = query.getResultList();
		commit();
		return lista;
	}


}
