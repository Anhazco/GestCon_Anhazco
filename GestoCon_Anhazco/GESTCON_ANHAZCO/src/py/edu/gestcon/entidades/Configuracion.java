package py.edu.gestcon.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="tb_configuracion")
public class Configuracion {
	@Id
	@Column(name="cfg_codigo")
	private int id;
	
	@Column(name="cfg_org", nullable = false)
	private String org;
	
	@Column(name="cfg_telefono")
	private String telefono;
	
	//getters and setters 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
