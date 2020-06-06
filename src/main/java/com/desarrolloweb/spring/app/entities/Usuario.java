package com.desarrolloweb.spring.app.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario extends Audit {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Long idUsuario;

	@Column(name="contrasena_")
	private String contrasena;

	private String nombre;

	//bi-directional many-to-one association to Orden
	@OneToMany(mappedBy="usuario", fetch=FetchType.EAGER)
	private List<Orden> ordens;

	//bi-directional many-to-one association to Reservacione
	@OneToMany(mappedBy="usuario", fetch=FetchType.EAGER)
	private List<Reservacione> reservaciones;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="id_rol")
	private Role role;

	public Usuario() {
	}


	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Orden> getOrdens() {
		return this.ordens;
	}

	public void setOrdens(List<Orden> ordens) {
		this.ordens = ordens;
	}

	public Orden addOrden(Orden orden) {
		getOrdens().add(orden);
		orden.setUsuario(this);

		return orden;
	}

	public Orden removeOrden(Orden orden) {
		getOrdens().remove(orden);
		orden.setUsuario(null);

		return orden;
	}

	public List<Reservacione> getReservaciones() {
		return this.reservaciones;
	}

	public void setReservaciones(List<Reservacione> reservaciones) {
		this.reservaciones = reservaciones;
	}

	public Reservacione addReservacione(Reservacione reservacione) {
		getReservaciones().add(reservacione);
		reservacione.setUsuario(this);

		return reservacione;
	}

	public Reservacione removeReservacione(Reservacione reservacione) {
		getReservaciones().remove(reservacione);
		reservacione.setUsuario(null);

		return reservacione;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}