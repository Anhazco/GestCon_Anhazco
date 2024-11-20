package py.edu.gestcon.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.gestcon.entidades.Seguimiento;

public class SeguimientoDao extends GenericDao<Seguimiento> {

	public SeguimientoDao() {
		super(Seguimiento.class);
	}
	
	@Override
	public List<Seguimiento> recuperarPorFiltro(String filtro) {
		iniciarTransaccion();
		String hql = "from tb_seguimiento where UPPER(actividad) like :filtro";
		Query<Seguimiento> query = getSession().createQuery(hql);
		query.setParameter("filtro", "%" +filtro.toUpperCase() + "%");
		List<Seguimiento> lista =  query.getResultList();
		getSession().getTransaction().commit();
		return lista;
	}

	public List<Seguimiento> filtroListadoSeguimiento(int dId, int hId, String dActividad, String hActividad, String order){
		iniciarTransaccion();
		String sql = "from tb_seguimiento where id BETWEEN "+dId+" and "+hId+" "
				+ "and UPPER(actividad) BETWEEN :dactividad and :hactividad "
				+ "order by "+order.toLowerCase();

		Query<Seguimiento> query = getSession().createQuery(sql);
		query.setParameter("dactividad", dActividad.toUpperCase());
		query.setParameter("hactividad", hActividad.toUpperCase()+"zzzzz");

		List<Seguimiento> lista = query.getResultList();
		commit();
		return lista;
	}
}
