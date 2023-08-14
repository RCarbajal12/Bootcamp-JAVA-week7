package com.romina.eventos.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "eventos")
public class EventModel {

	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = " Por favor ingresa un nombre")
	@Size(min = 3, max = 30, message = "Nombre debe ser mayor a 3 caracteres y menor a 30")
	private String nombre;

	
	//EN DATE NO SE PUEDE PONER @NOTBLANK
	@Future(message = "Por favor ingresa una fecha posterior")//ERROR SI LA FECHA ES ANTIGUA
	@DateTimeFormat(pattern = "yyyy-MM-dd")//TIPO DE FORMATO DE FECHA
	private Date fecha;

	@NotBlank(message = " Por favor ingresa una ubicacion")
	private String ubicacion;

	@NotBlank(message = " Por favor elige una provincia")
	private String provincia;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	//RELACION UNO A MUCHOS (1:N) HACIA USUARIO
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User organizador;
	
	//RELACION MUCHOS A MUCHOS (N:N) HACIA USUARIOS
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="asistentes",
	joinColumns=@JoinColumn(name="event_id"),
	inverseJoinColumns=@JoinColumn(name="user_id"))
	private List<User> asistentes;
	
	//RELACION MUCHOS A MUCHOS HACIA MENSAJES
	@OneToMany(mappedBy="evento", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MensajeModel> mensajes;
	

	//CONSTRUCTOR VACÍO
	public EventModel() {

	}

	public List<User> getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(List<User> asistentes) {
		this.asistentes = asistentes;
	}
	

	public List<MensajeModel> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<MensajeModel> mensajes) {
		this.mensajes = mensajes;
	}

	//GETTERS Y SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getOrganizador() {
		return organizador;
	}

	public void setOrganizador(User organizador) {
		this.organizador = organizador;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}