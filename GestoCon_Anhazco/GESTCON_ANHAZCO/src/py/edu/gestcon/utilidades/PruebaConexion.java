package py.edu.gestcon.utilidades;

import py.edu.gestcon.dao.UniversidadDao;
import py.edu.gestcon.entidades.Universidad;

public class PruebaConexion {

	public static void main(String[] args) {
		Universidad universidad = new Universidad();
		universidad.setDescripcion("UNICAN");
		universidad.setEstado(true);
		universidad.setAbreviatura("UCAN");
		universidad.setCelular("123456789");
		universidad.setEmail("info@unican.edu.py");
		universidad.setTelefono("0987654321"); 

		UniversidadDao universidadDao = new UniversidadDao();
		try {
			universidadDao.guardar(universidad);
			universidadDao.commit();
			System.out.println("Universidad guardada exitosamente.");
		} catch (Exception e) {
			universidadDao.rollback();
			e.printStackTrace();
		}
	}
}
