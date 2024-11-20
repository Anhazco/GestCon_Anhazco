package py.edu.gestcon.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.gestcon.entidades.Firmantes;

public class FirmantesDao extends GenericDao<Firmantes> {

	public FirmantesDao() {
		super(Firmantes.class);
	}

	@Override
	public List<Firmantes> recuperarPorFiltro(String filtro) {
		iniciarTransaccion();

		String hql = "from tb_firmantes where UPPER(nombre) like :filtro "
				+ " or UPPER(apellido) like :filtro "
				+ " or UPPER(documento) like :filtro "
				+ " or UPPER(cargo) like :filtro order by id";
		Query<Firmantes> query = getSession().createQuery(hql);
		query.setParameter("filtro", "%" +filtro.toUpperCase() + "%");
		List<Firmantes> lista =  query.getResultList();
		commit();
		return lista;
	}
	public Firmantes verificarCedula(String ci){
		iniciarTransaccion();
		String hql = "from tb_firmantes where UPPER(documento) like :filtro ";
		Query<Firmantes> query = getSession().createQuery(hql);
		query.setParameter("filtro", ci.toUpperCase());
		Firmantes firmantes;
		try {
			firmantes = query.getSingleResult();
		} catch (Exception e) {
			firmantes = null;
			e.printStackTrace();
		}
		commit();
		return firmantes;
	}

	public List<Firmantes> filtroListadoFirmantes(int dId, int hId, String dNombre, String hNombre, String order){
		iniciarTransaccion();
		 // Usando HQL y alias de entidad
	    String hql = "SELECT f FROM tb_firmantes f WHERE f.id BETWEEN :dId AND :hId " 
	                + "AND UPPER(f.nombre) BETWEEN :dnombre AND :hnombre "
	                + "ORDER BY f." + order.toLowerCase(); 

	    Query<Firmantes> query = getSession().createQuery(hql, Firmantes.class);
	    query.setParameter("dId", dId);
	    query.setParameter("hId", hId);
	    query.setParameter("dnombre", dNombre.toUpperCase());
	    query.setParameter("hnombre", hNombre.toUpperCase() + "zzzzz");

	    List<Firmantes> lista = query.getResultList();
	    return lista;
	}
}
