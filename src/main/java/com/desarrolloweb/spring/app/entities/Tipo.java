package com.desarrolloweb.spring.app.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import java.util.List;


/**
 * The persistent class for the tipos database table.
 * 
 */
@Entity
@Table(name="tipos")
@NamedQuery(name="Tipo.findAll", query="SELECT t FROM Tipo t")
public class Tipo extends Audit {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo")
	private Long idTipo;

	private String descripcion;

	//bi-directional many-to-one association to Bebida
	@OneToMany(mappedBy="tipo", fetch=FetchType.EAGER)
	private List<Bebida> bebidas;

	//bi-directional many-to-one association to Platillo
	@OneToMany(mappedBy="tipo", fetch=FetchType.EAGER)
	private List<Platillo> platillos;

	//bi-directional many-to-one association to Postre
	@OneToMany(mappedBy="tipo", fetch=FetchType.EAGER)
	private List<Postre> postres;

	//bi-directional many-to-one association to Reservacione
	@OneToMany(mappedBy="tipo", fetch=FetchType.EAGER)
	private List<Reservacione> reservaciones;

	public Tipo() {
	}

	

	public Long getIdTipo() {
		return idTipo;
	}



	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}



	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Bebida> getBebidas() {
		return this.bebidas;
	}

	public void setBebidas(List<Bebida> bebidas) {
		this.bebidas = bebidas;
	}

	public Bebida addBebida(Bebida bebida) {
		getBebidas().add(bebida);
		bebida.setTipo(this);

		return bebida;
	}

	public Bebida removeBebida(Bebida bebida) {
		getBebidas().remove(bebida);
		bebida.setTipo(null);

		return bebida;
	}

	public List<Platillo> getPlatillos() {
		return this.platillos;
	}

	public void setPlatillos(List<Platillo> platillos) {
		this.platillos = platillos;
	}

	public Platillo addPlatillo(Platillo platillo) {
		getPlatillos().add(platillo);
		platillo.setTipo(this);

		return platillo;
	}

	public Platillo removePlatillo(Platillo platillo) {
		getPlatillos().remove(platillo);
		platillo.setTipo(null);

		return platillo;
	}

	public List<Postre> getPostres() {
		return this.postres;
	}

	public void setPostres(List<Postre> postres) {
		this.postres = postres;
	}

	public Postre addPostre(Postre postre) {
		getPostres().add(postre);
		postre.setTipo(this);

		return postre;
	}

	public Postre removePostre(Postre postre) {
		getPostres().remove(postre);
		postre.setTipo(null);

		return postre;
	}

	public List<Reservacione> getReservaciones() {
		return this.reservaciones;
	}

	public void setReservaciones(List<Reservacione> reservaciones) {
		this.reservaciones = reservaciones;
	}

	public Reservacione addReservacione(Reservacione reservacione) {
		getReservaciones().add(reservacione);
		reservacione.setTipo(this);

		return reservacione;
	}

	public Reservacione removeReservacione(Reservacione reservacione) {
		getReservaciones().remove(reservacione);
		reservacione.setTipo(null);

		return reservacione;
	}

}