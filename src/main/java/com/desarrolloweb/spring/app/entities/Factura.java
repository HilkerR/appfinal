package com.desarrolloweb.spring.app.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.util.Date;


/**
 * The persistent class for the factura database table.
 * 
 */
@Entity
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura extends Audit {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_factura")
	private Long idFactura;

	private String descripcion;

	private String direccion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Long nit;

	private String nombre;

	private double total;

	//bi-directional many-to-one association to Orden
	@ManyToOne
	@JoinColumn(name="id_orden")
	private Orden orden;

	public Factura() {
	}

	

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdFactura() {
		return idFactura;
	}



	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}



	public Long getNit() {
		return nit;
	}



	public void setNit(Long nit) {
		this.nit = nit;
	}



	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Orden getOrden() {
		return this.orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

}