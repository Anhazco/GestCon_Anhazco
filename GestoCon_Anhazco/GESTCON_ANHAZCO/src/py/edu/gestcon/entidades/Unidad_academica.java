package py.edu.gestcon.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="tb_unidad_academica")
public class Unidad_academica {
	
	@Id
	@GenericGenerator(name= "ua_generator" , strategy="increment")
	@GeneratedValue(generator="ua_generator")
	@Column(name="id")
	private int id;
	@Column (name="ua_nombre", nullable=false, length=255)
	private String nombre;
	@Column (name="ua_breviatura", nullable=false, length=45)
	private String abreviatura;
	@Column (name="ua_telefono", nullable=false, length=45)
	private String telefono;
	@Column (name="ua_celular", nullable=false, length=45)
	private String celular;
	@Column (name="ua_email", nullable=false, length=45)
	private String email;
	@Column (name="ua_direccion", nullable=false, length=45)
	private String direccion;
	@Column (name="ua_estado", nullable=false)
	private boolean estado;
	
	@ManyToOne
	private Universidad universidad;
	@ManyToOne
	private Firmantes firmantes;
	
	@OneToMany(mappedBy="unidad_academica")
	private List<Convenio> convenios;
	
	//getters and setters 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isEstado() {
		return estado;
	}

	public Universidad getUniversidad() {
		return universidad;
	}

	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
	}

	public Firmantes getFirmantes() {
		return firmantes;
	}

	public void setFirmantes(Firmantes firmantes) {
		this.firmantes = firmantes;
	}

	public List<Convenio> getConvenios() {
		return convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
