package AE_Desing.ElBazar.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String descripcion;
	private LocalDate fecha = LocalDate.now();
	private Integer existencia;
	private Integer relevante;
	private Double costo;	
	private String portada = "no-image.png";
	
	@OneToOne
	@JoinColumn(name="idClasificacion")
	private Clasificacion clasificacion;
	
	@OneToOne
	@JoinColumn(name="idEditorial")
	private Editorial editorial;
	
	@OneToOne
	@JoinColumn(name="idAutor")	
	private Autor autor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Integer getExistencia() {
		return existencia;
	}

	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}

	public Integer getRelevante() {
		return relevante;
	}

	public void setRelevante(Integer relevante) {
		this.relevante = relevante;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", existencia=" + existencia + ", relevante=" + relevante + ", costo=" + costo + ", portada="
				+ portada + ", clasificacion=" + clasificacion + ", editorial=" + editorial + ", autor=" + autor + "]";
	}

		
}
