package com.desarrolloweb.spring.app.entities;


import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
/**
 * The persistent class for the bebidas database table.
 * 
 */
@Entity
@Table(name="bebidas")
@NamedQuery(name="Bebida.findAll", query="SELECT b FROM Bebida b")
public class Bebida extends Audit {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bebida")
	private Long idBebida;

	private String descripcion;

	private double precio;

	//bi-directional many-to-one association to Tipo
	@ManyToOne
	@JoinColumn(name="id_tipo")
	private Tipo tipo;

	//bi-directional many-to-one association to Orden
	@OneToMany(mappedBy="bebida", fetch=FetchType.EAGER)
	private List<Orden> ordens;

	//bi-directional many-to-one association to Reservacione
	@OneToMany(mappedBy="bebida", fetch=FetchType.EAGER)
	private List<Reservacione> reservaciones;

	public Bebida() {
	}

	

	public Long getIdBebida() {
		return idBebida;
	}



	public void setIdBebida(Long idBebida) {
		this.idBebida = idBebida;
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

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Orden> getOrdens() {
		return this.ordens;
	}

	public void setOrdens(List<Orden> ordens) {
		this.ordens = ordens;
	}

	public Orden addOrden(Orden orden) {
		getOrdens().add(orden);
		orden.setBebida(this);

		return orden;
	}

	public Orden removeOrden(Orden orden) {
		getOrdens().remove(orden);
		orden.setBebida(null);

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
		reservacione.setBebida(this);

		return reservacione;
	}

	public Reservacione removeReservacione(Reservacione reservacione) {
		getReservaciones().remove(reservacione);
		reservacione.setBebida(null);

		return reservacione;
	}

}