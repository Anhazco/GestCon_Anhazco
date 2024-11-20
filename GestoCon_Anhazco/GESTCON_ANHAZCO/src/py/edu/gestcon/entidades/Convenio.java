package py.edu.gestcon.entidades;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "tb_convenio")
public class Convenio {
	@Id
	@GenericGenerator(name = "con_generator", strategy = "increment")
	@GeneratedValue(generator = "con_generator")
	@Column(name = "id")
	private int id;
	@Column(name = "con_nombre", nullable = false, length = 255)
	private String nombre;
	@Column(name = "con_categoria", nullable = false, length = 255)
	private String categoria;
	@Column(name = "con_fechaInicio", nullable = false)
	private Date fecha_inicio;
	@Column(name = "con_fechaCaducidad", nullable = false)
	private java.util.Date fecha_caducidad;
	@Column(name = "con_duración", nullable = false, length = 255)
	private String duracion;
	@Column(name = "con_objetivos", nullable = false)
	private String objetivos;
	@Column(name = "con_estado", nullable = false)
	private boolean estado;
	
	@ManyToOne
	private Unidad_academica unidad_academica;

	@OneToMany(mappedBy = "convenio", fetch = FetchType.EAGER)
	private List<Seguimiento> seguimientos;

	@ManyToOne
	private Institucion institucion;
	
	
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_caducidad() {
		return fecha_caducidad;
	}

	public void setFecha_caducidad(Date fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Unidad_academica getUnidad_academica() {
		return unidad_academica;
	}

	public void setUnidad_academica(Unidad_academica unidad_academica) {
		this.unidad_academica = unidad_academica;
	}

	public List<Seguimiento> getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
}
