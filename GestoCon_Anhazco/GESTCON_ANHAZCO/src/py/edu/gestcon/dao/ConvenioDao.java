package py.edu.gestcon.dao;

import java.util.List;

import org.hibernate.query.Query;

import py.edu.gestcon.entidades.Convenio;

public class ConvenioDao extends GenericDao<Convenio> {

	public ConvenioDao() {
		super(Convenio.class);
	}
	@Override
	public List<Convenio> recuperarPorFiltro(String filtro) {
		iniciarTransaccion();
		String hql = "from tb_convenio where UPPER(nombre) like :filtro"
				+ " or UPPER(categoria) like :filtro "
				+ " or UPPER(duracion) like :filtro "
				+ " or UPPER(objetivos) like :filtro order by id";
		Query<Convenio> query = getSession().createQuery(hql);
		query.setParameter("filtro", "%" +filtro.toUpperCase() + "%");
		List<Convenio> lista =  query.getResultList();
		getSession().getTransaction().commit();
		return lista;
	}

	public List<Convenio> filtroListadoConvenio(int dId, int hId, String dNombre, String hNombre,String order){
		iniciarTransaccion();
		String sql = "from tb_convenio where id BETWEEN "+dId+" and "+hId+" "
				+ "and UPPER(nombre) BETWEEN :dnombre and :hnombre ".toLowerCase();

		Query<Convenio> query = getSession().createQuery(sql);
		query.setParameter("dnombre", dNombre.toUpperCase());
		query.setParameter("hnombre", hNombre.toUpperCase()+"zzzzz");

		List<Convenio> lista = query.getResultList();
		commit();
		return lista;
	}
}
