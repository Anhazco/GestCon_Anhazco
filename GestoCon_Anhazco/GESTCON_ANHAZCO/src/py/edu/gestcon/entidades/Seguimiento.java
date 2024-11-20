package py.edu.gestcon.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
@Entity(name="tb_seguimiento")
public class Seguimiento {
	@Id
	@GenericGenerator(name= "se_generator" , strategy="increment")
	@GeneratedValue(generator="se_generator")
	@Column(name="id")
	private int id;
	@Column (name="se_fecha", nullable=false)
	private Date fecha;
	@Column (name="se_actividad", nullable=false)
	private String actividad;
	
	@ManyToOne
	private Convenio convenio;
	
	//getters and setters 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
}
