package com.desarrolloweb.spring.app.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import java.util.List;


/**
 * The persistent class for the postres database table.
 * 
 */
@Entity
@Table(name="postres")
@NamedQuery(name="Postre.findAll", query="SELECT p FROM Postre p")
public class Postre extends Audit {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_postre")
	private Long idPostre;

	private String descripcion;

	private double precio;

	//bi-directional many-to-one association to Orden
	@OneToMany(mappedBy="postre", fetch=FetchType.EAGER)
	private List<Orden> ordens;

	//bi-directional many-to-one association to Tipo
	@ManyToOne
	@JoinColumn(name="id_tipo")
	private Tipo tipo;

	//bi-directional many-to-one association to Reservacione
	@OneToMany(mappedBy="postre", fetch=FetchType.EAGER)
	private List<Reservacione> reservaciones;

	public Postre() {
	}



	public Long getIdPostre() {
		return idPostre;
	}



	public void setIdPostre(Long idPostre) {
		this.idPostre = idPostre;
	}



	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Orden> getOrdens() {
		return this.ordens;
	}

	public void setOrdens(List<Orden> ordens) {
		this.ordens = ordens;
	}

	public Orden addOrden(Orden orden) {
		getOrdens().add(orden);
		orden.setPostre(this);

		return orden;
	}

	public Orden removeOrden(Orden orden) {
		getOrdens().remove(orden);
		orden.setPostre(null);

		return orden;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Reservacione> getReservaciones() {
		return this.reservaciones;
	}

	public void setReservaciones(List<Reservacione> reservaciones) {
		this.reservaciones = reservaciones;
	}

	public Reservacione addReservacione(Reservacione reservacione) {
		getReservaciones().add(reservacione);
		reservacione.setPostre(this);

		return reservacione;
	}

	public Reservacione removeReservacione(Reservacione reservacione) {
		getReservaciones().remove(reservacione);
		reservacione.setPostre(null);

		return reservacione;
	}

}