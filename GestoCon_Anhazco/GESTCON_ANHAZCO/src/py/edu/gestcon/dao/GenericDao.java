package py.edu.gestcon.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import py.edu.gestcon.utilidades.ConnectionHelper;

public abstract class GenericDao <T> {


	protected Class<T> clase;

	public GenericDao(Class<T>clase) {
		this.clase=clase;
	}

	protected Session getSession(){
		return ConnectionHelper.getSessionFactory().getCurrentSession();
	}
	public void iniciarTransaccion(){
		if( !getSession().getTransaction().isActive())getSession().beginTransaction();
	}

	public void commit(){
		getSession().flush();
		getSession().getTransaction().commit();
	}
	public void rollback(){
		getSession().getTransaction().rollback();
	}
	public void guardar(T entity)throws Exception{
		iniciarTransaccion();
		getSession().saveOrUpdate(entity);

	}
	public void actualizar( T entity) throws Exception {
		iniciarTransaccion();
		getSession().update(entity);
	}

	public void eliminar(T entity) throws Exception{
		iniciarTransaccion();
		getSession().delete(entity);

	}	
	public T recuperarPorId(Serializable id) {
	    if (!getSession().getTransaction().isActive()) {
	        getSession().beginTransaction();
	    }
	    T result = getSession().get(clase, id);
	    return result;
	}

	public void eliminarTodos(String tabla) {
		iniciarTransaccion();
		String deleteAll = "TRUNCATE TABLE "+tabla+" cascade";
		Query query = getSession().createNativeQuery(deleteAll);
		query.executeUpdate();
	}

	public List<T> recuperarTodos(){
	    String hql = "from " + clase.getName() + " order by id"; // Agregar el espacio antes de "order"
	    Query<T> query = getSession().createQuery(hql);
	    return query.getResultList();
	}


	public void inizializarTabla(String tabla) {
		iniciarTransaccion();
		String sql = "TRUNCATE TABLE " + tabla +  " CASCADE";
		Query query = getSession().createNativeQuery(sql);
		query.executeUpdate();
	}
	public void guardarActualizar(T entity) throws Exception{
		iniciarTransaccion();
		getSession().saveOrUpdate(entity);
	}

	public abstract List<T> recuperarPorFiltro(String filtro);

}

