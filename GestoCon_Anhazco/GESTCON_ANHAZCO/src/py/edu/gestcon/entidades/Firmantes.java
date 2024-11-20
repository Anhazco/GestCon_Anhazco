package py.edu.gestcon.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name= "tb_firmantes")
public class Firmantes {
	@Id
	@GenericGenerator(name="fir_generator", strategy = "increment")
	@GeneratedValue(generator = "fir_generator")
	@Column(name="id")
	private int id;
	@Column (name="fir_nombre", nullable=false, length=255)
	private String nombre;
	@Column (name="fir_apellido", nullable=false, length=255)
	private String apellido;
	@Column (name="fir_documento", nullable=false, length=45, unique=true)
	private String documento;
	@Column (name="fif_cargo", nullable=false, length=255)
	private String cargo;
	@Column (name="fir_resolucionRenombramiento", nullable=false, length=45)
	private String resolucion_renombramiento;
	@Column (name="fir_fechaResolucion", nullable=false)
	private Date fecha_resolucion;
	@Column (name="fir_direccion", nullable=false, length=255)
	private String direccion;
	@Column (name="fir_barrio", nullable=false, length=255)
	private String barrio;
	@Column (name="fir_ciudad", nullable=false, length=255)
	private String ciudad;
	
	@OneToMany(mappedBy="firmantes")
	private List<Unidad_academica> unidad_academicas;
	
	@OneToMany(mappedBy="firmantes")
	private List<Institucion> institucion;
	
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getResolucion_renombramiento() {
		return resolucion_renombramiento;
	}

	public void setResolucion_renombramiento(String resolucion_renombramiento) {
		this.resolucion_renombramiento = resolucion_renombramiento;
	}

	public Date getFecha_resolucion() {
		return fecha_resolucion;
	}

	public void setFecha_resolucion(Date fecha_resolucion) {
		this.fecha_resolucion = fecha_resolucion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	
	
	

}
