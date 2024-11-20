package py.edu.gestcon.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "tb_institucion")
public class Institucion {
	@Id
	@GenericGenerator(name= "ins_generator" , strategy="increment")
	@GeneratedValue(generator="ins_generator")
	@Column(name="id")
	private int id;
	@Column (name="ins_nombre", nullable=false, length=255)
	private String nombre;
	@Column (name="ins_abreviatura", nullable=false, length=45)
	private String abreviatura;
	@Column (name="ins_rubro", nullable=false, length=255)
	private String rubro;
	@Column (name="ins_tipo", nullable=false, length=45)
	private String tipo;
	@Column (name="ins_teléfono", nullable=false, length=45)
	private String telefono;
	@Column (name="ins_celular", nullable=false, length=45)
	private String celular;
	@Column (name="ins_dirección", nullable=false, length=255)
	private String direccion;

	@ManyToOne
	private Firmantes firmantes;
	
	@ManyToOne
	@JoinColumn(name="convenio_id")
	private Convenio convenio;
	
	//Getters and setters 
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

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Firmantes getFirmantes() {
		return firmantes;
	}

	public void setFirmantes(Firmantes firmantes) {
		this.firmantes = firmantes;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

}
