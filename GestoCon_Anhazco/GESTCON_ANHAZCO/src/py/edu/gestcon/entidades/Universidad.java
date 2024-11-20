package py.edu.gestcon.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="tb_universidad")
public class Universidad {
	@Id
	@GenericGenerator(name= "uni_generator" , strategy="increment")
	@GeneratedValue(generator="uni_generator")
	@Column(name="id")
	private int id;
	@Column (name="uni_descripcion", nullable=false, length=255)
	private String descripcion;
	@Column (name="uni_abreviatura", nullable=false, length=45)
	private String abreviatura;
	@Column (name="uni_telefono", nullable=false, length=45)
	private String telefono;
	@Column (name="uni_celular", nullable=false, length=45)
	private String celular;
	@Column (name="uni_email", nullable=false, length=45)
	private String email;
	@Column (name="_uni_estado", nullable=false)
	private boolean estado;
	
	@OneToMany(mappedBy="universidad")
	private List<Unidad_academica> unidad_academicas;
	
	
	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
