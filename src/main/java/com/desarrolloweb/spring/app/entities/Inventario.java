package com.desarrolloweb.spring.app.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the inventario database table.
 * 
 */
@Entity
@NamedQuery(name="Inventario.findAll", query="SELECT i FROM Inventario i")
public class Inventario extends Audit {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_inventario")
	private Long idInventario;

	private Long cantidad;

	private String nombre;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;

	//bi-directional many-to-one association to Umedida
	@ManyToOne
	@JoinColumn(name="id_uniad")
	private Umedida umedida;

	public Inventario() {
	}

	public Long getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(Long idInventario) {
		this.idInventario = idInventario;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Umedida getUmedida() {
		return this.umedida;
	}

	public void setUmedida(Umedida umedida) {
		this.umedida = umedida;
	}

}